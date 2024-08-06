package ashis.com.backend.Street_light.Area.Transformer.Street_light.Controller;

import ashis.com.backend.Street_light.Area.Transformer.Street_light.Model.Street_lightModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ashis.com.backend.Street_light.Area.Transformer.Street_light.Service.Street_lightService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gov/original/area/transformer/street-light")
public class Street_lightController {

    @Autowired
    private Street_lightService street_lightService;

    @GetMapping("/all")
    public ResponseEntity< Map<String, Object> > Street_lightDetails(){
        List< Street_lightModel > allStreetLights = street_lightService.getAllStreet_lights();

        if( ! allStreetLights.isEmpty() )
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "status_code", HttpStatus.OK,
                "message", "All Street Light Details",
                "data", allStreetLights
            ));
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStreet_lightById(
        @PathVariable("id") String id
    ){
        Street_lightModel street_lightModel = street_lightService.getStreet_lightById(id);
        String message = "Street Light " + id + " Details";

        if( street_lightModel != null )
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "status_code", HttpStatus.OK,
                "message", message,
                "data", street_lightModel
            ));
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> handleUpdateStreetLight (
        @PathVariable("id") String id,
        @RequestBody Street_lightModel model
    ){
        Street_lightModel updatedStreetLight = street_lightService.updateStreetLight(model);
        String message = "Street Light " + id + " Updated";

        if( updatedStreetLight != null )
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "status_code", HttpStatus.OK,
                "message", message,
                "data", updatedStreetLight
            ));
        else
            return ResponseEntity.notFound().build();
    }

}
