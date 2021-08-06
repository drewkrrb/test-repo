package com.globe.restservices.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
@Table(name = "Audit")
public class Audit {

    private static final Logger log = LoggerFactory.getLogger(Audit.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_id")
    private Integer auditId;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "record_id")
    private Integer recordId;

    @Column(name = "attribute_name")
    private String attributeName;

    @Column(name = "new_value")
    private String newValue;

    @Column(name = "effective_datetime")
    private Timestamp effectiveDatetime;

    public Audit() {}

    public Audit(String tableName, Integer recordId, String attributeName, String newValue, Timestamp effectiveDatetime) {
        this.tableName = tableName;
        this.recordId = recordId;
        this.attributeName = attributeName;
        this.newValue = newValue;
        this.effectiveDatetime = effectiveDatetime;
    }

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public Timestamp getEffectiveDatetime() {
        return effectiveDatetime;
    }

    public void setEffectiveDatetime(Timestamp effectiveDatetime) {
        this.effectiveDatetime = effectiveDatetime;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "auditId=" + auditId +
                ", tableName='" + tableName + '\'' +
                ", recordId=" + recordId +
                ", attributeName='" + attributeName + '\'' +
                ", newValue='" + newValue + '\'' +
                ", effectiveDatetime=" + effectiveDatetime +
                '}';
    }
}
