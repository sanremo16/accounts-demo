package org.san.home.persons.jpa;

import org.san.home.persons.model.Person;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author sanremo16
 */
@RepositoryRestResource
public interface PersonsRepository extends PagingAndSortingRepository<Person, Long>, QuerydslPredicateExecutor<Person> {

    //@RestResource(path = "secondNameStartsWith", rel = "secondNameStartsWith")
    //public Page findBySecondNameStartsWith(@Param("secondName") String secondName, Pageable p);
}