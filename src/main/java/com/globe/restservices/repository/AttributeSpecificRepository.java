package com.globe.restservices.repository;

import com.globe.restservices.model.AttributeSpecific;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributeSpecificRepository extends JpaRepository<AttributeSpecific, Integer> {
    List<AttributeSpecific> findByActiveIndicator(boolean activeIndicator);
    AttributeSpecific findByAttributeIdAndMultipleSourceId(Integer attributeId, Integer multipleSourceId);
}
