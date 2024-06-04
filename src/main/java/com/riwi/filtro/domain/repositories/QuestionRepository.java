package com.riwi.filtro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.filtro.domain.entitties.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
    
}
