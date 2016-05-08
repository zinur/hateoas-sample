package com.sample.hateoas.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sample.hateoas.domain.Child;

/**
 * @author zinur mustafayev
 */
public interface ChildRepository extends PagingAndSortingRepository<Child, Long> {
}
