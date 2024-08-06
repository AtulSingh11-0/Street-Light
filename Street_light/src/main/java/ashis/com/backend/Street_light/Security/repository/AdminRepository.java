package ashis.com.backend.Street_light.Security.repository;

import ashis.com.backend.Street_light.Security.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByJobId (String jobId);
}
