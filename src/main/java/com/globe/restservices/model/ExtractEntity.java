package com.globe.restservices.model;

public class ExtractEntity {
    private String drivingTable;
    private String relationalTable;
    private String joinType;
    private String joinCondition;
    private String remark;
    private String lastUpdatedBy;
    private String lastUpdatedDate;

    public String getDrivingTable() {
        return drivingTable;
    }

    public void setDrivingTable(String drivingTable) {
        this.drivingTable = drivingTable;
    }

    public String getRelationalTable() {
        return relationalTable;
    }

    public void setRelationalTable(String relationalTable) {
        this.relationalTable = relationalTable;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getJoinCondition() {
        return joinCondition;
    }

    public void setJoinCondition(String joinCondition) {
        this.joinCondition = joinCondition;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    public String toString() {
        return "ExtractEntity{" +
                "drivingTable='" + drivingTable + '\'' +
                ", relationalTable='" + relationalTable + '\'' +
                ", joinType='" + joinType + '\'' +
                ", joinCondition='" + joinCondition + '\'' +
                ", remarks='" + remark + '\'' +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdatedDate='" + lastUpdatedDate + '\'' +
                '}';
    }
}
