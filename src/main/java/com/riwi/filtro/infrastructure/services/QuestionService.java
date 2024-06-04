package com.riwi.filtro.infrastructure.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.filtro.api.dto.request.QuestionCreateRequest;
import com.riwi.filtro.api.dto.request.QuestionUpdateRequest;
import com.riwi.filtro.api.dto.response.OptionQuestionResponse;
import com.riwi.filtro.api.dto.response.QuestionResponse;
import com.riwi.filtro.domain.entitties.OptionQuestion;
import com.riwi.filtro.domain.entitties.Question;
import com.riwi.filtro.domain.repositories.OptionQuestionRepository;
import com.riwi.filtro.domain.repositories.QuestionRepository;

import lombok.AllArgsConstructor;
import com.riwi.filtro.infrastructure.abstract_services.IQuestionService;
import com.riwi.filtro.infrastructure.helpers.mappers.OptionQuestionMapper;
import com.riwi.filtro.infrastructure.helpers.mappers.QuestionMapper;
import com.riwi.filtro.util.enums.TypeQuestion;
import com.riwi.filtro.util.exceptions.IdNotFoundException;

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

    @Override
    public QuestionResponse update(Integer id, QuestionUpdateRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private Question find(Integer id){
        return this.questionRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Question"));
    }
    
}
