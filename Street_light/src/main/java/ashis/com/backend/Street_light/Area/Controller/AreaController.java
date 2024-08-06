package ashis.com.backend.Street_light.Area.Controller;

import ashis.com.backend.Street_light.Area.Model.AreaModel;
import ashis.com.backend.Street_light.Area.Transformer.Model.TransformerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import ashis.com.backend.Street_light.Area.Service.AreaService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/gov/original/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/all")
    public ResponseEntity< Map <String, Object> > AreaDetails(){
      List< AreaModel > areas = areaService.getAllArea();

      if(areas.isEmpty()){
        return ResponseEntity.notFound().build();
      } else {
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "status_code", HttpStatus.OK,
            "message", "All Area Details",
            "data", areas
        ));
      }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map <String, Object>> updateArea(
            @PathVariable("id") String id,
            @RequestBody AreaModel model
    ) {
      AreaModel areaModel = areaService.updateArea(model, id);

      if(areaModel == null){
        return ResponseEntity.notFound().build();
      } else {
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "status_code", HttpStatus.OK,
            "message", "Area Updated",
            "data", areaModel
        ));
      }
    }
}
