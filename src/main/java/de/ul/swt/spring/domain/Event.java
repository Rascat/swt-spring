package de.ul.swt.spring.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(of = {}, callSuper = true)
public class Event extends BaseEntity {

    @Getter
    @Setter
    @Nonnull
    private String name;

    @Getter
    @Setter
    private Date eventDate;

    @Getter
    @Setter
    @ManyToMany
    private List<Person> participants;

    public Event() {
        participants = new ArrayList<>();
    }

}
