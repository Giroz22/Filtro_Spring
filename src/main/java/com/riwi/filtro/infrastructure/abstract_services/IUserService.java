package com.riwi.filtro.infrastructure.abstract_services;

import com.riwi.filtro.api.dto.request.UserCreateRequest;
import com.riwi.filtro.api.dto.request.UserUpdateRequest;
import com.riwi.filtro.api.dto.response.UserResponse;

public interface IUserService extends IBaseService<UserCreateRequest, UserUpdateRequest, UserResponse, Integer>{
    
}
