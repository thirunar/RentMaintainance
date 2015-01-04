package com.rentmaintainance.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Expense implements Parcelable{

    private String propertyId;
    private Float amount;
    private String details;
    private String category;
    private Date date;

    public Expense(Parcel parcel) {
        propertyId = parcel.readString();
        amount = parcel.readFloat();
        details = parcel.readString();
        category = parcel.readString();
        date = (Date) parcel.readValue(Date.class.getClassLoader());
    }

    public Expense() {

    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(propertyId);
        dest.writeFloat(amount);
        dest.writeString(details);
        dest.writeString(category);
        dest.writeValue(date);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

}
