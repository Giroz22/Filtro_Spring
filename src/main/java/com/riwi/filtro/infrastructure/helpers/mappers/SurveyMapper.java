package com.riwi.filtro.infrastructure.helpers.mappers;

import org.springframework.stereotype.Component;

import com.riwi.filtro.api.dto.request.SurveyCreatedRequest;
import com.riwi.filtro.api.dto.response.SurveyResponse;
import com.riwi.filtro.domain.entitties.Survey;

@Component
public class SurveyMapper implements IMapperBase<Survey, SurveyCreatedRequest, SurveyResponse>{

    @Override
    public Survey requestToEntity(SurveyCreatedRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'requestToEntity'");
    }

    @Override
    public SurveyResponse entityToResponse(Survey entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'entityToResponse'");
    }
    
}
