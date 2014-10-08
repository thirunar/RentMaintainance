package com.rentmaintainance.app;

import com.rentmaintainance.app.repository.*;
import com.rentmaintainance.app.service.IncomeService;
import com.rentmaintainance.app.service.PropertyService;
import com.rentmaintainance.app.service.TenantService;

public class Context {
    private android.content.Context applicationContext;
    public static Context context = new Context();

    private PropertyService propertyService;
    private TenantService tenantService;
    private IncomeService incomeService;

    private Repository repository;
    private PropertyRepository propertyRepository;
    private TenantRepository tenantRepository;
    private IncomeRepository incomeRepository;

    private AllProperties allProperties;
    private AllTenants allTenants;
    private AllIncome allIncome;

    public static Context getInstance() {
        return context;
    }

    public Repository initRepository() {
        if (repository == null) {
            this.repository = new Repository(applicationContext(), getTenantRepository(), getPropertyRepository(), getIncomeRepository());
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

}
