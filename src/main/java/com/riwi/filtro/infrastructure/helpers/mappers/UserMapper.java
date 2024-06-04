package com.riwi.filtro.infrastructure.helpers.mappers;

import org.springframework.stereotype.Component;

import com.riwi.filtro.api.dto.request.UserCreateRequest;
import com.riwi.filtro.api.dto.response.UserResponse;
import com.riwi.filtro.domain.entitties.User;

@Component
public class UserMapper implements IMapperBase<User, UserCreateRequest, UserResponse>{

    @Override
    public User requestToEntity(UserCreateRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'requestToEntity'");
    }

    @Override
    public UserResponse entityToResponse(User entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'entityToResponse'");
    }
    
}
