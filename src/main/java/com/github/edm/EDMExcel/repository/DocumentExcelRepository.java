package com.github.edm.EDMExcel.repository;

import com.github.edm.EDMExcel.repository.entity.DocumentExcel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DocumentExcelRepository {
    private final static String pathToExcelList = "./ExampleList.xlsx";
    private final static String nameOfList = "Документы";
    public List<DocumentExcel> documentExcelList;

    public void saveDocument(DocumentExcel documentExcel) {
        try {
            Workbook workbook = new XSSFWorkbook(new FileInputStream(pathToExcelList));
            Sheet sheet = workbook.getSheet(nameOfList);
            DataFormat format = workbook.createDataFormat();
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
            Row newRow = findEmptyRow(sheet);
            assert newRow != null;
            addDocumentInExcel(newRow, documentExcel, dataStyle);
            workbook.write(new FileOutputStream(pathToExcelList));
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateDocument(DocumentExcel updatedDocumentExcel) {
        try {
            Workbook workbook = new XSSFWorkbook(new FileInputStream(pathToExcelList));
            Sheet sheet = workbook.getSheet(nameOfList);
            DataFormat format = workbook.createDataFormat();
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
            Row row = sheet.getRow(updatedDocumentExcel.getId());
            addDocumentInExcel(row, updatedDocumentExcel, dataStyle);
            workbook.write(new FileOutputStream(pathToExcelList));
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteDocument(Integer id) {
        try {
            Workbook workbook = new XSSFWorkbook(new FileInputStream(pathToExcelList));
            Sheet sheet = workbook.getSheet(nameOfList);
            int rowToDelete = id;
            Row row = sheet.getRow(rowToDelete);
            if (row != null) {
                sheet.removeRow(row);
            }
            workbook.write(new FileOutputStream(pathToExcelList));
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Sheet getConnectionToExcelTable() {
        try {
            return new XSSFWorkbook(new FileInputStream(pathToExcelList)).getSheet(nameOfList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DocumentExcel> getExcelData() {
        List<DocumentExcel> allData = new ArrayList<>();
        Sheet sheet = getConnectionToExcelTable();
        for (Row row : sheet) {
            Iterator<Cell> cellIterator = row.cellIterator();
            DocumentExcel document = new DocumentExcel();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                setDocumentDataFromCell(document, cell);
            }
            if(document.getNumberOfDocument() == 0)
                continue;
            calculateDaysUntilDue(document, row);
            allData.add(document);
        }
        documentExcelList = allData;
        return allData;
    }
    public Row findEmptyRow(Sheet sheet) {
        for(Row row : sheet) {
            if(row.getCell(0) == null) {
                return sheet.createRow(row.getRowNum());
            }
        }
        return null;
    }
    private void addDocumentInExcel(Row row, DocumentExcel document, CellStyle dataStyle) {
        Cell cellCode = row.createCell(0);
        cellCode.setCellValue(row.getRowNum());
        Cell cellNumberOfDocument = row.createCell(1);
        cellNumberOfDocument.setCellValue(document.getNumberOfDocument());
        Cell cellKindOfDocument = row.createCell(2);
        cellKindOfDocument.setCellValue(document.getKindOfDocument());
        Cell cellStartDate = row.createCell(3);
        cellStartDate.setCellStyle(dataStyle);
        cellStartDate.setCellValue(document.getStartData());
        Cell cellEndDate = row.createCell(4);
        cellEndDate.setCellStyle(dataStyle);
        cellEndDate.setCellValue(document.getEndData());
    }

    private void setDocumentDataFromCell(DocumentExcel document, Cell cell) {
        int columnIndex = cell.getColumnIndex();
        switch (columnIndex) {
            case 0 -> document.setId(getCellValueAsInt(cell));
            case 1 -> document.setNumberOfDocument(getCellValueAsInt(cell));
            case 2 -> document.setKindOfDocument(getCellValueAsString(cell));
            case 3 -> document.setStartData(getCellValueAsString(cell));
            case 4 -> document.setEndData(getCellValueAsString(cell));
        }
    }

    private int getCellValueAsInt(Cell cell) {
        if (cell == null) {
            return 0;
        }

        if (Objects.requireNonNull(cell.getCellType()) == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        }
        return 0;
    }
    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        if (Objects.requireNonNull(cell.getCellType()) == CellType.STRING) {
            return cell.getStringCellValue();
        }
        return null;
    }
    private void calculateDaysUntilDue(DocumentExcel documentExcel, Row row) {
        documentExcel.setDaysUntilOverdue((int) ChronoUnit.DAYS.between(LocalDate.now(),
                LocalDate.parse(row.getCell(4).toString(),
                        DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
    }
}