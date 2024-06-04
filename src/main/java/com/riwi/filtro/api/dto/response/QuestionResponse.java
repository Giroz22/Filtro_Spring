package com.riwi.filtro.api.dto.response;

import java.util.List;

import com.riwi.filtro.util.enums.TypeQuestion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class QuestionResponse {
    private Integer id;
    private String text;
    private TypeQuestion type;
    private Boolean active;
    private List<OptionQuestionResponse> options;
}
