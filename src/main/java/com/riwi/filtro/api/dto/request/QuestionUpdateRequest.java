package com.riwi.filtro.api.dto.request;

import java.util.List;

import com.riwi.filtro.util.enums.TypeQuestion;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class QuestionUpdateRequest {
    @NotBlank(message = "El texto es requerido")
    private String text;

    @NotBlank(message = "El tipo de question es requerido")
    private TypeQuestion type;
    
    private Boolean active;

    private List<OptionQuestionCreateRequest> optionquestions;
}
