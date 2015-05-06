package com.rentmaintainance.app;

import com.rentmaintainance.app.repository.*;
import com.rentmaintainance.app.service.*;
import com.rentmaintainance.app.utils.CSVUtil;

public class Context {
    private android.content.Context applicationContext;
    public static Context context = new Context();

    private PropertyService propertyService;
    private TenantService tenantService;
    private IncomeService incomeService;
    private ExpenseService expenseService;
    private ReportingService reportingService;

    private Repository repository;
    private PropertyRepository propertyRepository;
    private TenantRepository tenantRepository;
    private IncomeRepository incomeRepository;
    private ExpenseRepository expenseRepository;

    private AllProperties allProperties;
    private AllTenants allTenants;
    private AllIncome allIncome;
    private AllExpense allExpense;
    private CSVUtil csvUtil;

    public static Context getInstance() {
        return context;
    }

    public Repository initRepository() {
        if (repository == null) {
            this.repository = new Repository(applicationContext(), getTenantRepository(), getPropertyRepository(), getIncomeRepository(), getExpenseRepository());
        }
        return repository;
    }

    private PropertyRepository getPropertyRepository() {
        if (propertyRepository == null) {
            this.propertyRepository = new PropertyRepository();
        }
        return propertyRepository;
    }

    private TenantRepository getTenantRepository() {
        if (tenantRepository == null) {
            this.tenantRepository = new TenantRepository();
        }
        return tenantRepository;
    }

    private IncomeRepository getIncomeRepository() {
        if (incomeRepository == null) {
            this.incomeRepository = new IncomeRepository();
        }
        return incomeRepository;
    }

    private ExpenseRepository getExpenseRepository() {
        if (expenseRepository == null) {
            this.expenseRepository = new ExpenseRepository();
        }
        return expenseRepository;
    }

    public android.content.Context applicationContext() {
        return applicationContext;
    }

    public Context updateApplicationContext(android.content.Context applicationContext) {
        this.applicationContext = applicationContext;
        return this;
    }

    public AllProperties allProperties() {
        initRepository();
        if (allProperties == null) {
            this.allProperties = new AllProperties(getPropertyRepository());
        }
        return allProperties;
    }

    public AllTenants allTenants() {
        initRepository();
        if (allTenants == null) {
            this.allTenants = new AllTenants(getTenantRepository());
        }
        return allTenants;
    }

    public AllIncome allIncome() {
        initRepository();
        if (allIncome == null) {
            this.allIncome = new AllIncome(getIncomeRepository());
        }
        return allIncome;
    }

    public AllExpense allExpense() {
        initRepository();
        if (allExpense == null) {
            this.allExpense = new AllExpense(getExpenseRepository());
        }
        return allExpense;
    }

    public TenantService getTenantService() {
        if (tenantService == null) {
            this.tenantService = new TenantService();
        }
        return tenantService;
    }

    public PropertyService getPropertyService() {
        if (propertyService == null) {
            this.propertyService = new PropertyService();
        }
        return propertyService;
    }

    public IncomeService getIncomeService() {
        if (incomeService == null) {
            this.incomeService = new IncomeService();
        }
        return incomeService;
    }

    public ExpenseService getExpenseService() {
        if (expenseService == null) {
            this.expenseService = new ExpenseService();
        }
        return expenseService;
    }

    public ReportingService getReportingService() {
        if (reportingService == null) {
            this.reportingService = new ReportingService();
        }
        return reportingService;
    }
    
    public CSVUtil getCSVUtil() {
        if (csvUtil == null) {
            this.csvUtil = new CSVUtil();
        }
        return csvUtil;
    }

}
