package com.sample.hateoas.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sample.hateoas.domain.GardenGroup;

/**
 * @author zinur mustafayev
 */
public interface GardenGroupRepository extends PagingAndSortingRepository<GardenGroup, Long> {
}
