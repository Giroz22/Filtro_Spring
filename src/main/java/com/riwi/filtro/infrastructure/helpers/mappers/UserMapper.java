package com.riwi.filtro.infrastructure.helpers.mappers;

import org.springframework.stereotype.Component;

import com.riwi.filtro.api.dto.request.UserCreateRequest;
import com.riwi.filtro.api.dto.response.UserResponse;
import com.riwi.filtro.domain.entitties.User;

@Component
public class UserMapper implements IMapperBase<User, UserCreateRequest, UserResponse>{

    @Override
    public User requestToEntity(UserCreateRequest request) {
        User user = Mapper.sourceToTarget(request, 
            User.builder()
            .active(true)
            .build()
        );

        return user;
    }

    public User requestToEntity(UserCreateRequest request, User entity) {
        return Mapper.sourceToTarget(request, entity);
    }

    @Override
    public UserResponse entityToResponse(User entity) {
        return Mapper.sourceToTarget(entity, new UserResponse());
    }   
}
