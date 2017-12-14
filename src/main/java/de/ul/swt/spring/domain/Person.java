package de.ul.swt.spring.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(of = {}, callSuper = true)
public class Person extends BaseEntity {

    private String name;

    private String firstName;

    private Date birthDay;

    @ManyToMany(mappedBy = "participants")
    private final List<Event> events;

    public Person() {
        events = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Transient
    public int getAge() {
        return Years.yearsBetween(new LocalDate(birthDay), new LocalDate()).getYears();
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

}
