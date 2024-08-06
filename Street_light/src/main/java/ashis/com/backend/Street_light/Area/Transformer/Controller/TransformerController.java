package ashis.com.backend.Street_light.Area.Transformer.Controller;

import ashis.com.backend.Street_light.Area.Transformer.Model.TransformerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ashis.com.backend.Street_light.Area.Transformer.Service.TransformerService;

import java.util.Map;

@RestController
@RequestMapping("/gov/original/area/transformer")
public class TransformerController {

    @Autowired
    private TransformerService transformerService;

    @GetMapping("/{id}")
    public ResponseEntity< Map<String, Object> > getTransformerById(
        @PathVariable("id") String id
    ){
        TransformerModel transformer = transformerService.getTransformerById(id);
        String message = "Transformer " + id + " Details";

        if(transformer != null)
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "status_code", HttpStatus.OK,
                "message", message,
                "data", transformer
            ));
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity< Map<String, Object> > updateTransformer(
            @PathVariable("id") String id,
            @RequestBody TransformerModel Model
    ) {
        TransformerModel transformer = transformerService.UpdateTransformer(Model, id);
        if (transformer != null)
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "status_code", HttpStatus.OK,
                "message", "Transformer Updated",
                "data", transformer
            ));
        else
            return ResponseEntity.notFound().build();
    }

}
