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
import com.rentmaintainance.app.model.Property;

import static com.rentmaintainance.app.AllConstants.PROPERTY_STRING;

public class ViewPropertyFragment extends Fragment {

    private TextView houseNumberValueTextView;
    private TextView houseNameValueTextView;
    private TextView rentValueTextView;
    private TextView itemsValueTextView;
    private TextView detailsValueTextView;
    private TextView addressValueTextView;
    private Button editPropertyButton;
    private Context context;

    public static ViewPropertyFragment newInstance(Property property) {
        ViewPropertyFragment fragment = new ViewPropertyFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(PROPERTY_STRING, property);
        fragment.setArguments(bundle);

        return fragment;
    }

    public ViewPropertyFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Property property = (Property) getArguments().get(PROPERTY_STRING);
        View rootView = inflater.inflate(R.layout.fragment_view_property, container, false);

        initializeView(rootView);

        houseNumberValueTextView.setText(property.getHouseNumber());
        houseNameValueTextView.setText(property.getName());
        rentValueTextView.setText(String.valueOf(property.getRent()));
        itemsValueTextView.setText(property.getItems());
        detailsValueTextView.setText(property.getDetails());
        addressValueTextView.setText(property.getAddress());

        editPropertyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction()
                        .replace(R.id.container,
                                EditPropertyFragment.newInstance(property))
                        .addToBackStack(null)
                        .commit();

            }
        });

        return rootView;

    }

    private void initializeView(View rootView) {
        houseNumberValueTextView = (TextView) rootView.findViewById(R.id.houseNumberValueTextView);
        houseNameValueTextView = (TextView) rootView.findViewById(R.id.houseNameValueTextView);
        rentValueTextView = (TextView) rootView.findViewById(R.id.rentValueTextView);
        itemsValueTextView = (TextView) rootView.findViewById(R.id.itemsValueTextView);
        detailsValueTextView = (TextView) rootView.findViewById(R.id.detailsValueTextView);
        addressValueTextView = (TextView) rootView.findViewById(R.id.addressValueTextView);
        editPropertyButton = (Button) rootView.findViewById(R.id.editPropertyButton);
        context = Context.getInstance().updateApplicationContext(getActivity().getApplicationContext());
        context.initRepository();
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
