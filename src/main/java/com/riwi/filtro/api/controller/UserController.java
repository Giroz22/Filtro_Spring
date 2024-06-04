package com.riwi.filtro.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.filtro.api.dto.errors.ErrorResponse;
import com.riwi.filtro.api.dto.errors.ErrorsResponse;
import com.riwi.filtro.api.dto.request.UserCreateRequest;
import com.riwi.filtro.api.dto.request.UserUpdateRequest;
import com.riwi.filtro.api.dto.response.UserResponse;
import com.riwi.filtro.infrastructure.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @Operation(summary = "Lista todos los usuarios y los devuelve en forma de paginacion")
    @GetMapping("")
    public ResponseEntity<Page<UserResponse>> getAll(
        @RequestParam(defaultValue = "1") int page, 
        @RequestParam(defaultValue = "3") int size
    ) {
        return ResponseEntity.ok().body(this.userService.getAll(page-1, size));
    }

    @Operation(summary = "Obtiene un usuario en base a un id")
    @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", 
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.userService.getById(id));
    }
    
    @Operation(summary = "Permite crear un usuario")
    @ApiResponse(responseCode = "400", description = "Cuando alguno de los atributos no sean validos",      
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorsResponse.class))
        }
    )
    @PostMapping("")
    public ResponseEntity<UserResponse> create(@Validated @RequestBody UserCreateRequest request) {
        return ResponseEntity.ok().body(this.userService.create(request));
    }
    
    @Operation(summary = "Permite actualizar un usuario")
    @ApiResponse(responseCode = "400", description = "Cuando alguno de los atributos no sean validos",      
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorsResponse.class))
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Integer id,@Validated @RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok().body(this.userService.update(id, request));
    }
}
