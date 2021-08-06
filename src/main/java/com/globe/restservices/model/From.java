package com.globe.restservices.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "ma_from")
public class From {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "from_id")
    private Integer fromId;

    @Column(name = "multiple_source_id")
    private Integer multipleSourceId;

    @Column(name = "database_id")
    private Integer databaseId;

    @Column(name = "entity_id")
    private Integer entityId;

    public From() {}

    public From(Integer multipleSourceId, Integer databaseId, Integer entityId) {
        this.multipleSourceId = multipleSourceId;
        this.databaseId = databaseId;
        this.entityId = entityId;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getMultipleSourceId() {
        return multipleSourceId;
    }

    public void setMultipleSourceId(Integer multipleSourceId) {
        this.multipleSourceId = multipleSourceId;
    }

    public Integer getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(Integer databaseId) {
        this.databaseId = databaseId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    @Override
    public String toString() {
        return "From{" +
                "fromId=" + fromId +
                ", multipleSourceId=" + multipleSourceId +
                ", databaseId=" + databaseId +
                ", entityId=" + entityId +
                '}';
    }
}
