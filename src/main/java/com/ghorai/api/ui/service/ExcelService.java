package com.ghorai.api.ui.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.ghorai.api.ui.entity.SalaryEntity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    public void generateExcel(List<SalaryEntity> salaries, String filePath) throws IOException {
        // Create a new Workbook
        Workbook workbook = new XSSFWorkbook();

        // Create a sheet
        Sheet sheet = workbook.createSheet("Salaries");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Emp No");
        headerRow.createCell(1).setCellValue("Amount");
        headerRow.createCell(2).setCellValue("From Date");
        headerRow.createCell(3).setCellValue("To Date");

        // Populate the sheet with data
        int rowNum = 1;
        for (SalaryEntity salary : salaries) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(salary.getEmpNo());
            row.createCell(1).setCellValue(salary.getAmount());
            row.createCell(2).setCellValue(salary.getFrom_date());
            row.createCell(3).setCellValue(salary.getTo_date());
        }

        // Resize columns to fit content
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        // Close the workbook
        workbook.close();
    }
}
