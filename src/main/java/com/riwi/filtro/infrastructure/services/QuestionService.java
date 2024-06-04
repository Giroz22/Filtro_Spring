package com.riwi.filtro.infrastructure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.filtro.api.dto.request.QuestionAndOptionsUpdateRequest;
import com.riwi.filtro.api.dto.request.QuestionCreateRequest;
import com.riwi.filtro.api.dto.request.QuestionUpdateRequest;
import com.riwi.filtro.api.dto.response.QuestionResponse;
import com.riwi.filtro.domain.entitties.OptionQuestion;
import com.riwi.filtro.domain.entitties.Question;
import com.riwi.filtro.domain.repositories.OptionQuestionRepository;
import com.riwi.filtro.domain.repositories.QuestionRepository;
import com.riwi.filtro.infrastructure.abstract_services.IQuestionService;
import com.riwi.filtro.infrastructure.helpers.mappers.Mapper;
import com.riwi.filtro.infrastructure.helpers.mappers.OptionQuestionMapper;
import com.riwi.filtro.infrastructure.helpers.mappers.QuestionMapper;
import com.riwi.filtro.util.enums.TypeQuestion;
import com.riwi.filtro.util.exceptions.IdNotFoundException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionService implements IQuestionService{

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final OptionQuestionRepository optionQuestionRepository;

    @Autowired
    private final QuestionMapper questionMapper;

    @Autowired
    private final OptionQuestionMapper optionQuestionMapper;

    @Override
    public Page<QuestionResponse> getAll(int page, int size) {
        if (page<0) page = 0;
        PageRequest pagination = PageRequest.of(page, size);

        return this.questionRepository.findAll(pagination).map(
            (question) -> this.questionMapper.entityToResponse(question) 
        );
    }

    @Override
    public QuestionResponse getById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    @Transactional
    public QuestionResponse create(QuestionCreateRequest request) {
        Question question = this.questionMapper.requestToEntity(request);

        Question newQuestion = this.questionRepository.save(question);

        //Si el tipo de pregunta en CLOSED se agregar las opciones
        if (newQuestion.getType() == TypeQuestion.CLOSED) {    

            //Convertimos todas las opciones de request a entity y lo guardamos en una lista
            List<OptionQuestion> options = request.getOptionquestions().stream().map(
                (requestOptionalQuestion)  -> this.optionQuestionMapper.requestToEntity(newQuestion, requestOptionalQuestion)
            ).toList();

            //Guardamos las opciones de la pregunta
            List<OptionQuestion> optionsSaved = options.stream().map (
                (option) -> this.optionQuestionRepository.save(option)
            ).toList();

            newQuestion.setOptionquestions(optionsSaved);

        }

        return this.questionMapper.entityToResponse(newQuestion);
    }

    private Question modificate(Integer id, QuestionUpdateRequest request){
        Question question = this.find(id);

        Question questionUpdate = this.questionMapper.requestToEntity(request, question);

        Question questionUpdated = this.questionRepository.save(questionUpdate);

        return questionUpdated;
    }

    @Override
    @Transactional
    public QuestionResponse update(Integer id, QuestionUpdateRequest request) {

        return this.questionMapper.entityToResponse(modificate(id, request));
    }

    @Transactional
    public QuestionResponse update(Integer id, QuestionAndOptionsUpdateRequest request) {

        Question questionUpdated = this.modificate(id, Mapper.sourceToTarget(request, new QuestionUpdateRequest()));

        //Si el tipo de pregunta en CLOSED se agregar las opciones
        if (questionUpdated.getType() == TypeQuestion.CLOSED) {   

            //Almacenamos todas las opciones guardadas en la db 
            List<OptionQuestion> optionsSaved = request.getOptionquestions().stream().map(
                (optionsUpdateRequest) -> {

                    //Buscamos si existe la opcion
                    OptionQuestion option = this.findOption(optionsUpdateRequest.getId());

                    //Convertimos el request a una entidad
                    OptionQuestion optionUpdate = this.optionQuestionMapper.requestToEntity(optionsUpdateRequest, option);

                    //Actualizamos la opcion
                    OptionQuestion optionUpdated = this.optionQuestionRepository.save(optionUpdate);
                    
                    return optionUpdated;
                }
            ).toList();

            questionUpdated.setOptionquestions(optionsSaved);

        }

        return this.questionMapper.entityToResponse(questionUpdated);
    }

    @Override
    public void delete(Integer id) {
        
        Question question = this.find(id);

        this.questionRepository.delete(question);
    }

    private Question find(Integer id){
        return this.questionRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Question"));
    }

    private OptionQuestion findOption(Integer id){
        return this.optionQuestionRepository.findById(id).orElseThrow(() -> new IdNotFoundException("OptionQuestion"));
    }
    
}
