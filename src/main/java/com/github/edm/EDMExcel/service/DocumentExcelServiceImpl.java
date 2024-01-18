package com.github.edm.EDMExcel.service;

import com.github.edm.EDMExcel.repository.DocumentExcelRepository;
import com.github.edm.EDMExcel.repository.entity.DocumentExcel;
import org.apache.poi.ss.usermodel.Sheet;

public class DocumentExcelServiceImpl implements DocumentExcelService {

    private final DocumentExcelRepository documentExcelRepository;

    public DocumentExcelServiceImpl(DocumentExcelRepository documentExcelRepository) {
        this.documentExcelRepository = documentExcelRepository;
    }


    @Override
    public void saveDocument(DocumentExcel documentExcel) {
        documentExcelRepository.saveDocument(documentExcel);
    }

    @Override
    public void updateDocument(DocumentExcel updatedDocumentExcel) {
        documentExcelRepository.updateDocument(updatedDocumentExcel);
    }

    @Override
    public void deleteDocument(DocumentExcel documentExcel) {
        documentExcelRepository.deleteDocument(documentExcel);
    }

    @Override
    public DocumentExcel getDocument(int id) {
        return documentExcelRepository.getDocumentExcel(id);
    }

    @Override
    public Sheet getConnectionToExcelTable() {
        return documentExcelRepository.getConnectionToExcelTable();
    }

}