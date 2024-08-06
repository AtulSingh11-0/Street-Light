package ashis.com.backend.Street_light.Area.Service;

import ashis.com.backend.Street_light.Area.Model.AreaModel;
import ashis.com.backend.Street_light.Area.Repository.AreaRepository;
import ashis.com.backend.Street_light.exceptions.AreaCodeAlreadyExistsException;
import ashis.com.backend.Street_light.exceptions.AreaNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AreaService {
    @Autowired
    private AreaRepository areaRepository;

    @Transactional(readOnly = true)
    public List<AreaModel> getAllArea() {
        List<AreaModel> areas = areaRepository.findAll();
        if (areas.isEmpty()) {
            throw new AreaNotFoundException("No Area Found");
        }
        return areas;
    }

    @Transactional
    public AreaModel updateArea(AreaModel model, String id) {
        AreaModel areaModel = areaRepository.findByAreaCode(id)
            .orElseThrow(() -> new AreaNotFoundException("No Area Found"));

        // Check if the new area code already exists for another area
        Optional<AreaModel> existingAreaWithCode = areaRepository.findByAreaCode(model.getAreaCode());
        if (existingAreaWithCode.isPresent() && !existingAreaWithCode.get().getId().equals(areaModel.getId())) {
            throw new AreaCodeAlreadyExistsException("Area code already exists for another area");
        }

        areaModel.setAreaCode(model.getAreaCode());
        areaModel.setAreaName(model.getAreaName());

        return areaRepository.save(areaModel);
    }

}
