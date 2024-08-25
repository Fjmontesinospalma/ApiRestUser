package cl.smartjob.user.service;


import cl.smartjob.user.dto.PhoneDTO;
import cl.smartjob.user.dto.UserGetResponseDTO;
import cl.smartjob.user.dto.UserRequestDTO;
import cl.smartjob.user.dto.UserResponseDTO;
import cl.smartjob.user.exception.CustomException;
import cl.smartjob.user.model.Phone;
import cl.smartjob.user.model.User;
import cl.smartjob.user.repository.UserRepository;
import cl.smartjob.user.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private final Util util;

    public UserServiceImpl(UserRepository userRepository, Util util) {
        this.userRepository = userRepository;
        this.util = util;
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public UserResponseDTO save(UserRequestDTO userRequestDTO) {
        if (emailExists(userRequestDTO.getEmail())) {
            throw new CustomException("Email ya esta registrado.");
        }

        util.validatePassword(userRequestDTO.getPassword());

        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(UUID.randomUUID().toString());
        user.setActive(true);

        List<Phone> phones = userRequestDTO.getPhones().stream()
                .map(phoneDTO -> {
                    Phone phone = new Phone(phoneDTO.getNumber(), phoneDTO.getCitycode(), phoneDTO.getContrycode());
                    phone.setUser(user);
                    return phone;
                }).collect(Collectors.toList());

        user.setPhones(phones);

        userRepository.save(user);

        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setCreated(user.getCreated());
        response.setModified(user.getModified());
        response.setLastLogin(user.getLastLogin());
        response.setToken(user.getToken());
        response.setActive(user.isActive());

        return response;
    }

    public List<UserGetResponseDTO> getAllUsers() {

        return userRepository.findAll().stream()
                .map(this::dataToGetResponseDTO)
                .collect(Collectors.toList());
    }

    public UserGetResponseDTO getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("Usuario no encontrado"));
        return dataToGetResponseDTO(user);
    }

    public UserResponseDTO update(UUID id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("Usuario no encontrado"));

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setModified(LocalDateTime.now());

        user.getPhones().clear();

        userRequestDTO.getPhones().forEach(phoneDTO -> {
            Phone phone = new Phone(phoneDTO.getNumber(), phoneDTO.getCitycode(), phoneDTO.getContrycode());
            phone.setUser(user);
            user.getPhones().add(phone);
        });

        User updatedUser = userRepository.save(user);

        return toUserResponseDTO(updatedUser);
    }

    public void deleteUser(UUID id) {

        if (!userRepository.existsById(id)) {
            throw new CustomException("Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }



    private UserGetResponseDTO dataToGetResponseDTO(User user) {

        UserGetResponseDTO response = new UserGetResponseDTO();
        response.setId(user.getId());
        response.setCreated(user.getCreated());
        response.setModified(user.getModified());
        response.setLastLogin(user.getLastLogin());
        response.setToken(user.getToken());
        response.setActive(user.isActive());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhones(user.getPhones().stream()
                .map(phone -> new PhoneDTO(phone.getNumber(), phone.getCitycode(), phone.getContrycode()))
                .collect(Collectors.toList()));

        return response;

    }
    private UserResponseDTO toUserResponseDTO(User user) {

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setCreated(user.getCreated());
        responseDTO.setModified(user.getModified());
        responseDTO.setLastLogin(user.getLastLogin());
        responseDTO.setToken(user.getToken());
        responseDTO.setActive(user.isActive());


        /*
        responseDTO.setPhones(user.getPhones().stream()
                .map(phone -> new PhoneDTO(phone.getNumber(), phone.getCitycode(), phone.getContrycode()))
                .collect(Collectors.toList()));
        */
        return responseDTO;
    }
}
