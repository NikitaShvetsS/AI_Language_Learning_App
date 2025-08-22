package org.example.service.crud;

import org.example.dto.request.UserRequestDTO;
import org.example.dto.response.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO dto);
    UserResponseDTO updateUser(UUID uuid, UserRequestDTO dto);
    UserResponseDTO getUserById(UUID uuid);
    List<UserResponseDTO> getAllUsers();
    void deleteUser(UUID uuid);

}
