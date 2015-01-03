package com.rentmaintainance.app.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.emilsjolander.components.StickyScrollViewItems.StickyScrollView;
import com.melnykov.fab.FloatingActionButton;
import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.R;
import com.rentmaintainance.app.customviews.KenBurnsView;
import com.rentmaintainance.app.model.Property;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;

import java.util.ArrayList;
import java.util.List;

import static com.rentmaintainance.app.AllConstants.PROPERTY_STRING;

public class ViewPropertiesFragment extends Fragment {

    private static final String TAG = "ViewPropertiesFragment";
    private ListView propertiesListView;
    private FloatingActionButton floatingActionButton;


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

        View view = inflater.inflate(R.layout.fragment_view_properties, container, false);
        initializeView(view);
        return view;
    }

    private void initializeView(final View rootView) {
        setupKenBurnsView(rootView);
        setupStickyScrollView(rootView);
        setupPropertiesListView(rootView);
        setupActionButton(rootView);
    }

    private void setupActionButton(View rootView) {
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.add_property_button);
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new AddPropertyFragment(getActivity()))
                        .commit();
            }
        });
    }

    private void setupPropertiesListView(View rootView) {
        propertiesListView = (ListView) rootView.findViewById(R.id.propertiesListView);
        List<Property> properties = Context.getInstance().allProperties().getAllProperties();
        CardArrayAdapter propertiesArrayAdapter = new CardArrayAdapter(getActivity(), getCardsFor(properties));
        propertiesListView.setAdapter(propertiesArrayAdapter);
    }


    private List<Card> getCardsFor(List<Property> properties) {
        List<Card> cards = new ArrayList<Card>();
        for (final Property property : properties) {
            CardHeader cardheader = setupCardHeader(property);

            Card card = setupCardDetails(property, cardheader);
            setupCardThumbnail(card);

            final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            card.setOnClickListener(new Card.OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    ViewPropertyFragment fragment = new ViewPropertyFragment(getActivity());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(PROPERTY_STRING, property);
                    fragment.setArguments(bundle);
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .commit();
                }
            });

            cards.add(card);
        }
        return cards;
    }

    private void setupCardThumbnail(Card card) {
        CardThumbnail cardThumbnail = new CardThumbnail(getActivity());
        cardThumbnail.setDrawableResource(R.drawable.ic_home);

        card.addCardThumbnail(cardThumbnail);
    }

    private Card setupCardDetails(Property property, CardHeader cardheader) {
        Card card = new Card(getActivity().getApplicationContext());
        card.setTitle(property.getName());
        card.addCardHeader(cardheader);
        return card;
    }

    private CardHeader setupCardHeader(Property property) {
        CardHeader cardheader = new CardHeader(getActivity());
        cardheader.setTitle(property.getHouseNumber());
        return cardheader;
    }

    private void setupKenBurnsView(View rootView) {
        KenBurnsView headerPicture = (KenBurnsView) rootView.findViewById(R.id.header_picture);
        headerPicture.setResourceIds(R.drawable.picture0, R.drawable.picture1);
    }

    private void setupStickyScrollView(View rootView) {
        StickyScrollView stickyScroll = (StickyScrollView) rootView.findViewById(R.id.sticky_scroll);
        stickyScroll.setShadowHeight(50);
    }


}
