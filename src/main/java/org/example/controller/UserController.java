package org.example.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.UserRequestDTO;
import org.example.dto.response.UserResponseDTO;
import org.example.service.crud.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "APIs for managing users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO){
        log.info("Running method create user");
        return userService.createUser(userRequestDTO);
    }

    @PutMapping("/update/{uuid}")
    public UserResponseDTO updateUser(@PathVariable("uuid") UUID uuid, @RequestBody UserRequestDTO dto){
        log.info("Running method update user");
        return userService.updateUser(uuid, dto);
    }

    @GetMapping("/all")
    public List<UserResponseDTO> getAll(){
        log.info("Running method get all users");
        return userService.getAllUsers();
    }

    @GetMapping("/id/{uuid}")
    public UserResponseDTO getById(@PathVariable("uuid") UUID uuid){
        log.info("Running method get user by id");
        return userService.getUserById(uuid);
    }

    @DeleteMapping("/id/{uuid}")
    public ResponseEntity<Void> deleteById(@PathVariable("uuid") UUID uuid){
        log.info("Running method delete user by id");
        userService.deleteUser(uuid);
        return ResponseEntity.ok().build();
    }

}
