package com.globe.restservices.model;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "entity_specific")
public class EntitySpecific {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entity_specific_id")
    private Integer entitySpecificId;

    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "entity_description")
    private String entityDescription;

    @Column(name = "entity_type_code")
    private String entityTypeCode;

    @Column(name = "entity_tag_value")
    private String entityTagValue;

    public EntitySpecific() {}

    public EntitySpecific(Integer entityId, String entityDescription, String entityTypeCode, String entityTagValue) {
        this.entityId = entityId;
        this.entityDescription = entityDescription;
        this.entityTypeCode = entityTypeCode;
        this.entityTagValue = entityTagValue;
    }

    public Integer getEntitySpecificId() {
        return entitySpecificId;
    }

    public void setEntitySpecificId(Integer entitySpecificId) {
        this.entitySpecificId = entitySpecificId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getEntityDescription() {
        return entityDescription;
    }

    public void setEntityDescription(String entityDescription) {
        this.entityDescription = entityDescription;
    }

    public String getEntityTypeCode() {
        return entityTypeCode;
    }

    public void setEntityTypeCode(String entityTypeCode) {
        this.entityTypeCode = entityTypeCode;
    }

    public String getEntityTagValue() {
        return entityTagValue;
    }

    public void setEntityTagValue(String entityTagValue) {
        this.entityTagValue = entityTagValue;
    }

    @Override
    public String toString() {
        return "EntitySpecific{" +
                "entitySpecificId=" + entitySpecificId +
                ", entityId=" + entityId +
                ", entityDescription='" + entityDescription + '\'' +
                ", entityTypeCode='" + entityTypeCode + '\'' +
                ", entityTagValue='" + entityTagValue + '\'' +
                '}';
    }
}
