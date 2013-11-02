package com.carpoolbuddy.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: alexus
 * Date: 11/2/13
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@XmlRootElement
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private City from;

    private City to;


    public Person() {}

    public Person(String name, City from, City to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public void setTo(City to) {
        this.to = to;
    }
}
