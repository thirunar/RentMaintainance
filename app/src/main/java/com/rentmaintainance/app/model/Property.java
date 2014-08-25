package com.rentmaintainance.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Property implements Parcelable {

    private String houseNumber;
    private String name;
    private double rent;
    private String items;
    private String details;
    private String address;
    private Date date;

    public Property() {

    }

    public Property(Parcel parcel) {
        houseNumber = parcel.readString();
        name = parcel.readString();
        rent = parcel.readDouble();
        items = parcel.readString();
        details = parcel.readString();
        address = parcel.readString();
        date = new Date(parcel.readLong());
    }


    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(houseNumber);
        dest.writeString(name);
        dest.writeDouble(rent);
        dest.writeString(items);
        dest.writeString(details);
        dest.writeString(address);
        dest.writeLong(date.getTime());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Property createFromParcel(Parcel in) {
            return new Property(in);
        }

        public Property[] newArray(int size) {
            return new Property[size];
        }
    };
}

