package com.rentmaintainance.app.service;

import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.model.Property;
import com.rentmaintainance.app.repository.AllProperties;

import java.io.File;
import java.io.IOException;

public class PropertyService {

    private AllProperties allProperties;
    private Context context;

    public PropertyService() {
        context = Context.getInstance();
        allProperties = context.allProperties();
    }

    public void addProperty(Property property) {
        allProperties.addPropertyDetails(property);
    }
    
    public void generateCSV(File file) throws IOException {
        context.getCSVUtil().generateCSV(allProperties.getCursorForAllProperties(), file);
    }
}
