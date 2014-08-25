package com.rentmaintainance.app.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.rentmaintainance.app.AllConstants;
import com.rentmaintainance.app.R;
import com.rentmaintainance.app.model.Property;
import com.rentmaintainance.app.repository.Repository;
import com.rentmaintainance.app.utils.DoubleUtil;

import java.util.Date;

public class EditPropertyFragment extends Fragment {

    private EditText houseNumberEditText;
    private EditText houseNameEditText;
    private EditText rentEditText;
    private EditText itemsEditText;
    private EditText detailsEditText;
    private EditText addressEditText;
    private Button editPropertyButton;
    private Repository repository;

    public EditPropertyFragment() {
    }

    public static EditPropertyFragment newInstance(Property property) {
        EditPropertyFragment fragment = new EditPropertyFragment();

        Bundle args = new Bundle();
        args.putParcelable(AllConstants.PROPERTY_STRING, property);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_property, container, false);

        initializeView(rootView);
        repository = new Repository(getActivity().getApplicationContext());

        initializeProperty();

        editPropertyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Property property = getPropertyDetails();

                repository.updatePropertyDetails(property);
            }
        });

        return rootView;
    }

    private void initializeProperty() {
        Property property = (Property) getArguments().get("property");

        houseNumberEditText.setText(property.getHouseNumber());
        houseNameEditText.setText(property.getName());
        itemsEditText.setText(property.getItems());
        rentEditText.setText(String.valueOf(property.getRent()));
        detailsEditText.setText(property.getDetails());
        addressEditText.setText(property.getAddress());
    }

    private Property getPropertyDetails() {
        Property property = new Property();

        property.setHouseNumber(houseNumberEditText.getText().toString());
        property.setName(houseNameEditText.getText().toString());
        property.setDetails(detailsEditText.getText().toString());
        property.setRent(DoubleUtil.tryParse(rentEditText.getText().toString(), 0));
        property.setItems(itemsEditText.getText().toString());
        property.setAddress(addressEditText.getText().toString());
        property.setDate(new Date());

        return property;
    }

    private void initializeView(View rootView) {
        houseNumberEditText = (EditText) rootView.findViewById(R.id.houseNumberEditText);
        houseNameEditText = (EditText) rootView.findViewById(R.id.houseNameEditText);
        rentEditText = (EditText) rootView.findViewById(R.id.rentEditText);
        itemsEditText = (EditText) rootView.findViewById(R.id.itemsEditText);
        detailsEditText = (EditText) rootView.findViewById(R.id.detailsEditText);
        addressEditText = (EditText) rootView.findViewById(R.id.addressEditText);
        editPropertyButton = (Button) rootView.findViewById(R.id.editPropertyButton);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
