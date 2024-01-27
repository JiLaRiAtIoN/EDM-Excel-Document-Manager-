package com.github.edm.EDMJavaFX.components;

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

public class EditAndDeleteButtonsComponent {
    private final DocumentFXService documentFXService = new DocumentFXServiceImpl();

    public void showEditDocumentDialog(DocumentFX documentFX, TableView<DocumentFX> tableView) {
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

        codeField.setEditable(false);
        daysUntilDueField.setEditable(false);

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
        grid.add(deleteButton, 1, 6, 2, 1);

        Scene scene = new Scene(grid, 300, 250);
        editDialog.setScene(scene);

        editDialog.showAndWait();
    }
}
