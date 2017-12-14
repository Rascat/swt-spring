package de.ul.swt.spring.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

import de.ul.swt.spring.domain.Event;
import de.ul.swt.spring.domain.Person;
import de.ul.swt.spring.domain.PublicEvent;
import de.ul.swt.spring.domain.SecretEvent;
import de.ul.swt.spring.validation.SecretEventValidator;

@Component
public class RestConfiguration extends RepositoryRestConfigurerAdapter {

    public RestConfiguration() {
        super();
    }

    @Override
    public void configureRepositoryRestConfiguration(
            final RepositoryRestConfiguration config) {

        config.exposeIdsFor(Person.class);
        config.exposeIdsFor(Event.class);
        config.exposeIdsFor(PublicEvent.class);
        config.exposeIdsFor(SecretEvent.class);

    }

    @Override
    public void configureValidatingRepositoryEventListener(
            final ValidatingRepositoryEventListener validatingListener) {

        validatingListener.addValidator("beforeCreate", new SecretEventValidator());

    }
}
