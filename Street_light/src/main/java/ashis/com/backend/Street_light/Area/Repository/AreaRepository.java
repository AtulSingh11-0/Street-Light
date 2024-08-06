package ashis.com.backend.Street_light.Area.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ashis.com.backend.Street_light.Area.Model.AreaModel;

import java.util.Optional;

@Repository
public interface AreaRepository extends JpaRepository<AreaModel, Long> {
    Optional<AreaModel> findByAreaCode(String id);
}
