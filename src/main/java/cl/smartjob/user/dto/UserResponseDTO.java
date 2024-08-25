package cl.smartjob.user.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The type User response dto.
 */
@Data
public class UserResponseDTO {
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;
}
