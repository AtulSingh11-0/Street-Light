package ashis.com.backend.Street_light.Area.Transformer.Street_light.Service;

import ashis.com.backend.Street_light.Area.Transformer.Street_light.Repository.Street_lightRepository;
import ashis.com.backend.Street_light.exceptions.StreetLightNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ashis.com.backend.Street_light.Area.Transformer.Street_light.Model.Street_lightModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class Street_lightService {

    @Autowired
    private Street_lightRepository street_lightRepository;

    @Transactional (readOnly = true)
    public List<Street_lightModel> getAllStreet_lights(){
      return street_lightRepository.findAll();
    }

    public Street_lightModel getStreet_lightById(String id){
        Optional<Street_lightModel> model = street_lightRepository.findByStreetLightCode(id);

        if (model.isPresent()){
            return model.get();
        } else
            throw new StreetLightNotFoundException("Street_light not found");
    }

    public Street_lightModel updateStreetLight ( Street_lightModel street_lightModel ){
        Street_lightModel light = getStreet_lightById(street_lightModel.getStreetLightCode());
        light.setStreet_light_status(street_lightModel.getStreet_light_status());
        return street_lightRepository.save(light);
    }
}
