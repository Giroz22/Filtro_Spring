package com.riwi.filtro.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.filtro.api.dto.request.SurveyCreatedRequest;
import com.riwi.filtro.api.dto.request.SurveyUpdateRequest;
import com.riwi.filtro.api.dto.response.SurveyResponse;
import com.riwi.filtro.domain.entitties.Survey;
import com.riwi.filtro.domain.repositories.SurveyRepository;
import com.riwi.filtro.infrastructure.abstract_services.ISurveyService;
import com.riwi.filtro.infrastructure.helpers.mappers.SurveyMapper;
import com.riwi.filtro.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurveyService implements ISurveyService{

    @Autowired
    private final SurveyRepository surveyRepository;

    @Autowired
    private final SurveyMapper surveyMapper;

    @Override
    public Page<SurveyResponse> getAll(int page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public SurveyResponse getById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public SurveyResponse create(SurveyCreatedRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public SurveyResponse update(Integer id, SurveyUpdateRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
    private Survey find(Integer id){
        return this.surveyRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Survey"));
    }

}
