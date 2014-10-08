package com.rentmaintainance.app.model;

import java.util.Date;

public class Income {
    private String propertyId;
    private Float amount;
    private String details;
    private Date date;

    public String propertyId() {
        return propertyId;
    }

    public Income withPropertyId(String propertyId) {
        this.propertyId = propertyId;
        return this;
    }

    public Float amount() {
        return amount;
    }

    public Income withAmount(Float amount) {
        this.amount = amount;
        return this;
    }

    public String details() {
        return details;
    }

    public Income withDetails(String details) {
        this.details = details;
        return this;
    }

    public Date date() {
        return date;
    }

    public Income withDate(Date date) {
        this.date = date;
        return this;
    }
}
