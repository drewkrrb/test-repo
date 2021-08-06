package com.globe.restservices.repository;

import com.globe.restservices.model.MappingView;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MappingViewRepository extends PagingAndSortingRepository<MappingView, Integer> {
    //List<MappingView> findByTargetLocation(String targetLocation);
    //List<MappingView> findAllMappingViewsWithPagination(Pageable pageable);
}
