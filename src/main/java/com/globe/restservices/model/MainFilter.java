package com.globe.restservices.model;

public class MainFilter {
    private String extractName;
    private String granularity;
    private String filterDescription;
    private String filter;
    private String remark;
    private String lastUpdatedBy;
    private String lastUpdatedDate;

    public String getExtractName() {
        return extractName;
    }

    public void setExtractName(String extractName) {
        this.extractName = extractName;
    }

    public String getGranularity() {
        return granularity;
    }

    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    public String getFilterDescription() {
        return filterDescription;
    }

    public void setFilterDescription(String filterDescription) {
        this.filterDescription = filterDescription;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
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
        return "MainFilter{" +
                "extractName='" + extractName + '\'' +
                ", granularity='" + granularity + '\'' +
                ", filterDescription='" + filterDescription + '\'' +
                ", filter='" + filter + '\'' +
                ", remark='" + remark + '\'' +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdatedDate='" + lastUpdatedDate + '\'' +
                '}';
    }
}
