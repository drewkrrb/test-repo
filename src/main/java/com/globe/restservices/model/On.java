package com.globe.restservices.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "join_on")
public class On {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "on_id")
    private Integer onId;

    @Column(name = "left_attribute_id")
    private Integer leftAttributeId;

    @Column(name = "left_transformation_value")
    private String leftTransformationValue;


    @Column(name = "right_attribute_id")
    private Integer rightAttributeId;

    @Column(name = "right_transformation_value")
    private String rightTransformationValue;

    public On() {}

    public On(Integer leftAttributeId, String leftTransformationValue, Integer rightAttributeId, String rightTransformationValue) {
        this.leftAttributeId = leftAttributeId;
        this.leftTransformationValue = leftTransformationValue;
        this.rightAttributeId = rightAttributeId;
        this.rightTransformationValue = rightTransformationValue;
    }

    public Integer getOnId() {
        return onId;
    }

    public void setOnId(Integer onId) {
        this.onId = onId;
    }

    public Integer getLeftAttributeId() {
        return leftAttributeId;
    }

    public void setLeftAttributeId(Integer leftAttributeId) {
        this.leftAttributeId = leftAttributeId;
    }

    public String getLeftTransformationValue() {
        return leftTransformationValue;
    }

    public void setLeftTransformationValue(String leftTransformationValue) {
        this.leftTransformationValue = leftTransformationValue;
    }

    public Integer getRightAttributeId() {
        return rightAttributeId;
    }

    public void setRightAttributeId(Integer rightAttributeId) {
        this.rightAttributeId = rightAttributeId;
    }

    public String getRightTransformationValue() {
        return rightTransformationValue;
    }

    public void setRightTransformationValue(String rightTransformationValue) {
        this.rightTransformationValue = rightTransformationValue;
    }

    @Override
    public String toString() {
        return "On{" +
                "onId=" + onId +
                ", leftAttributeId=" + leftAttributeId +
                ", leftTransformationValue='" + leftTransformationValue + '\'' +
                ", rightAttributeId=" + rightAttributeId +
                ", rightTransformationValue='" + rightTransformationValue + '\'' +
                '}';
    }
}
