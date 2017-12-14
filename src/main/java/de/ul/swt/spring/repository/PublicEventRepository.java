package de.ul.swt.spring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import de.ul.swt.spring.domain.PublicEvent;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "publicevent", path = "publicevent")
public interface PublicEventRepository
        extends PagingAndSortingRepository<PublicEvent, Long> {

}
