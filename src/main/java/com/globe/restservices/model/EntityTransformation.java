package com.globe.restservices.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "entity_transformation")
public class EntityTransformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entity_transformation_id")
    private Integer entityTransformationId;

    @Column(name = "multiple_source_id")
    private Integer multipleSourceId;

    @Column(name = "transformation_value")
    private String transformationValue;

    public EntityTransformation() {}

    public EntityTransformation(Integer multipleSourceId, String transformationValue) {
        this.multipleSourceId = multipleSourceId;
        this.transformationValue = transformationValue;
    }

    public Integer getEntityTransformationId() {
        return entityTransformationId;
    }

    public void setEntityTransformationId(Integer entityTransformationId) {
        this.entityTransformationId = entityTransformationId;
    }

    public Integer getMultipleSourceId() {
        return multipleSourceId;
    }

    public void setMultipleSourceId(Integer multipleSourceId) {
        this.multipleSourceId = multipleSourceId;
    }

    public String getTransformationValue() {
        return transformationValue;
    }

    public void setTransformationValue(String transformationValue) {
        this.transformationValue = transformationValue;
    }

    @Override
    public String toString() {
        return "EntityTransformation{" +
                "entityTransformationId=" + entityTransformationId +
                ", multipleSourceId=" + multipleSourceId +
                ", transformationValue='" + transformationValue + '\'' +
                '}';
    }
}
