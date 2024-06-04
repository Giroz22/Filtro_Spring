package com.riwi.filtro.api.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SurveyResponse {
    private Integer id;
    private String title;
    private String description;
    private LocalDate creationDate;
    private Boolean active;
    private List<QuestionResponse> questions;

}
