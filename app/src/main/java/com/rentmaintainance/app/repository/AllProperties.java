package com.rentmaintainance.app.repository;

import android.database.sqlite.SQLiteException;
import com.rentmaintainance.app.model.Property;

import java.util.List;

public class AllProperties {

    private PropertyRepository propertyRepository;

    public AllProperties(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public long addPropertyDetails(Property property) throws SQLiteException {
        return propertyRepository.addPropertyDetails(property);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.getAllProperties();
    }

    public long updatePropertyDetails(Property property) {
        return propertyRepository.updatePropertyDetails(property);
    }
}
