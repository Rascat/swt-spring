package de.ul.swt.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import de.ul.swt.spring.domain.Person;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "person", path = "person")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    public Page<Person> findByNameStartsWithIgnoreCase(@Param("n") String name,
            Pageable p);

    @RestResource(path = "short")
    public Page<Person> findByNameStartsWithIgnoreCaseAndIdGreaterThanAndFirstNameIsNotLike(
            @Param("n") String name, @Param("id") long identifier,
            @Param("first") String firstName, Pageable p);

}
