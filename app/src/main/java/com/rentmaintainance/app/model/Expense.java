package com.rentmaintainance.app.model;

import java.util.Date;

public class Expense {

    private String propertyId;
    private Float amount;
    private String details;
    private String category;
    private Date date;

    public String propertyId() {
        return propertyId;
    }

    public Expense withPropertyId(String propertyId) {
        this.propertyId = propertyId;
        return this;
    }

    public Float amount() {
        return amount;
    }

    public Expense withAmount(Float amount) {
        this.amount = amount;
        return this;
    }

    public String details() {
        return details;
    }

    public Expense withDetails(String details) {
        this.details = details;
        return this;
    }

    public String category() {
        return category;
    }

    public Expense withCategory(String category) {
        this.category = category;
        return this;
    }

    public Date date() {
        return date;
    }

    public Expense withDate(Date date) {
        this.date = date;
        return this;
    }
}
