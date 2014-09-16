package com.rentmaintainance.app.service;

import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.model.Tenant;
import com.rentmaintainance.app.repository.AllTenants;

public class TenantService {
    private AllTenants allTenants;
    private Context context;

    public TenantService() {
        context = Context.getInstance();
        this.allTenants = context.allTenants();
    }

    public long addTenant(Tenant tenant) {
        return allTenants.addTenant(tenant);
    }
}
