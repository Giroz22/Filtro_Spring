package com.riwi.filtro.infrastructure.helpers.mappers;

import org.springframework.stereotype.Component;

import com.riwi.filtro.api.dto.request.QuestionCreateRequest;
import com.riwi.filtro.api.dto.response.QuestionResponse;
import com.riwi.filtro.domain.entitties.Question;

@Component
public class QuestionMapper implements IMapperBase<Question, QuestionCreateRequest, QuestionResponse>{

    @Override
    public Question requestToEntity(QuestionCreateRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'requestToEntity'");
    }

    @Override
    public QuestionResponse entityToResponse(Question entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'entityToResponse'");
    }
    
}
