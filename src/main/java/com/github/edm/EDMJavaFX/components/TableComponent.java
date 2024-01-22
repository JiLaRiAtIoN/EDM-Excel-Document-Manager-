package com.github.edm.EDMJavaFX.components;

import com.github.edm.EDMJavaFX.repository.entity.DocumentFX;
import com.github.edm.EDMJavaFX.service.DocumentFXService;
import com.github.edm.EDMJavaFX.service.DocumentFXServiceImpl;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableComponent {
    private final DocumentFXService documentFXService = new DocumentFXServiceImpl();
    private final EditAndDeleteButtonsComponent editAndDeleteButtonsComponent = new EditAndDeleteButtonsComponent();

    public TableView<DocumentFX> tableView() {
        TableColumn<DocumentFX, Integer> codeCol = new TableColumn<>("Код");
        codeCol.setCellValueFactory(cellData -> cellData.getValue().codeProperty().asObject());

        TableColumn<DocumentFX, Integer> documentNumberCol = new TableColumn<>("Номер документа");
        documentNumberCol.setCellValueFactory(cellData -> cellData.getValue().documentNumberProperty().asObject());

        TableColumn<DocumentFX, String> documentTypeCol = new TableColumn<>("Вид документа");
        documentTypeCol.setCellValueFactory(cellData -> cellData.getValue().documentTypeProperty());

        TableColumn<DocumentFX, String> signingDateCol = new TableColumn<>("Дата подписания");
        signingDateCol.setCellValueFactory(cellData -> cellData.getValue().signingDateProperty());

        TableColumn<DocumentFX, String> endDateCol = new TableColumn<>("Дата окончания");
        endDateCol.setCellValueFactory(cellData -> cellData.getValue().endDateProperty());

        TableColumn<DocumentFX, Integer> daysUntilDueCol = new TableColumn<>("Дней до просрочки");
        daysUntilDueCol.setCellValueFactory(cellData -> cellData.getValue().daysUntilDueProperty().asObject());

        TableView<DocumentFX> tableView = new TableView<>();
        tableView.getColumns().addAll(codeCol, documentNumberCol, documentTypeCol, signingDateCol,
                endDateCol, daysUntilDueCol);

        documentFXService.getExcelTableData();
        tableView.setItems(documentFXService.getData());

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                editAndDeleteButtonsComponent.showEditDocumentDialog(newValue, tableView);
            }
        });

        return tableView;
    }
}
