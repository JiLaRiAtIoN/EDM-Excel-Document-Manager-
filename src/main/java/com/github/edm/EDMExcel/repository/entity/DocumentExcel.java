package com.github.edm.EDMExcel.repository.entity;


public class DocumentExcel {
    private int id;
    private int numberOfDocument;
    private String kindOfDocument;
    private String startData;
    private String endData;
    private int daysUntilOverdue;

    public DocumentExcel(Integer id, Integer numberOfDocument, String kindOfDocument, String startData, String endData,
                         Integer daysUntilOverdue) {
        this.id = id;
        this.numberOfDocument = numberOfDocument;
        this.kindOfDocument = kindOfDocument;
        this.startData = startData;
        this.endData = endData;
        this.daysUntilOverdue = daysUntilOverdue;
    }

    public DocumentExcel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberOfDocument() {
        return numberOfDocument;
    }

    public void setNumberOfDocument(Integer numberOfDocument) {
        this.numberOfDocument = numberOfDocument;
    }

    public String getKindOfDocument() {
        return kindOfDocument;
    }

    public void setKindOfDocument(String kindOfDocument) {
        this.kindOfDocument = kindOfDocument;
    }

    public String getStartData() {
        return startData;
    }

    public void setStartData(String startData) {
        this.startData = startData;
    }

    public String getEndData() {
        return endData;
    }

    public void setEndData(String endData) {
        this.endData = endData;
    }

    public Integer getDaysUntilOverdue() {
        return daysUntilOverdue;
    }

    public void setDaysUntilOverdue(Integer daysUntilOverdue) {
        this.daysUntilOverdue = daysUntilOverdue;
    }

    @Override
    public String toString() {
        return "DocumentExcel{" +
                "id=" + id +
                ", numberOfDocument=" + numberOfDocument +
                ", kindOfDocument='" + kindOfDocument + '\'' +
                ", startData='" + startData + '\'' +
                ", endData='" + endData + '\'' +
                ", daysUntilOverdue=" + daysUntilOverdue +
                '}';
    }
}
