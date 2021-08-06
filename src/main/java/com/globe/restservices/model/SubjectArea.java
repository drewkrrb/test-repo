package com.globe.restservices.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "subject_area")
public class SubjectArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_area_id")
    private Integer subjectAreaId;

    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "subject_area_value")
    private String subjectAreaValue;

    public SubjectArea() {}

    public SubjectArea(Integer entityId, String subjectAreaValue) {
        this.entityId = entityId;
        this.subjectAreaValue = subjectAreaValue;
    }

    public Integer getSubjectAreaId() {
        return subjectAreaId;
    }

    public void setSubjectAreaId(Integer subjectAreaId) {
        this.subjectAreaId = subjectAreaId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getSubjectAreaValue() {
        return subjectAreaValue;
    }

    public void setSubjectAreaValue(String subjectAreaValue) {
        this.subjectAreaValue = subjectAreaValue;
    }

    @Override
    public String toString() {
        return "SubjectArea{" +
                "subjectAreaId=" + subjectAreaId +
                ", entityId=" + entityId +
                ", subjectAreaValue='" + subjectAreaValue + '\'' +
                '}';
    }
}
