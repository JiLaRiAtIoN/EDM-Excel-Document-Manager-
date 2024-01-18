package com.github.edm.EDMJavaFX.repository.entity;

import javafx.beans.property.*;


public class DocumentFX {
    private final IntegerProperty code;
    private final IntegerProperty documentNumber;
    private final StringProperty documentType;
    private final StringProperty signingDate;
    private final StringProperty endDate;
    private final IntegerProperty daysUntilDue;

    public DocumentFX(Integer code, Integer documentNumber, String documentType, String signingDate, String endDate, Integer daysUntilDue) {
        this.code = new SimpleIntegerProperty(code);
        this.documentNumber = new SimpleIntegerProperty(documentNumber);
        this.documentType = new SimpleStringProperty(documentType);
        this.signingDate = new SimpleStringProperty(signingDate);
        this.endDate = new SimpleStringProperty(endDate);
        this.daysUntilDue = new SimpleIntegerProperty(daysUntilDue);
    }

    public Integer getCode() {
        return code.get();
    }

    public void setCode(int code) {
        this.code.set(code);
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber.set(documentNumber);
    }

    public void setDocumentType(String documentType) {
        this.documentType.set(documentType);
    }

    public void setSigningDate(String signingDate) {
        this.signingDate.set(signingDate);
    }

    public void setEndDate(String endDate) {
        this.endDate.set(endDate);
    }

    public void setDaysUntilDue(int daysUntilDue) {
        this.daysUntilDue.set(daysUntilDue);
    }

    public IntegerProperty codeProperty() {
        return code;
    }

    public Integer getDocumentNumber() {
        return documentNumber.get();
    }

    public IntegerProperty documentNumberProperty() {
        return documentNumber;
    }

    public String getDocumentType() {
        return documentType.get();
    }

    public StringProperty documentTypeProperty() {
        return documentType;
    }

    public String getSigningDate() {
        return signingDate.get();
    }

    public StringProperty signingDateProperty() {
        return signingDate;
    }

    public String getEndDate() {
        return endDate.get();
    }

    public StringProperty endDateProperty() {
        return endDate;
    }

    public Integer getDaysUntilDue() {
        return daysUntilDue.get();
    }

    public IntegerProperty daysUntilDueProperty() {
        return daysUntilDue;
    }
}