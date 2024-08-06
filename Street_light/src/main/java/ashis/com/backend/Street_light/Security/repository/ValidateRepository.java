package ashis.com.backend.Street_light.Security.repository;

import ashis.com.backend.Street_light.Security.model.Validate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidateRepository extends JpaRepository<Validate, Long> {
    Optional<Validate> findByJobId (String jodId);
}
