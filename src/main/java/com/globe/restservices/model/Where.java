package com.globe.restservices.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "ma_where")
public class Where {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "where_id")
    private Integer whereId;

    @Column(name = "multiple_source_id")
    private Integer multipleSourceId;

    @Column(name = "position_count")
    private Integer positionCount;

    @Column(name = "attribute_id")
    private Integer attributeId;

    @Column(name = "condition_value")
    private String conditionValue;

    @Column(name = "filter_value")
    private String filterValue;

    public Where() {}

    public Where(Integer multipleSourceId, Integer positionCount, Integer attributeId, String conditionValue, String filterValue) {
        this.multipleSourceId = multipleSourceId;
        this.positionCount = positionCount;
        this.attributeId = attributeId;
        this.conditionValue = conditionValue;
        this.filterValue = filterValue;
    }

    public Integer getWhereId() {
        return whereId;
    }

    public void setWhereId(Integer whereId) {
        this.whereId = whereId;
    }

    public Integer getMultipleSourceId() {
        return multipleSourceId;
    }

    public void setMultipleSourceId(Integer multipleSourceId) {
        this.multipleSourceId = multipleSourceId;
    }

    public Integer getPositionCount() {
        return positionCount;
    }

    public void setPositionCount(Integer positionCount) {
        this.positionCount = positionCount;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
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
        return "Where{" +
                "whereId=" + whereId +
                ", multipleSourceId=" + multipleSourceId +
                ", positionCount=" + positionCount +
                ", attributeId=" + attributeId +
                ", conditionValue='" + conditionValue + '\'' +
                ", filterValue='" + filterValue + '\'' +
                '}';
    }
}
