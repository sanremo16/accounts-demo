package org.san.home.persons.controller;

import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.san.home.persons.jpa.PersonsRepository;
import org.san.home.persons.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/persons")
@Tag(name = "Search QueryDSL API for persons")
public class PersonsSearchController {

    @Autowired
    private PersonsRepository personsRepository;

    /**
     * can be extended for complex predicate query, see https://www.baeldung.com/rest-api-search-language-spring-data-querydsl
     * @link https://www.baeldung.com/rest-api-search-language-spring-data-querydsl,
     * @link https://stackoverflow.com/questions/51127468/how-to-easy-implement-rest-api-query-language-with-querydsl-and-spring-data-to
     * @param predicate
     * @return
     */
    @GetMapping()
    public Iterable<Person> getUsersByQuerydslPredicate(
            @QuerydslPredicate(root = Person.class) Predicate predicate) {
        return personsRepository.findAll(predicate);
    }
}
