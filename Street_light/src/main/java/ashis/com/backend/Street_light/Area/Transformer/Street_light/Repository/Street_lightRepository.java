package ashis.com.backend.Street_light.Area.Transformer.Street_light.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ashis.com.backend.Street_light.Area.Transformer.Street_light.Model.Street_lightModel;

import java.util.Optional;

@Repository
public interface Street_lightRepository extends JpaRepository<Street_lightModel, Long> {
    Optional<Street_lightModel> findByStreetLightCode(String id);
}
