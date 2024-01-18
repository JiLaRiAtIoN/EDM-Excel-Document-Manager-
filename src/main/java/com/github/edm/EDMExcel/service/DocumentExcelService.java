package com.github.edm.EDMExcel.service;

import com.github.edm.EDMExcel.repository.entity.DocumentExcel;
import org.apache.poi.ss.usermodel.Sheet;

public interface DocumentExcelService {
    void saveDocument(DocumentExcel documentExcel);
    void updateDocument(DocumentExcel updatedDocumentExcel);
    void deleteDocument(DocumentExcel documentExcel);
    DocumentExcel getDocument(int id);
    Sheet getConnectionToExcelTable();
}
