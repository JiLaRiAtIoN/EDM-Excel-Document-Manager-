package com.github.edm.EDMJavaFX.service;

import com.github.edm.EDMJavaFX.repository.entity.DocumentFX;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public interface DocumentFXService {
    void addDocument(String documentNumber, String documentType, LocalDate signingDate,
                     LocalDate endDate, String daysUntilDue);

    void editDocument(DocumentFX documentFX, String code, String documentNumber, String documentType,
                      LocalDate signingDate, LocalDate endDate, String daysUntilDue, TableView<DocumentFX> tableView);
    void getExcelTableData();
    void deleteDocument(DocumentFX documentFX);
    ObservableList<DocumentFX> getData();
    void setData(ObservableList<DocumentFX> data);
}
