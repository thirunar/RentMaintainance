package com.rentmaintainance.app.model;

import java.util.Date;

public class Tenant {

    private String id;
    private String name;
    private String phoneNumber;
    private Date dateOccupied;
    private Date dateVacate;
    private String status;

    public Tenant() {
    }

    public Tenant(String id, String name, String phoneNumber, Date dateOccupied, Date dateVacate, String status) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateOccupied = dateOccupied;
        this.dateVacate = dateVacate;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public Tenant withName(String name) {
        this.name = name;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Tenant withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Date getDateOccupied() {
        return dateOccupied;
    }

    public Tenant withDateOccupied(Date dateOccupied) {
        this.dateOccupied = dateOccupied;
        return this;
    }

    public Date getDateVacate() {
        return dateVacate;
    }

    public Tenant withDateVacate(Date dateVacate) {
        this.dateVacate = dateVacate;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Tenant withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getId() {
        return id;
    }

    public Tenant withId(String id) {
        this.id = id;
        return this;
    }
}
