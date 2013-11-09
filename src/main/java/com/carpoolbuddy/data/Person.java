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
    private String id;

    private String name;

    private String facebookId;

    private City from;

    private City to;


    public Person() {}

    public Person(String name, String facebookId, City from, City to) {
        this.name = name;
        this.facebookId = facebookId;
        this.from = from;
        this.to = to;
    }

    public String getId() {
        return id;
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

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
}
