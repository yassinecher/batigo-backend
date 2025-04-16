package com.batigobackend.batigo.Controller;

import com.batigobackend.batigo.Entity.ResultatInspection;
import com.batigobackend.batigo.Entity.EnumOption;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enums")
@CrossOrigin(origins = "*") // Optionnel mais utile si problème de CORS
public class InspectionEnumController {

    @GetMapping("/resultats-inspection")
    public List<EnumOption> getResultatInspectionEnum() {
        return Arrays.stream(ResultatInspection.values())
                .map(e -> new EnumOption(e.name(), e.getLabel()))
                .collect(Collectors.toList());
    }
}
