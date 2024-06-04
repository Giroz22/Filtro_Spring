package com.riwi.filtro.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserUpdateRequest {
    @NotBlank(message = "El nombre es requerido")
    private String name;

    @NotBlank(message = "El correo es requerido")
    @Size(min=0, max = 100, message = "El correo tiene un maximo de 100 caracteres")
    @Email(message = "El correo debe tener un formato valido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    @Size(min=8, max = 255, message = "La contraseña debe ser minimo de 8 y maximo 255 caracteres")
    private String password;
    
    private Boolean active;
}
