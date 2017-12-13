package de.ul.swt.spring.domain;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "nameOnly", types = { Person.class })
public interface SimplePersonProjection {
    String getFirstName();

    String getName();
}
