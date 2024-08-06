package ashis.com.backend.Street_light.Area.Transformer.Service;

import ashis.com.backend.Street_light.Area.Model.AreaModel;
import ashis.com.backend.Street_light.Area.Repository.AreaRepository;
import ashis.com.backend.Street_light.Area.Transformer.Repository.TransformerRepository;
import ashis.com.backend.Street_light.exceptions.AreaNotFoundException;
import ashis.com.backend.Street_light.exceptions.TransformerCodeAlreadyExistsException;
import ashis.com.backend.Street_light.exceptions.TransformerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ashis.com.backend.Street_light.Area.Transformer.Model.TransformerModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransformerService {

    @Autowired
    private TransformerRepository transformerRepository;
    @Autowired
    private AreaRepository areaRepository;

    public TransformerModel getTransformerById(String id){
        Optional<TransformerModel> model = transformerRepository.findByTransformerCode(id);
        if (model.isPresent()){
            return model.get();
        } else
            throw new TransformerNotFoundException("Transformer not found");
    }

    @Transactional
    public TransformerModel UpdateTransformer(
        TransformerModel transformerModel,
        String id
    ){
        TransformerModel model = transformerRepository.findByTransformerCode(id)
            .orElseThrow(() -> new TransformerNotFoundException("Transformer not found"));

        Optional<TransformerModel> existingTransformerWithCode = transformerRepository.findByTransformerCode(transformerModel.getTransformerCode());
        if (existingTransformerWithCode.isPresent() && !existingTransformerWithCode.get().getId().equals(model.getId())) {
            throw new TransformerCodeAlreadyExistsException("Transformer code already exists for another transformer");
        }

        Optional< AreaModel > areaModel = areaRepository.findByAreaCode(transformerModel.getAreaModel().getAreaCode());
        if(areaModel.isEmpty()){
            throw new AreaNotFoundException("Area not found");
        }
        model.setTransformerCode(transformerModel.getTransformerCode());
        model.setTransformerName(transformerModel.getTransformerName());
        model.setAreaModel(transformerModel.getAreaModel());

        return transformerRepository.save(model);
    }

}
