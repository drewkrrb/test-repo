package com.globe.restservices.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "multiple_source")
public class MultipleSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "multiple_source_id")
    private Integer multipleSourceId;

    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "source_name")
    private String sourceName;

    @Column(name = "active_indicator")
    private boolean activeIndicator;

    public MultipleSource() {}

    public MultipleSource(Integer entityId, String sourceName, boolean activeIndicator) {
        this.entityId = entityId;
        this.sourceName = sourceName;
        this.activeIndicator = activeIndicator;
    }

    public Integer getMultipleSourceId() {
        return multipleSourceId;
    }

    public void setMultipleSourceId(Integer multipleSourceId) {
        this.multipleSourceId = multipleSourceId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public boolean isActiveIndicator() {
        return activeIndicator;
    }

    public void setActiveIndicator(boolean activeIndicator) {
        this.activeIndicator = activeIndicator;
    }

    @Override
    public String toString() {
        return "MultipleSource{" +
                "multipleSourceId=" + multipleSourceId +
                ", entityId=" + entityId +
                ", sourceName='" + sourceName + '\'' +
                ", activeIndicator=" + activeIndicator +
                '}';
    }
}
