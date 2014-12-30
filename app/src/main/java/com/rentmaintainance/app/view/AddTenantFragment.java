package com.rentmaintainance.app.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.R;
import com.rentmaintainance.app.model.Tenant;
import com.rentmaintainance.app.service.TenantService;
import com.rentmaintainance.app.utils.DateUtil;

public class AddTenantFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private EditText tenantNameEditText;
    private EditText dateOccupiedEditText;
    private EditText phoneNumberEditText;
    private EditText statusEditText;
    private Button addTenantButton;
    private TenantService tenantService;
    private EditText securityDepositEditText;

    public AddTenantFragment(Activity activity) {
        context = Context.getInstance();
        Context.getInstance().updateApplicationContext(activity);
        tenantService = context.getTenantService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_tenant, container, false);

        initializeView(rootView);

        return rootView;
    }

    private Tenant getTenanantDetails() {
        Tenant tenant = new Tenant();
        tenant.withName(tenantNameEditText.getText().toString())
                .withPhoneNumber(phoneNumberEditText.getText().toString())
                .withDateOccupied(DateUtil.getDateTime(dateOccupiedEditText.getText().toString()))
                .withSecurityDeposit(Float.parseFloat(securityDepositEditText.getText().toString()))
                .withStatus("yes");
        return tenant;
    }

    private void initializeView(View rootView) {
        tenantNameEditText = (EditText) rootView.findViewById(R.id.tenantNameEditText);
        dateOccupiedEditText = (EditText) rootView.findViewById(R.id.dateOccupiedEditText);
        phoneNumberEditText = (EditText) rootView.findViewById(R.id.phoneNumberEditText);
        securityDepositEditText = (EditText) rootView.findViewById(R.id.securityDepositEditText);
        addTenantButton = (Button) rootView.findViewById(R.id.addTenantButton);

        addTenantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenantService.addTenant(getTenanantDetails());
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.addTenantButton:

        }
    }
}
