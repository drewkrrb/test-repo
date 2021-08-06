package com.globe.restservices.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "ma_join")
public class Join {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "join_id")
    private Integer joinId;

    @Column(name = "multiple_source_id")
    private Integer multipleSourceId;

    @Column(name = "position_count")
    private Integer positionCount;

    @Column(name = "typeValue")
    private String typeValue;

    @Column(name = "left_database_id")
    private Integer leftDatabaseId;

    @Column(name = "left_entity_id")
    private Integer leftEntityId;

    @Column(name = "right_database_id")
    private Integer rightDatabaseId;

    @Column(name = "right_entity_id")
    private Integer rightEntityId;

    public Join() {}

    public Join(Integer multipleSourceId, Integer positionCount, String typeValue, Integer leftDatabaseId, Integer leftEntityId, Integer rightDatabaseId, Integer rightEntityId) {
        this.multipleSourceId = multipleSourceId;
        this.positionCount = positionCount;
        this.typeValue = typeValue;
        this.leftDatabaseId = leftDatabaseId;
        this.leftEntityId = leftEntityId;
        this.rightDatabaseId = rightDatabaseId;
        this.rightEntityId = rightEntityId;
    }

    public Integer getJoinId() {
        return joinId;
    }

    public void setJoinId(Integer joinId) {
        this.joinId = joinId;
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

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public Integer getLeftDatabaseId() {
        return leftDatabaseId;
    }

    public void setLeftDatabaseId(Integer leftDatabaseId) {
        this.leftDatabaseId = leftDatabaseId;
    }

    public Integer getLeftEntityId() {
        return leftEntityId;
    }

    public void setLeftEntityId(Integer leftEntityId) {
        this.leftEntityId = leftEntityId;
    }

    public Integer getRightDatabaseId() {
        return rightDatabaseId;
    }

    public void setRightDatabaseId(Integer rightDatabaseId) {
        this.rightDatabaseId = rightDatabaseId;
    }

    public Integer getRightEntityId() {
        return rightEntityId;
    }

    public void setRightEntityId(Integer rightEntityId) {
        this.rightEntityId = rightEntityId;
    }

    @Override
    public String toString() {
        return "Join{" +
                "joinId=" + joinId +
                ", multipleSourceId=" + multipleSourceId +
                ", positionCount=" + positionCount +
                ", typeValue='" + typeValue + '\'' +
                ", leftDatabaseId=" + leftDatabaseId +
                ", leftEntityId=" + leftEntityId +
                ", rightDatabaseId=" + rightDatabaseId +
                ", rightEntityId=" + rightEntityId +
                '}';
    }
}
