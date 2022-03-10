package com.spring.mvc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;

//Client-это запись/заявка в парикмахерскую
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String name;
    private String dateVisit;
    private String phone;
    private String serviceType;

    public Client() {
    }

    protected Client(String name, String dateVisit, String phone, String serviceType){
        this.name = name;
        this.dateVisit = dateVisit;
        this.phone = phone;
        this.serviceType = serviceType;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(String dateVisit) {
        this.dateVisit = dateVisit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

}

