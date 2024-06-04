package com.riwi.filtro.infrastructure.abstract_services;

import com.riwi.filtro.api.dto.request.SurveyCreatedRequest;
import com.riwi.filtro.api.dto.request.SurveyUpdateRequest;
import com.riwi.filtro.api.dto.response.SurveyResponse;

public interface ISurveyService extends IBaseService<SurveyCreatedRequest, SurveyUpdateRequest, SurveyResponse,  Integer>{
    
}
