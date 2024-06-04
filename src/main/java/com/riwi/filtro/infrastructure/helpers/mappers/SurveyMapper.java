package com.riwi.filtro.infrastructure.helpers.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.riwi.filtro.api.dto.request.SurveyCreatedRequest;
import com.riwi.filtro.api.dto.response.SurveyResponse;
import com.riwi.filtro.domain.entitties.Survey;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SurveyMapper implements IMapperBase<Survey, SurveyCreatedRequest, SurveyResponse>{

    @Autowired
    private final QuestionMapper questionMapper;

    @Override
    public Survey requestToEntity(SurveyCreatedRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'requestToEntity'");
    }

    @Override
    public SurveyResponse entityToResponse(Survey entity) {
        return Mapper.sourceToTarget(entity, 
            SurveyResponse.builder()
            .questions(
                entity.getQuestions().stream().map(
                    (questions)-> questionMapper.entityToResponse(questions)
                ).toList()
            )
            .build()
        );
    }
    
}
