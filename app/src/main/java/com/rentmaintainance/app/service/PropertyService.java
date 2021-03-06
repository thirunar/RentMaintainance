package com.rentmaintainance.app.service;

import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.model.Property;
import com.rentmaintainance.app.repository.AllProperties;
import com.rentmaintainance.app.utils.DateUtil;
import jxl.write.WriteException;

import java.io.IOException;
import java.util.Date;

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
    
}
