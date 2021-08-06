package com.globe.restservices.repository;

import com.globe.restservices.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributeRepository extends JpaRepository<Attribute, Integer> {
    List<Attribute> findByAttributeNameContaining(String attributeName);
    List<Attribute> findByActiveIndicator(boolean activeIndicator);
    Attribute findByAttributeNameAndEntityId(String attributeName, Integer entityId);
}
