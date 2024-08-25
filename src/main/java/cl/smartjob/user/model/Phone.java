package cl.smartjob.user.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * The type Phone.
 */
@Entity
@Data
@Table(name = "phone")
public class Phone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String number;
    private String citycode;
    private String contrycode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Instantiates a new Phone.
     */
    public Phone() {
    }

    /**
     * Instantiates a new Phone.
     *
     * @param number     the number
     * @param citycode   the citycode
     * @param contrycode the contrycode
     */
    public Phone(String number, String citycode, String contrycode) {
        this.number = number;
        this.citycode = citycode;
        this.contrycode = contrycode;
    }

}

