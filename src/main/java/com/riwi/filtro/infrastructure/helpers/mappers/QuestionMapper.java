package com.riwi.filtro.infrastructure.helpers.mappers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.riwi.filtro.api.dto.request.QuestionAndOptionsUpdateRequest;
import com.riwi.filtro.api.dto.request.QuestionCreateRequest;
import com.riwi.filtro.api.dto.request.QuestionUpdateRequest;
import com.riwi.filtro.api.dto.response.QuestionResponse;
import com.riwi.filtro.domain.entitties.OptionQuestion;
import com.riwi.filtro.domain.entitties.Question;
import com.riwi.filtro.util.enums.TypeQuestion;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class QuestionMapper implements IMapperBase<Question, QuestionCreateRequest, QuestionResponse>{

    @Autowired
    private final OptionQuestionMapper optionQuestionMapper;

    @Override
    public Question requestToEntity(QuestionCreateRequest request) {
        Question question = Mapper.sourceToTarget(request, new Question());

        question.setActive(true);

        //Si el tipo de pregunta es cerrada le agregamos un array opciones vacio
        if (request.getType() == TypeQuestion.CLOSED) 
            question.setOptionquestions(new ArrayList<OptionQuestion>());

        return question;
    }

    public Question requestToEntity(QuestionUpdateRequest request, Question entity) {
        return Mapper.sourceToTarget(request, entity);
    }

    public Question requestToEntity(QuestionAndOptionsUpdateRequest request, Question entity) {
        Question question = Mapper.sourceToTarget(request, entity);

        question.setOptionquestions(
            request.getOptionquestions().stream().map(
                (optionRequest) -> this.optionQuestionMapper.requestToEntity(optionRequest, new OptionQuestion())
            ).toList()
        );

        return question;
    }

    @Override
    public QuestionResponse entityToResponse(Question entity) {
        QuestionResponse response = Mapper.sourceToTarget(entity, 
            QuestionResponse.builder()
            .options(new ArrayList<>())
            .build()
        );

        //Si la pregunta es de tipo cerrada, Convertimos la lista de opciones en una lista de respuestas
        if (entity.getType() == TypeQuestion.CLOSED) {
            response.setOptions(
                entity.getOptionquestions().stream().map(
                    (option) -> this.optionQuestionMapper.entityToResponse(option)
                ).toList()
            );
        }
    
        return response;
    }
    
}
