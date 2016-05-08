package com.sample.hateoas.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sample.hateoas.domain.KinderGartener;

/**
 * @author zinur mustafayev
 */
public interface KinderGartenerRepository extends PagingAndSortingRepository<KinderGartener, Long> {
}
