package com.globe.restservices.model;

public class Mapping {
    private String sourceLocation;
    private String sourceEntity;
    private String sourceAttribute;
    private String sourceComments;
    private String multipleSource;
    private String transformationRules;
    private String subjectArea;
    private String targetEntity;
    private String targetAttribute;
    private String position;
    private String Description;
    private String primaryKey;
    private String primaryIndex;
    private String notOption;
    private String dataType;
    private Integer mapped;
    private String updatedBy;
    private String updatedVersion;
    private String updateDate;
    private String comments;

    public String getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(String sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public String getSourceEntity() {
        return sourceEntity;
    }

    public void setSourceEntity(String sourceEntity) {
        this.sourceEntity = sourceEntity;
    }

    public String getSourceAttribute() {
        return sourceAttribute;
    }

    public void setSourceAttribute(String sourceAttribute) {
        this.sourceAttribute = sourceAttribute;
    }

    public String getSourceComments() {
        return sourceComments;
    }

    public void setSourceComments(String sourceComments) {
        this.sourceComments = sourceComments;
    }

    public String getMultipleSource() {
        return multipleSource;
    }

    public void setMultipleSource(String multipleSource) {
        this.multipleSource = multipleSource;
    }

    public String getTransformationRules() {
        return transformationRules;
    }

    public void setTransformationRules(String transformationRules) {
        this.transformationRules = transformationRules;
    }

    public String getSubjectArea() {
        return subjectArea;
    }

    public void setSubjectArea(String subjectArea) {
        this.subjectArea = subjectArea;
    }

    public String getTargetEntity() {
        return targetEntity;
    }

    public void setTargetEntity(String targetEntity) {
        this.targetEntity = targetEntity;
    }

    public String getTargetAttribute() {
        return targetAttribute;
    }

    public void setTargetAttribute(String targetAttribute) {
        this.targetAttribute = targetAttribute;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getPrimaryIndex() {
        return primaryIndex;
    }

    public void setPrimaryIndex(String primaryIndex) {
        this.primaryIndex = primaryIndex;
    }

    public String getNotOption() {
        return notOption;
    }

    public void setNotOption(String notOption) {
        this.notOption = notOption;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getMapped() {
        return mapped;
    }

    public void setMapped(Integer mapped) {
        this.mapped = mapped;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedVersion() {
        return updatedVersion;
    }

    public void setUpdatedVersion(String updatedVersion) {
        this.updatedVersion = updatedVersion;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "MappingSpecification{" +
                "sourceLocation='" + sourceLocation + '\'' +
                ", sourceEntity='" + sourceEntity + '\'' +
                ", sourceAttribute='" + sourceAttribute + '\'' +
                ", sourceComments='" + sourceComments + '\'' +
                ", multipleSource='" + multipleSource + '\'' +
                ", transformationRules='" + transformationRules + '\'' +
                ", subjectArea='" + subjectArea + '\'' +
                ", targetEntity='" + targetEntity + '\'' +
                ", targetAttribute='" + targetAttribute + '\'' +
                ", position='" + position + '\'' +
                ", Description='" + Description + '\'' +
                ", primaryKey='" + primaryKey + '\'' +
                ", primaryIndex='" + primaryIndex + '\'' +
                ", notOption='" + notOption + '\'' +
                ", dataType='" + dataType + '\'' +
                ", mapped=" + mapped +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedVersion='" + updatedVersion + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
