package com.globe.restservices.repository;

import com.globe.restservices.model.MultipleSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultipleSourceRepository extends JpaRepository<MultipleSource, Integer> {
    List<MultipleSource> findBySourceName(String sourceName);
    MultipleSource findBySourceNameAndEntityId(String sourceName, Integer entityId);
}
