package cl.smartjob.user.controller;

import cl.smartjob.user.dto.UserGetResponseDTO;
import cl.smartjob.user.dto.UserRequestDTO;
import cl.smartjob.user.dto.UserResponseDTO;
import cl.smartjob.user.exception.CustomException;
import cl.smartjob.user.exception.CustomResponse;
import cl.smartjob.user.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/usuario")
public class UserController {

    private final UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Register user response entity.
     *
     * @param usuarioRequestDTO the usuario request dto
     * @return the response entity
     */
    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserRequestDTO usuarioRequestDTO) {
        try {
            UserResponseDTO userResponseDTO = userService.save(usuarioRequestDTO);
            return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
        } catch (CustomException e) {
            CustomResponse CustomResponse = new CustomResponse(e.getMessage());
            return new ResponseEntity<>(CustomResponse, HttpStatus.CONFLICT);
        } catch (Exception ex) {
            CustomResponse CustomResponse = new CustomResponse("Error al registrar usuario");
            return new ResponseEntity<>(CustomResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserGetResponseDTO>> getAllUsers() {
        List<UserGetResponseDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        try {
            UserGetResponseDTO user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(new CustomResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update response entity.
     *
     * @param id             the id
     * @param userRequestDTO the user request dto
     * @return the response entity
     */
    @Transactional
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable UUID id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO userResponseDTO = userService.update(id, userRequestDTO);
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(new CustomResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            userService.deleteUser(id);
            CustomResponse response = new CustomResponse("Usuario eliminado exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(new CustomResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
