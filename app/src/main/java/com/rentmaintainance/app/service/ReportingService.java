package com.rentmaintainance.app.service;

import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.repository.AllProperties;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ReportingService {

    private AllProperties allProperties;
    private Context context;

    public ReportingService() {
        context = Context.getInstance();
        allProperties = context.allProperties();
    }

    public void getReportForAllProperties() throws IOException {
        try {
            context.getCSVUtil().write(allProperties.getAllPropertiesData(), "PropertyDetails" + new Date().getTime() + ".xls");
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
