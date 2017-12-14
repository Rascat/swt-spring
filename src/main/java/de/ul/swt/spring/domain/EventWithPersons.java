package de.ul.swt.spring.domain;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "withPersons", types = { Event.class })
public interface EventWithPersons extends EventPersonCount {

    public List<Person> getParticipants();
}
