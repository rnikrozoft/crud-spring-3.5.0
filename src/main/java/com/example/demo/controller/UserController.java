package com.example.demo.controller;

import com.example.demo.mapping.UserMapping;
import com.example.demo.model.dto.UserDto;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapping userMapping;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto saveUser(@Valid @RequestBody UserDto req) {
        return  null;
    }

    @GetMapping("/{email}")
    public UserDto findByEmail(@PathVariable String email) {
        return userMapping.toDto(userService.findByEmail(email));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userMapping.toDtoList(userService.findAllUsers());
    }
}
