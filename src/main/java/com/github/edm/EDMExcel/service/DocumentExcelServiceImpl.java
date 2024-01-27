package com.github.edm.EDMExcel.service;

import com.github.edm.EDMExcel.repository.DocumentExcelRepository;
import com.github.edm.EDMExcel.repository.entity.DocumentExcel;

import java.util.List;

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
    public void deleteDocument(Integer id) {
        documentExcelRepository.deleteDocument(id);
    }

    @Override
    public List<DocumentExcel> excelData() {
        return documentExcelRepository.getExcelData();
    }
}