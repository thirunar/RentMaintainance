package com.rentmaintainance.app.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.R;
import com.rentmaintainance.app.model.Tenant;
import com.rentmaintainance.app.utils.DateUtil;

import static com.rentmaintainance.app.AllConstants.TENANT_STRING;

public class ViewTenantFragment extends Fragment {

    private TextView tenantNameValueTextView;
    private TextView dateOccupiedValueTextView;
    private TextView phoneNumberValueTextView;
    private TextView securityDepositValueTextView;
    private Button editTenantButton;
    private Context context;

    public ViewTenantFragment(Activity activity) {
        Context.getInstance().updateApplicationContext(activity);
    }

    public static ViewTenantFragment newInstance(Tenant tenant) {
        ViewTenantFragment fragment = new ViewTenantFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(TENANT_STRING, tenant);
        fragment.setArguments(bundle);

        return fragment;
    }

    public ViewTenantFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Tenant tenant = (Tenant) getArguments().get(TENANT_STRING);
        View rootView = inflater.inflate(R.layout.fragment_view_tenant, container, false);

        initializeView(rootView);

        tenantNameValueTextView.setText(tenant.name());
        dateOccupiedValueTextView.setText(DateUtil.formatDateTime(tenant.dateOccupied()));
        phoneNumberValueTextView.setText(tenant.phoneNumber());
        securityDepositValueTextView.setText(String.valueOf(tenant.securityDeposit()));

        return rootView;

    }

    private void initializeView(View rootView) {
        tenantNameValueTextView = (TextView) rootView.findViewById(R.id.tenantNameValueTextView);
        dateOccupiedValueTextView = (TextView) rootView.findViewById(R.id.dateOccupiedValueTextView);
        phoneNumberValueTextView = (TextView) rootView.findViewById(R.id.phoneNumberValueTextView);
        securityDepositValueTextView = (TextView) rootView.findViewById(R.id.securityDepositValueTextView);
        editTenantButton = (Button) rootView.findViewById(R.id.editTenantButton);
        context = Context.getInstance().updateApplicationContext(getActivity().getApplicationContext());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
