package com.globe.restservices.repository;

import com.globe.restservices.model.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntityRepository extends JpaRepository<Entity, Integer> {
    List<Entity> findByEntityNameContaining(String entityName);
    List<Entity> findByActiveIndicator(boolean activeIndicator);
    Entity findByEntityNameAndDatabaseId(String entityName, Integer databaseId);
}
