package com.globe.restservices.repository;

import com.globe.restservices.model.Database;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatabaseRepository extends JpaRepository<Database, Integer> {
    List<Database> findByDatabaseNameContaining(String databaseName);
    List<Database> findByActiveIndicator(boolean activeIndicator);
    Database findByDatabaseName(String databaseName);
}
