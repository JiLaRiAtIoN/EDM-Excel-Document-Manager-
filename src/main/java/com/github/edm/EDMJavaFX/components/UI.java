package com.github.edm.EDMJavaFX.components;

import com.github.edm.EDMJavaFX.repository.entity.DocumentFX;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class UI {
    private final TableComponent tableComponent = new TableComponent();
    private final AddButtonComponent addButtonComponent = new AddButtonComponent();

    public TableView<DocumentFX> tableView() {
        return tableComponent.tableView();
    }

    public Button createAddButton() {
        return addButtonComponent.createAddButton();
    }
}
