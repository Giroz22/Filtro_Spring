package com.riwi.filtro.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserUpdateRequest {
    private String name;
    private String email;
    private String password;
    private Boolean active;
}
