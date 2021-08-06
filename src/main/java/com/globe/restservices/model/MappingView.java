package com.globe.restservices.model;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "mapping_view")
public class MappingView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attribute_specific_id")
    private Integer id;

    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "multiple_source_id")
    private Integer multipleSourceId;

    @Column(name = "source_location")
    private String sourceLocation;

    @Column(name = "source_entity")
    private String sourceEntity;

    @Column(name = "source_attribute")
    private String sourceAttribute;

    @Column(name = "multiple_source")
    private String multipleSource;

    @Column(name = "target_location")
    private String targetLocation;

    @Column(name = "transformation_rules")
    private String transformationRules;

    @Column(name = "subject_area")
    private String subjectArea;

    @Column(name = "target_entity")
    private String targetEntity;

    @Column(name = "target_attribute")
    private String targetAttribute;

    @Column(name = "position", nullable=true)
    private Integer position;

    @Column(name = "description")
    private String description;

    @Column(name = "not_option", nullable=true)
    private boolean notOption;

    @Column(name = "data_type")
    private String dataType;

    @Column(name = "comment")
    private String comment;

    @Column(name = "primary_key_indicator", nullable=true)
    private boolean primaryKeyIndicator;

    @Column(name = "primary_index_indicator", nullable=true)
    private boolean primaryIndexIndicator;

    @Column(name = "transformation_value")
    private String transformationValue;

    @Column(name = "filter_attribute_name")
    private String filterAttributeName;

    @Column(name = "condition_value")
    private String conditionValue;

    @Column(name = "filter_value")
    private String filterValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Integer getMultipleSourceId() {
        return multipleSourceId;
    }

    public void setMultipleSourceId(Integer multipleSourceId) {
        this.multipleSourceId = multipleSourceId;
    }

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

    public String getMultipleSource() {
        return multipleSource;
    }

    public void setMultipleSource(String multipleSource) {
        this.multipleSource = multipleSource;
    }

    public String getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(String targetLocation) {
        this.targetLocation = targetLocation;
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNotOption() {
        return notOption;
    }

    public void setNotOption(boolean notOption) {
        this.notOption = notOption;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isPrimaryKeyIndicator() {
        return primaryKeyIndicator;
    }

    public void setPrimaryKeyIndicator(boolean primaryKeyIndicator) {
        this.primaryKeyIndicator = primaryKeyIndicator;
    }

    public boolean isPrimaryIndexIndicator() {
        return primaryIndexIndicator;
    }

    public void setPrimaryIndexIndicator(boolean primaryIndexIndicator) {
        this.primaryIndexIndicator = primaryIndexIndicator;
    }

    public String getTransformationValue() {
        return transformationValue;
    }

    public void setTransformationValue(String transformationValue) {
        this.transformationValue = transformationValue;
    }

    public String getFilterAttributeName() {
        return filterAttributeName;
    }

    public void setFilterAttributeName(String filterAttributeName) {
        this.filterAttributeName = filterAttributeName;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

    @Override
    public String toString() {
        return "MappingView{" +
                "id=" + id +
                ", entityId=" + entityId +
                ", multipleSourceId=" + multipleSourceId +
                ", sourceLocation='" + sourceLocation + '\'' +
                ", sourceEntity='" + sourceEntity + '\'' +
                ", sourceAttribute='" + sourceAttribute + '\'' +
                ", multipleSource='" + multipleSource + '\'' +
                ", targetLocation='" + targetLocation + '\'' +
                ", transformationRules='" + transformationRules + '\'' +
                ", subjectArea='" + subjectArea + '\'' +
                ", targetEntity='" + targetEntity + '\'' +
                ", targetAttribute='" + targetAttribute + '\'' +
                ", position=" + position +
                ", description='" + description + '\'' +
                ", notOption=" + notOption +
                ", dataType='" + dataType + '\'' +
                ", comment='" + comment + '\'' +
                ", primaryKeyIndicator=" + primaryKeyIndicator +
                ", primaryIndexIndicator=" + primaryIndexIndicator +
                ", transformationValue='" + transformationValue + '\'' +
                ", filterAttributeName='" + filterAttributeName + '\'' +
                ", conditionValue='" + conditionValue + '\'' +
                ", filterValue='" + filterValue + '\'' +
                '}';
    }
}
