package com.rentmaintainance.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Income implements Parcelable {
    private String propertyId;
    private Float amount;
    private String details;
    private Date date;

    public Income(Parcel parcel) {
        propertyId = parcel.readString();
        amount = parcel.readFloat();
        details = parcel.readString();
        date = (Date) parcel.readValue(Date.class.getClassLoader());
    }

    public Income() {

    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(propertyId);
        dest.writeFloat(amount);
        dest.writeString(details);
        dest.writeValue(date);
    }

    public static final Creator CREATOR = new Creator() {
        public Income createFromParcel(Parcel in) {
            return new Income(in);
        }

        public Income[] newArray(int size) {
            return new Income[size];
        }
    };

}
