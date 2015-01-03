package com.rentmaintainance.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Tenant implements Parcelable {

    private String id;
    private String name;
    private String phoneNumber;
    private Date dateOccupied;
    private Date dateVacate;
    private String status;
    private Float securityDeposit;

    public Tenant(Parcel parcel) {
        id = parcel.readString();
        name = parcel.readString();
        phoneNumber = parcel.readString();
        dateOccupied = (Date) parcel.readValue(Date.class.getClassLoader());
        dateVacate = (Date) parcel.readValue(Date.class.getClassLoader());
        status = parcel.readString();
        securityDeposit = parcel.readFloat();
    }

    public Tenant(String id, String name, String phoneNumber, Date dateOccupied, Date dateVacate, String status, Float securityDeposit) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateOccupied = dateOccupied;
        this.dateVacate = dateVacate;
        this.status = status;
        this.securityDeposit = securityDeposit;
    }

    public Tenant() {

    }

    public String name() {
        return name;
    }

    public Tenant withName(String name) {
        this.name = name;
        return this;
    }

    public String phoneNumber() {
        return phoneNumber;
    }

    public Tenant withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Date dateOccupied() {
        return dateOccupied;
    }

    public Tenant withDateOccupied(Date dateOccupied) {
        this.dateOccupied = dateOccupied;
        return this;
    }

    public Date dateVacate() {
        return dateVacate;
    }

    public Tenant withDateVacate(Date dateVacate) {
        this.dateVacate = dateVacate;
        return this;
    }

    public String status() {
        return status;
    }

    public Tenant withStatus(String status) {
        this.status = status;
        return this;
    }

    public String id() {
        return id;
    }

    public Tenant withId(String id) {
        this.id = id;
        return this;
    }

    public Float securityDeposit() {
        return securityDeposit;
    }

    public Tenant withSecurityDeposit(Float securityDeposit) {
        this.securityDeposit = securityDeposit;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(phoneNumber);
        dest.writeValue(dateOccupied);
        dest.writeValue(dateVacate);
        dest.writeString(status);
        dest.writeFloat(securityDeposit);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Tenant createFromParcel(Parcel in) {
            return new Tenant(in);
        }

        public Tenant[] newArray(int size) {
            return new Tenant[size];
        }
    };

}
