package cl.smartjob.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The type Phone dto.
 */
@Data
@AllArgsConstructor
public class PhoneDTO {
    private String number;
    private String citycode;
    private String contrycode;
}
