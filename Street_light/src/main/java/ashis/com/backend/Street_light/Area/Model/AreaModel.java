package ashis.com.backend.Street_light.Area.Model;

import ashis.com.backend.Street_light.Area.Transformer.Model.TransformerModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "area_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long id;
    private String areaName;
    private String areaCode;

    @OneToMany(mappedBy = "areaModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TransformerModel> transformerModelDetails=new ArrayList<>();
}
