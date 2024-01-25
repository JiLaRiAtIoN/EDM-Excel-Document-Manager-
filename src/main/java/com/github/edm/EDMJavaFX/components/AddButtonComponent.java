package com.github.edm.EDMJavaFX.components;

import com.github.edm.EDMJavaFX.service.DocumentFXService;
import com.github.edm.EDMJavaFX.service.DocumentFXServiceImpl;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddButtonComponent {
    private final DocumentFXService documentFXService = new DocumentFXServiceImpl();
    private final TableComponent tableComponent = new TableComponent();

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

        Button addButton = new Button("Добавить");
        addButton.setOnAction(event -> {
            documentFXService.addDocument(
                    documentNumberField.getText(),
                    documentTypeField.getText(),
                    signingDatePicker.getValue(),
                    endDatePicker.getValue()
            );
            addDialog.close();
        });

        grid.add(addButton, 0, 6, 2, 1);

        Scene scene = new Scene(grid, 300, 250);
        addDialog.setScene(scene);

        addDialog.showAndWait();
    }
}
