package com.riwi.filtro.infrastructure.abstract_services;

import com.riwi.filtro.api.dto.request.QuestionCreateRequest;
import com.riwi.filtro.api.dto.request.QuestionUpdateRequest;
import com.riwi.filtro.api.dto.response.QuestionResponse;

public interface IQuestionService extends IBaseService<QuestionCreateRequest, QuestionUpdateRequest, QuestionResponse, Integer>{
    
}
