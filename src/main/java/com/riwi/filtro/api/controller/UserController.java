package com.riwi.filtro.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.filtro.api.dto.response.UserResponse;
import com.riwi.filtro.infrastructure.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    
    
}
