package ashis.com.backend.Street_light.Area.Transformer.Model;

import ashis.com.backend.Street_light.Area.Model.AreaModel;
import ashis.com.backend.Street_light.Area.Transformer.Street_light.Model.Street_lightModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transformer_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransformerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transformer_id")
    private Long id;
    private String transformerName;
    private String transformerCode;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private AreaModel areaModel;

    @OneToMany(mappedBy = "transformerModel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Street_lightModel> street_lightModelDetails=new ArrayList<>();
}
