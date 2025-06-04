package com.example.demo.controller;

import com.example.demo.mapping.UserMapping;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "My API", description = "this is my first api")
public class UserController {
    private final UserService userService;
    private final UserMapping userMapping;

    @GetMapping("/{email}")
    @Operation(summary = "Find user by email", description = "Finds a user record from the database by their unique email.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User found successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found with the given ID",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class, example = "{\"id\": 0, \"email\": \"\", \"password\": \"\"}")
                    )
            )
    })
    public UserDto findByEmail(@PathVariable String email) {
        return userMapping.toDto(userService.findByEmail(email));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto saveUser(@Valid @RequestBody UserDto req) {
        UserEntity entity = userMapping.toEntity(req);
        UserEntity result = userService.newUser(entity);
        return userMapping.toDto(result);
    }

    @PutMapping
    public UserDto editUser(@Valid @RequestBody UserDto req) {
        UserEntity entity = userMapping.toEntity(req);
        UserEntity result = userService.updateUser(entity);
        return userMapping.toDto(result);
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
