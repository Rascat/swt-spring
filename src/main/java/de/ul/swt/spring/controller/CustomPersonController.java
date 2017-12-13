package de.ul.swt.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.ul.swt.spring.domain.Person;
import de.ul.swt.spring.domain.QPerson;
import de.ul.swt.spring.repository.PersonRepository;

@RepositoryRestController
@RequestMapping(value = "/person/search")
public class CustomPersonController {

    private PersonRepository repository;

    public CustomPersonController(final PersonRepository repo) {
        this.repository = repo;
    }

    @Autowired
    private PagedResourcesAssembler<Person> pageAssembler;

    @RequestMapping(value = "/myseach", method = { RequestMethod.GET })
    @ResponseBody
    public PagedResources<Resource<Person>> findByCustomContraints(
            @PageableDefault(size = 1, page = 0, direction = Sort.Direction.DESC) final Pageable pageable) {

        try {
            Page<Person> result = repository
                    .findAll(
                            (QPerson.person.id.gt(100).or(QPerson.person.id.lt(10))
                                    .and(QPerson.person.name.startsWithIgnoreCase("c")
                                            .or(QPerson.person.name
                                                    .startsWithIgnoreCase("j")))),
                            pageable);

            return pageAssembler.toResource(result);
        } catch (final Exception e) {
            System.err.println(e);
            throw e;
        }

    }

}
