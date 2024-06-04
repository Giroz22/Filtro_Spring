package com.riwi.filtro.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.filtro.api.dto.request.QuestionCreateRequest;
import com.riwi.filtro.api.dto.request.QuestionUpdateRequest;
import com.riwi.filtro.api.dto.response.QuestionResponse;
import com.riwi.filtro.domain.entitties.Question;
import com.riwi.filtro.domain.repositories.QuestionRepository;

import lombok.AllArgsConstructor;
import com.riwi.filtro.infrastructure.abstract_services.IQuestionService;
import com.riwi.filtro.infrastructure.helpers.mappers.QuestionMapper;
import com.riwi.filtro.util.exceptions.IdNotFoundException;

@Service
@AllArgsConstructor
public class QuestionService implements IQuestionService{

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final QuestionMapper questionMapper;

    @Override
    public Page<QuestionResponse> getAll(int page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public QuestionResponse getById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public QuestionResponse create(QuestionCreateRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
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
