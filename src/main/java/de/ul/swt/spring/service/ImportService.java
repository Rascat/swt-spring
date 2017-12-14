package de.ul.swt.spring.service;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import de.ul.swt.spring.domain.Event;
import de.ul.swt.spring.domain.Person;
import de.ul.swt.spring.repository.EventRepository;
import de.ul.swt.spring.repository.PersonRepository;

@Service
public class ImportService {

    private final PersonRepository personRegistry;

    private final EventRepository eventRepository;

    public ImportService(final PersonRepository personRegistry,
            final EventRepository eventRepository) {
        this.personRegistry = personRegistry;
        this.eventRepository = eventRepository;
    }

    public void importPersons(final String fileName)
            throws JsonProcessingException, IOException, ParseException {

        Preconditions.checkArgument(!Strings.isNullOrEmpty(fileName));

        final ObjectMapper mapper = new ObjectMapper();
        JsonNode result = mapper.readTree(new File(fileName));

        JsonNode persons = result.get("persons");
        for (int i = 0; i < persons.size(); i++) {
            JsonNode person = persons.get(i);

            String name = person.at("/name/first").asText();
            String firstName = person.at("/name/last").asText();

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date birth = df.parse(person.get("birthday").asText());

            Person pojo = new Person();
            pojo.setName(name);
            pojo.setFirstName(firstName);
            pojo.setBirthDay(birth);

            personRegistry.save(pojo);
        }

        Event myEvent = new Event();
        myEvent.setName("University Lesson");
        myEvent.setEventDate(new Date());
        myEvent.setParticipants(Lists.newArrayList(personRegistry
                .findAll(Lists.newArrayList(4L, 10L, 90L, 44L)).iterator()));

        eventRepository.save(myEvent);

    }
}