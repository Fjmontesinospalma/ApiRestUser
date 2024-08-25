package cl.smartjob.user.service;


import cl.smartjob.user.dto.UserGetResponseDTO;
import cl.smartjob.user.dto.UserRequestDTO;
import cl.smartjob.user.dto.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserGetResponseDTO getUserById(UUID id);

    UserResponseDTO update(UUID id, UserRequestDTO userRequestDTO);

    UserResponseDTO save(UserRequestDTO userRequestDTO);

    List<UserGetResponseDTO> getAllUsers();

    void deleteUser(UUID id);
}
