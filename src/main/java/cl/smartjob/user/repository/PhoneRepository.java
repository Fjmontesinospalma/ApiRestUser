package cl.smartjob.user.repository;

import cl.smartjob.user.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * The interface Phone repository.
 */
public interface PhoneRepository extends JpaRepository<Phone, UUID> {
}
