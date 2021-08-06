package com.globe.restservices.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "attribute_specific")
public class AttributeSpecific {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_specific_id")
    private Integer attributeSpecificId;

    @Column(name = "attribute_id")
    private Integer attributeId;

    @Column(name = "multiple_source_id")
    private Integer multipleSourceId;

    @Column(name = "source_attribute_id", nullable = true)
    private Integer sourceAttributeId;

    @Column(name = "transformation_value")
    private String transformationValue;

    @Column(name = "attribute_comment_value")
    private String attributeCommentValue;

    @Column(name = "active_indicator")
    private boolean activeIndicator;

    public AttributeSpecific() {}

    public AttributeSpecific(Integer attributeId, Integer multipleSourceId, Integer sourceAttributeId, String transformationValue, String attributeCommentValue, boolean activeIndicator) {
        this.attributeId = attributeId;
        this.multipleSourceId = multipleSourceId;
        this.sourceAttributeId = sourceAttributeId;
        this.transformationValue = transformationValue;
        this.attributeCommentValue = attributeCommentValue;
        this.activeIndicator = activeIndicator;
    }

    public Integer getAttributeSpecificId() {
        return attributeSpecificId;
    }

    public void setAttributeSpecificId(Integer attributeSpecificId) {
        this.attributeSpecificId = attributeSpecificId;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public Integer getMultipleSourceId() {
        return multipleSourceId;
    }

    public void setMultipleSourceId(Integer multipleSourceId) {
        this.multipleSourceId = multipleSourceId;
    }

    public Integer getSourceAttributeId() {
        return sourceAttributeId;
    }

    public void setSourceAttributeId(Integer sourceAttributeId) {
        this.sourceAttributeId = sourceAttributeId;
    }

    public String getTransformationValue() {
        return transformationValue;
    }

    public void setTransformationValue(String transformationValue) {
        this.transformationValue = transformationValue;
    }

    public String getAttributeCommentValue() {
        return attributeCommentValue;
    }

    public void setAttributeCommentValue(String attributeCommentValue) {
        this.attributeCommentValue = attributeCommentValue;
    }

    public boolean isActiveIndicator() {
        return activeIndicator;
    }

    public void setActiveIndicator(boolean activeIndicator) {
        this.activeIndicator = activeIndicator;
    }
}
