package com.learn.blog.service;

import com.learn.blog.model.User;
import com.learn.blog.payloads.UserDto;

import java.util.List;

public interface UserService {

    public UserDto createUser(UserDto user);

    public UserDto updateUser(UserDto user, Integer userId);

    public void deleteUser(Integer userId);
    public void deleteAllUser();

    public UserDto  getUserById(Integer userId);

    public List<UserDto> getAllUsers();





}
