package com.github.edm.EDMJavaFX.service;

import com.github.edm.EDMJavaFX.repository.DocumentFXRepository;
import com.github.edm.EDMJavaFX.repository.entity.DocumentFX;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class DocumentFXServiceImpl implements DocumentFXService {

    private final DocumentFXRepository documentFXRepository = new DocumentFXRepository();

    @Override
    public void addDocument(String documentNumber, String documentType, LocalDate signingDate,
                            LocalDate endDate) {
        documentFXRepository.addDocumentFromDialog(
                documentNumber,
                documentType,
                signingDate,
                endDate
        );
    }

    @Override
    public void editDocument(DocumentFX documentFX, String code, String documentNumber, String documentType,
                             LocalDate signingDate, LocalDate endDate, String daysUntilDue,
                             TableView<DocumentFX> tableView) {
        documentFXRepository.editDocumentFromDialog(
                documentFX,
                code,
                documentNumber,
                documentType,
                signingDate,
                endDate,
                daysUntilDue,
                tableView
        );
    }
    @Override
    public void getExcelTableData() {
        documentFXRepository.getExcelTableData();
    }

    @Override
    public void deleteDocument(DocumentFX documentFX) {
        documentFXRepository.deleteDocument(documentFX);
    }
}
