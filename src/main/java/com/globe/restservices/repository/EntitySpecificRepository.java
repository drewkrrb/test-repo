package com.globe.restservices.repository;

import com.globe.restservices.model.EntitySpecific;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntitySpecificRepository extends JpaRepository<EntitySpecific, Integer> {
}
