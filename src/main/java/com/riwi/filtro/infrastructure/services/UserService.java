package com.riwi.filtro.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.filtro.api.dto.request.UserCreateRequest;
import com.riwi.filtro.api.dto.request.UserUpdateRequest;
import com.riwi.filtro.api.dto.response.UserResponse;
import com.riwi.filtro.domain.entitties.User;
import com.riwi.filtro.domain.repositories.UserRepository;
import com.riwi.filtro.infrastructure.abstract_services.IUserService;
import com.riwi.filtro.infrastructure.helpers.mappers.UserMapper;
import com.riwi.filtro.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    @Override
    public Page<UserResponse> getAll(int page, int size) {
        if (page<0) page=0;
        PageRequest pagination = PageRequest.of(page, size);

        return this.userRepository.findAll(pagination).map(
            (user) -> this.userMapper.entityToResponse(user)
        );
    }

    @Override
    public UserResponse getById(Integer id) {
        User user = this.find(id);

        return this.userMapper.entityToResponse(user);
    }

    @Override
    public UserResponse create(UserCreateRequest request) {
        User user = this.userMapper.requestToEntity(request);

        User newUser = this.userRepository.save(user);

        return this.userMapper.entityToResponse(newUser);
    }

    @Override
    public UserResponse update(Integer id, UserUpdateRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
    private User find(Integer id){
        return this.userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("User"));
    }
    
}
