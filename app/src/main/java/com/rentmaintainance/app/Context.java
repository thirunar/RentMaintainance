package com.rentmaintainance.app;

import com.rentmaintainance.app.repository.*;
import com.rentmaintainance.app.service.PropertyService;
import com.rentmaintainance.app.service.TenantService;

public class Context {
    private android.content.Context applicationContext;
    public static Context context = new Context();

    private PropertyService propertyService;
    private TenantService tenantService;

    private Repository repository;
    private PropertyRepository propertyRepository;
    private TenantRepository tenantRepository;

    private AllProperties allProperties;
    private AllTenants allTenants;

    public static Context getInstance() {
        return context;
    }

    public Repository initRepository() {
        if (repository == null) {
            this.repository = new Repository(applicationContext(), getTenantRepository(), getPropertyRepository());
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

}
