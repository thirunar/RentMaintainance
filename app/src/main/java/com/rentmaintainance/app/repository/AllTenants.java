package com.rentmaintainance.app.repository;

import com.rentmaintainance.app.model.Tenant;

public class AllTenants {

    private TenantRepository tenantRepository;

    public AllTenants(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public long addTenant(Tenant tenant) {
        return tenantRepository.addTenant(tenant);
    }
}
