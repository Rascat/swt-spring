package de.ul.swt.spring.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "personCount", types = { Event.class })
public interface EventPersonCount {

    public String getName();

    @Value("#{target.participants.size()}")
    public int getNumberOfParticipants();

}
