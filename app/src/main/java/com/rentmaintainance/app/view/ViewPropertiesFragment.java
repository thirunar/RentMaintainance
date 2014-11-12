package com.rentmaintainance.app.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.emilsjolander.components.StickyScrollViewItems.StickyScrollView;
import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.R;
import com.rentmaintainance.app.customviews.KenBurnsView;
import com.rentmaintainance.app.model.Property;

import java.util.List;

public class ViewPropertiesFragment extends android.app.Fragment {

    private static final String TAG = "ViewPropertiesFragment";
    private ListView propertiesListView;
    private int defaultHeaderTopPos;
    private int contentFrameLayoutPos;
    private DrawerLayout drawerLayout;
    private BottomMenuFragment bottomMenuFragment;


    public ViewPropertiesFragment(Activity activity) {
        Context.getInstance().updateApplicationContext(activity.getApplicationContext());
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_properties, container, false);

        initializeView(rootView);

        propertiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                getFragmentManager().beginTransaction()
                        .replace(R.id.container,
                                ViewPropertyFragment.newInstance((Property) parent.getItemAtPosition(position)))
                        .addToBackStack(null)
                        .commit();
            }
        });
        return rootView;
    }

    private void initializeView(View rootView) {
        KenBurnsView headerPicture = (KenBurnsView) rootView.findViewById(R.id.header_picture);
        headerPicture.setResourceIds(R.drawable.picture0, R.drawable.picture1);

        StickyScrollView stickyScroll = (StickyScrollView) rootView.findViewById(R.id.sticky_scroll);

        stickyScroll.setShadowHeight(50);

        propertiesListView = (ListView) rootView.findViewById(R.id.propertiesListView);

        List<Property> properties = Context.getInstance().allProperties().getAllProperties();

        PropertiesListAdaptor propertiesListAdaptor = new PropertiesListAdaptor(getActivity().getApplicationContext(), properties);
        propertiesListView.setAdapter(propertiesListAdaptor);

        bottomMenuFragment = (BottomMenuFragment) getFragmentManager().findFragmentById(R.id.fragment_bottom_menu);

        bottomMenuFragment.plusButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new AddPropertyFragment(getActivity()))
                        .commit();

            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
