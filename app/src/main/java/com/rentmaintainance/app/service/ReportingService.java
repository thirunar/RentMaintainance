package com.rentmaintainance.app.service;

import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.repository.AllExpense;
import com.rentmaintainance.app.repository.AllIncome;
import com.rentmaintainance.app.repository.AllProperties;
import com.rentmaintainance.app.repository.AllTenants;
import jxl.write.WriteException;

import java.io.IOException;
import java.util.Date;

import static com.rentmaintainance.app.AllConstants.*;
import static com.rentmaintainance.app.AllConstants.PROPERTY_DETAILS;

public class ReportingService {

    private AllProperties allProperties;
    private AllIncome allIncome;
    private AllTenants allTenants;
    private AllExpense allExpense;
    private Context context;

    public ReportingService() {
        context = Context.getInstance();
        allProperties = context.allProperties();
        allIncome = context.allIncome();
        allTenants = context.allTenants();
        allExpense = context.allExpense();
    }

    public void getReportForAllProperties() throws IOException {
        try {
            context.getCSVUtil().write(allProperties.getAllPropertiesData(), PROPERTY_DETAILS + new Date().getTime() + XLS_EXTENSION);
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    public void getReportForAllIncomes() throws IOException {
        try {
            context.getCSVUtil().write(allIncome.getAllIncomeData(), INCOME_DETAILS + new Date().getTime() + XLS_EXTENSION);
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    public void getReportForAllTenants() throws IOException {
        try {
            context.getCSVUtil().write(allTenants.getAllTenantsData(), TENANT_DETAILS + new Date().getTime() + XLS_EXTENSION);
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    public void getReportForAllExpenses() throws IOException {
        try {
            context.getCSVUtil().write(allExpense.getAllExpenseData(), EXPENSE_DETAILS + new Date().getTime() + XLS_EXTENSION);
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
