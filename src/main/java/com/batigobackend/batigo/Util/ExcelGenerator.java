package com.batigobackend.batigo.Util;

import com.batigobackend.batigo.Entity.Incidents;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


public class ExcelGenerator {

    public static ByteArrayInputStream incidentsToExcel(List<Incidents> incidents) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Incidents");

            // ✅ En-têtes
            Row headerRow = sheet.createRow(0);
            String[] headers = { "ID", "Description", "Gravité", "État", "Responsable", "Date" };
            for (int col = 0; col < headers.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(headers[col]);
            }

            // ✅ Lignes de données
            int rowIdx = 1;
            for (Incidents incident : incidents) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(incident.getId());
                row.createCell(1).setCellValue(incident.getDescription());
                row.createCell(2).setCellValue(incident.getGravite());
                row.createCell(3).setCellValue(incident.getEtat());
                row.createCell(5).setCellValue(incident.getDate().toString());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'export Excel : " + e.getMessage());
        }
    }
}
