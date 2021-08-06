package com.globe.restservices.model;

public class ExtractField {
    private String reportField;
    private String availability;
    private String database;
    private String entity;
    private String attribute;
    private String logic;
    private String lastUpdatedBy;
    private String lastUpdatedDate;
    private String remark;

    public String getReportField() {
        return reportField;
    }

    public void setReportField(String reportField) {
        this.reportField = reportField;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getLogic() {
        return logic;
    }

    public void setLogic(String logic) {
        this.logic = logic;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Field{" +
                "reportField='" + reportField + '\'' +
                ", availability='" + availability + '\'' +
                ", database='" + database + '\'' +
                ", entity='" + entity + '\'' +
                ", attribute='" + attribute + '\'' +
                ", logic='" + logic + '\'' +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdatedDate='" + lastUpdatedDate + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
