package com.rentmaintainance.app;

import com.rentmaintainance.app.repository.AllProperties;
import com.rentmaintainance.app.repository.PropertyRepository;
import com.rentmaintainance.app.repository.Repository;

import static com.rentmaintainance.app.AllConstants.DATABASE_NAME;

public class Context {
    private android.content.Context applicationContext;
    public static Context context = new Context();

    private Repository repository;
    private PropertyRepository propertyRepository;

    private AllProperties properties;

    public static Context getInstance() {
        return context;
    }

    public Repository initRepository() {
        if (repository == null) {
            return new Repository(applicationContext(), DATABASE_NAME, getPropertyRepository());
        }
        return repository;
    }

    private PropertyRepository getPropertyRepository() {
        if (propertyRepository == null) {
            this.propertyRepository = new PropertyRepository();
        }
        return propertyRepository;
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
        if (properties == null) {
            return new AllProperties(getPropertyRepository());
        }
        return properties;
    }

}
