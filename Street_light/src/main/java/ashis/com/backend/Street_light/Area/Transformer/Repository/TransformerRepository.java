package ashis.com.backend.Street_light.Area.Transformer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ashis.com.backend.Street_light.Area.Transformer.Model.TransformerModel;

import java.util.Optional;

@Repository
public interface TransformerRepository extends JpaRepository<TransformerModel, Long> {
    Optional<TransformerModel> findByTransformerCode(String id);
}
