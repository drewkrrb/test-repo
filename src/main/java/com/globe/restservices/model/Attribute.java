package com.globe.restservices.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "attribute")
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_id")
    private Integer attributeId;

    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "attribute_name")
    private String attributeName;

    @Column(name = "data_type_value")
    private String dataTypeValue;

    @Column(name = "attribute_description")
    private String attributeDescription;

    @Column(name = "nullable_indicator")
    private boolean nullableIndicator;

    @Column(name = "primary_key_indicator")
    private boolean primaryKeyIndicator;

    @Column(name = "primary_index_indicator")
    private boolean primaryIndexIndicator;

    @Column(name = "position_count")
    private Integer positionCount;

    @Column(name = "active_indicator")
    private boolean activeIndicator;

    public Attribute() {}

    public Attribute(Integer entityId, String attributeName, String dataTypeValue, String attributeDescription, boolean nullableIndicator, boolean primaryKeyIndicator, boolean primaryIndexIndicator, Integer positionCount, boolean activeIndicator) {
        this.entityId = entityId;
        this.attributeName = attributeName;
        this.dataTypeValue = dataTypeValue;
        this.attributeDescription = attributeDescription;
        this.nullableIndicator = nullableIndicator;
        this.primaryKeyIndicator = primaryKeyIndicator;
        this.primaryIndexIndicator = primaryIndexIndicator;
        this.positionCount = positionCount;
        this.activeIndicator = activeIndicator;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getDataTypeValue() {
        return dataTypeValue;
    }

    public void setDataTypeValue(String dataTypeValue) {
        this.dataTypeValue = dataTypeValue;
    }

    public String getAttributeDescription() {
        return attributeDescription;
    }

    public void setAttributeDescription(String attributeDescription) {
        this.attributeDescription = attributeDescription;
    }

    public boolean isNullableIndicator() {
        return nullableIndicator;
    }

    public void setNullableIndicator(boolean nullableIndicator) {
        this.nullableIndicator = nullableIndicator;
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

    public Integer getPositionCount() {
        return positionCount;
    }

    public void setPositionCount(Integer positionCount) {
        this.positionCount = positionCount;
    }

    public boolean isActiveIndicator() {
        return activeIndicator;
    }

    public void setActiveIndicator(boolean activeIndicator) {
        this.activeIndicator = activeIndicator;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "attributeId=" + attributeId +
                ", entityId=" + entityId +
                ", attributeName='" + attributeName + '\'' +
                ", dataTypeValue='" + dataTypeValue + '\'' +
                ", attributeDescription='" + attributeDescription + '\'' +
                ", nullableIndicator=" + nullableIndicator +
                ", primaryKeyIndicator=" + primaryKeyIndicator +
                ", primaryIndexIndicator=" + primaryIndexIndicator +
                ", positionCount=" + positionCount +
                ", activeIndicator=" + activeIndicator +
                '}';
    }
}
