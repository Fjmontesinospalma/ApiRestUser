package cl.smartjob.user.service;


import cl.smartjob.user.dto.UserGetResponseDTO;
import cl.smartjob.user.dto.UserRequestDTO;
import cl.smartjob.user.dto.UserResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    UserGetResponseDTO getUserById(UUID id);

    /**
     * Update user response dto.
     *
     * @param id             the id
     * @param userRequestDTO the user request dto
     * @return the user response dto
     */
    UserResponseDTO update(UUID id, UserRequestDTO userRequestDTO);

    /**
     * Save user response dto.
     *
     * @param userRequestDTO the user request dto
     * @return the user response dto
     */
    UserResponseDTO save(UserRequestDTO userRequestDTO);

    /**
     * Gets all users.
     *
     * @return the all users
     */
    List<UserGetResponseDTO> getAllUsers();

    /**
     * Delete user.
     *
     * @param id the id
     */
    void deleteUser(UUID id);
}
