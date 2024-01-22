package com.github.edm.EDMJavaFX.nodes;

import com.github.edm.EDMJavaFX.repository.entity.DocumentFX;
import com.github.edm.EDMJavaFX.service.DocumentFXService;
import com.github.edm.EDMJavaFX.service.DocumentFXServiceImpl;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UI {

    private final DocumentFXService documentFXService = new DocumentFXServiceImpl();

    public TableView<DocumentFX> TableView() {
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
                showEditDocumentDialog(newValue, tableView);
            }
        });

        return tableView;
    }

    public Button createAddButton() {
        Button addButton = new Button("Добавить");
        addButton.setOnAction(event -> showAddDocumentDialog());
        return addButton;
    }

    private void showAddDocumentDialog() {
        Stage addDialog = new Stage();
        addDialog.initModality(Modality.APPLICATION_MODAL);
        addDialog.setTitle("Добавление документа");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        TextField codeField = new TextField();
        TextField documentNumberField = new TextField();
        TextField documentTypeField = new TextField();
        DatePicker signingDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();
        TextField daysUntilDueField = new TextField();


        grid.add(new Label("Код:"), 0, 0);
        grid.add(codeField, 1, 0);
        grid.add(new Label("Номер документа:"), 0, 1);
        grid.add(documentNumberField, 1, 1);
        grid.add(new Label("Вид документа:"), 0, 2);
        grid.add(documentTypeField, 1, 2);
        grid.add(new Label("Дата подписания:"), 0, 3);
        grid.add(signingDatePicker, 1, 3);
        grid.add(new Label("Дата окончания:"), 0, 4);
        grid.add(endDatePicker, 1, 4);
        grid.add(new Label("Дней до просрочки:"), 0, 5);
        grid.add(daysUntilDueField, 1, 5);

        Button addButton = new Button("Добавить");
        addButton.setOnAction(event -> {
            documentFXService.addDocument(
                    codeField.getText(),
                    documentNumberField.getText(),
                    documentTypeField.getText(),
                    signingDatePicker.getValue(),
                    endDatePicker.getValue(),
                    daysUntilDueField.getText()
            );
            addDialog.close();
        });

        grid.add(addButton, 0, 6, 2, 1);

        Scene scene = new Scene(grid, 300, 250);
        addDialog.setScene(scene);

        addDialog.showAndWait();
    }
    private void showEditDocumentDialog(DocumentFX documentFX, TableView<DocumentFX> tableView) {
        Stage editDialog = new Stage();
        editDialog.initModality(Modality.APPLICATION_MODAL);
        editDialog.setTitle("Редактирование документа");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        TextField codeField = new TextField(String.valueOf(documentFX.getCode()));
        TextField documentNumberField = new TextField(String.valueOf(documentFX.getDocumentNumber()));
        TextField documentTypeField = new TextField(documentFX.getDocumentType());
        DatePicker signingDatePicker = new DatePicker(LocalDate.parse(documentFX.getSigningDate(),
                DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        DatePicker endDatePicker = new DatePicker(LocalDate.parse(documentFX.getEndDate(),
                DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        TextField daysUntilDueField = new TextField(String.valueOf(documentFX.getDaysUntilDue()));

        grid.add(new Label("Код:"), 0, 0);
        grid.add(codeField, 1, 0);
        grid.add(new Label("Номер документа:"), 0, 1);
        grid.add(documentNumberField, 1, 1);
        grid.add(new Label("Вид документа:"), 0, 2);
        grid.add(documentTypeField, 1, 2);
        grid.add(new Label("Дата подписания:"), 0, 3);
        grid.add(signingDatePicker, 1, 3);
        grid.add(new Label("Дата окончания:"), 0, 4);
        grid.add(endDatePicker, 1, 4);
        grid.add(new Label("Дней до просрочки:"), 0, 5);
        grid.add(daysUntilDueField, 1, 5);

        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(event -> {
            documentFXService.editDocument(
                    documentFX,
                    codeField.getText(),
                    documentNumberField.getText(),
                    documentTypeField.getText(),
                    signingDatePicker.getValue(),
                    endDatePicker.getValue(),
                    daysUntilDueField.getText(),
                    tableView
            );
            editDialog.close();
        });

        Button deleteButton = new Button("Удалить");
        deleteButton.setOnAction(event -> {
            documentFXService.deleteDocument(documentFX);
            editDialog.close();
        });

        grid.add(saveButton, 0, 6, 2, 1);
        grid.add(deleteButton, 1,6,2,1);

        Scene scene = new Scene(grid, 300, 250);
        editDialog.setScene(scene);

        editDialog.showAndWait();
    }
}