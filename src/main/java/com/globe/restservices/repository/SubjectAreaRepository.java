package com.globe.restservices.repository;

import com.globe.restservices.model.SubjectArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectAreaRepository extends JpaRepository<SubjectArea, Integer> {
    List<SubjectArea> findBySubjectAreaValue(String subjectAreaValue);
}
