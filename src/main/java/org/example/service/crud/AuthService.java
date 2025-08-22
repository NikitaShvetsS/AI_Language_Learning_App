package org.example.service.crud;

import org.example.dto.request.AuthRequestDTO;
import org.example.dto.request.RegisterRequestDTO;
import org.example.dto.response.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO login(AuthRequestDTO request);
    AuthResponseDTO register(RegisterRequestDTO request);

}
