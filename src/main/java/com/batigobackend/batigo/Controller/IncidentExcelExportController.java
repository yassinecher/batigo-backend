package com.batigobackend.batigo.Controller;

import com.batigobackend.batigo.Entity.Incidents;
import com.batigobackend.batigo.Service.IncidentService;
import com.batigobackend.batigo.Util.ExcelGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/incidents") // ✅ correspond au préfixe déjà présent dans SecurityConfig
@CrossOrigin(origins = "http://localhost:4200") // ✅ autorise Angular à accéder
public class IncidentExcelExportController {

    private final IncidentService incidentService;

    @Autowired
    public IncidentExcelExportController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping("/export/excel") // ✅ route finale : /incidents/export/excel
    public void exportIncidentsToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=incidents.xlsx";
        response.setHeader(headerKey, headerValue);

        List<Incidents> incidents = incidentService.findAll();

        var stream = ExcelGenerator.incidentsToExcel(incidents);
        stream.transferTo(response.getOutputStream());
    }
}