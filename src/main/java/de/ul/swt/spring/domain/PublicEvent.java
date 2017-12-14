package de.ul.swt.spring.domain;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode(of = {}, callSuper = true)
public class PublicEvent extends Event {

    @Getter
    @Setter
    private String location;
}
