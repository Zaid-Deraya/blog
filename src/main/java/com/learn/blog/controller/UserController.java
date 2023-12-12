package com.learn.blog.controller;

import com.learn.blog.payloads.UserDto;
import com.learn.blog.service.UserService;
import com.learn.blog.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("createUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

        UserDto userCreated = this.userService.createUser(userDto);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = this.userService.getAllUsers();

        return new ResponseEntity<>(allUsers, HttpStatus.OK);

    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer id) {
        UserDto user = this.userService.getUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);


    }

    @PutMapping("updateUser/{userId}")
    public ResponseEntity<UserDto> upadateUser(@Valid @PathVariable("userId") int id, @RequestBody UserDto userDto) {
        UserDto updateDto = this.userService.updateUser(userDto, id);

        return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);

    }




    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id){
        this.userService.deleteUser(id);
        return ResponseEntity.ok(Map.of("message","User deleted!"));
    }

    @DeleteMapping("deleteAllUsers")
    public ResponseEntity<?> deleteAllUsers(){
        this.userService.deleteAllUser();
        return ResponseEntity.ok(Map.of("message","All users deleted!"));
    }





//    @GetMapping("/map")
//    public ResponseEntity<HashMap<String, Object>> createUser() {
//
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("name", "Zaid");
//        map.put("email", "z@h.oi");
//        map.put("password", "Audir8");
//        return ResponseEntity.ok(map);
//    }


}
