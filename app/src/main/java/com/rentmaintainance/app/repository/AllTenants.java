package com.rentmaintainance.app.repository;

import android.util.SparseArray;
import com.rentmaintainance.app.model.Tenant;

import java.util.List;

public class AllTenants {

    private TenantRepository tenantRepository;

    public AllTenants(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public long addTenant(Tenant tenant) {
        return tenantRepository.addTenant(tenant);
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.getAllTenants();
    }

    public SparseArray<SparseArray<String>> getAllTenantsData() {
        return tenantRepository.getAllTenantsData();
    }


}
