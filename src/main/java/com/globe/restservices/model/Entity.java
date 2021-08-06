package com.globe.restservices.model;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "entity")
public class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "database_id")
    private Integer databaseId;

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "active_indicator")
    private boolean activeIndicator;

    public Entity() {}

    public Entity(Integer databaseId, String entityName, boolean activeIndicator) {
        this.databaseId = databaseId;
        this.entityName = entityName;
        this.activeIndicator = activeIndicator;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Integer getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(Integer databaseId) {
        this.databaseId = databaseId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public boolean isActiveIndicator() {
        return activeIndicator;
    }

    public void setActiveIndicator(boolean activeIndicator) {
        this.activeIndicator = activeIndicator;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "entityId=" + entityId +
                ", databaseId=" + databaseId +
                ", entityName='" + entityName + '\'' +
                ", activeIndicator=" + activeIndicator +
                '}';
    }
}
