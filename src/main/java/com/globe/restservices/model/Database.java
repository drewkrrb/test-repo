package com.globe.restservices.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "ma_database")
public class Database {

    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "database_id")
    private Integer databaseId;

    @Column(name = "database_name", nullable = false)
    private String databaseName;

    @Column(name = "active_indicator")
    private boolean activeIndicator;

    @PrePersist
    public void logNewDatabaseAttempt() {
        logger.info("Attempting to add new database with database name: " + databaseName);
    }

    @PostPersist
    public void logNewDatabaseAdded() {
        logger.info("Added database name " + databaseName + " with ID: " + databaseId);
    }

    public Database() {}

    public Database(String databaseName, boolean activeIndicator) {
        this.databaseName = databaseName;
        this.activeIndicator = activeIndicator;
    }

    public Integer getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(Integer databaseId) {
        this.databaseId = databaseId;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public boolean isActiveIndicator() {
        return activeIndicator;
    }

    public void setActiveIndicator(boolean activeIndicator) {
        this.activeIndicator = activeIndicator;
    }

    @Override
    public String toString() {
        return "Database{" +
                "databaseId=" + databaseId +
                ", databaseName='" + databaseName + '\'' +
                ", activeIndicator=" + activeIndicator +
                '}';
    }
}
