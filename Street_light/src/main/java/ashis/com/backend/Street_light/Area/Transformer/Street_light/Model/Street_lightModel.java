package ashis.com.backend.Street_light.Area.Transformer.Street_light.Model;

import ashis.com.backend.Street_light.Area.Transformer.Model.TransformerModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "street_light_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Street_lightModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String streetLight_Name;
    private String streetLightCode;
    private Boolean street_light_status;
    private String power_consumption;
    private String Electricity_consumption;
    private String voltage;

    @ManyToOne
    @JoinColumn(name = "transformer_id")
    private TransformerModel transformerModel;
}
