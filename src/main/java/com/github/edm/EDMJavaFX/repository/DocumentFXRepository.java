package com.github.edm.EDMJavaFX.repository;

import com.github.edm.EDMExcel.repository.DocumentExcelRepository;
import com.github.edm.EDMExcel.repository.entity.DocumentExcel;
import com.github.edm.EDMJavaFX.repository.entity.DocumentFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DocumentFXRepository {
    ObservableList<DocumentFX> data;
    private final DocumentExcelRepository documentExcelRepository = new DocumentExcelRepository();

    public void addDocumentFromDialog(String documentNumber, String documentType, LocalDate signingDate,
                                      LocalDate endDate, String daysUntilDue) {
        int code = documentExcelRepository.findEmptyRow(
                documentExcelRepository.getConnectionToExcelTable()).getRowNum();
        DocumentExcel documentExcel =
                new DocumentExcel(
                        code,
                        Integer.parseInt(documentNumber),
                        documentType,
                        signingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        endDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        Integer.parseInt(daysUntilDue)
        );
        documentExcelRepository.saveDocument(documentExcel);
        try {
            DocumentFX documentFX = new DocumentFX(
                    code,
                    Integer.parseInt(documentNumber),
                    documentType,
                    signingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    endDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    Integer.parseInt(daysUntilDue));
            data.add(documentFX);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void editDocumentFromDialog(DocumentFX documentFX, String code, String documentNumber, String documentType,
                                       LocalDate signingDate, LocalDate endDate, String daysUntilDue,
                                       TableView<DocumentFX> tableView) {
        try {
            Integer codeInt = Integer.parseInt(code);
            Integer documentNumberInt = Integer.parseInt(documentNumber);
            Integer daysUntilDueInt = Integer.parseInt(daysUntilDue);

            documentFX.setCode(codeInt);
            documentFX.setDocumentNumber(documentNumberInt);
            documentFX.setDocumentType(documentType);
            documentFX.setSigningDate(signingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            documentFX.setEndDate(endDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            documentFX.setDaysUntilDue(daysUntilDueInt);

            DocumentExcel documentExcel = new DocumentExcel(
                    Integer.parseInt(code),
                    Integer.parseInt(documentNumber),
                    documentType,
                    signingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    endDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    Integer.parseInt(daysUntilDue)
            );
            documentExcelRepository.updateDocument(documentExcel);

            tableView.refresh();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    public void getExcelTableData() {
        List<DocumentExcel> documentExcelList = documentExcelRepository.getExcelData();
        ObservableList<DocumentFX> documentFXObservableList = FXCollections.observableArrayList();

        for(DocumentExcel documentExcel : documentExcelList) {
            documentFXObservableList.add(new DocumentFX(
                    documentExcel.getId(),
                    documentExcel.getNumberOfDocument(),
                    documentExcel.getKindOfDocument(),
                    documentExcel.getStartData(),
                    documentExcel.getEndData(),
                    documentExcel.getDaysUntilOverdue())
            );
        }
        data = documentFXObservableList;
    }

    public void deleteDocument(DocumentFX documentFX) {
        getData().remove(documentFX);
        documentExcelRepository.deleteDocument(documentFX.getCode());
    }

    public ObservableList<DocumentFX> getData() {
        return data;
    }

    public void setData(ObservableList<DocumentFX> data) {
        this.data = data;
    }
}
