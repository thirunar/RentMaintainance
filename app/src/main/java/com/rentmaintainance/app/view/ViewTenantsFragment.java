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
import com.rentmaintainance.app.model.Tenant;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;

import java.util.ArrayList;
import java.util.List;

import static com.rentmaintainance.app.AllConstants.TENANT_STRING;

public class ViewTenantsFragment extends Fragment {

    private static final String TAG = "ViewTenantsFragment";
    private ListView tenantsListView;
    private FloatingActionButton floatingActionButton;


    public ViewTenantsFragment(Activity activity) {
        Context.getInstance().updateApplicationContext(activity.getApplicationContext());
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_tenants, container, false);
        initializeView(view);
        return view;
    }

    private void initializeView(final View rootView) {
        setupKenBurnsView(rootView);
        setupStickyScrollView(rootView);
        setupTenantsListView(rootView);
        setupActionButton(rootView);
    }

    private void setupActionButton(View rootView) {
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.add_tenant_button);
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new AddTenantFragment(getActivity()))
                        .commit();
            }
        });
    }

    private void setupTenantsListView(View rootView) {
        tenantsListView = (ListView) rootView.findViewById(R.id.tenantsListView);
        List<Tenant> tenants = Context.getInstance().allTenants().getAllTenants();
        CardArrayAdapter tenantsArrayAdapter = new CardArrayAdapter(getActivity(), getCardsFor(tenants));
        tenantsListView.setAdapter(tenantsArrayAdapter);
    }


    private List<Card> getCardsFor(List<Tenant> tenants) {
        List<Card> cards = new ArrayList<Card>();
        for (final Tenant tenant : tenants) {
            CardHeader cardheader = setupCardHeader(tenant);
            Card card = setCardDetails(tenant, cardheader);
            setCardThumbnail(card);

            final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            card.setOnClickListener(new Card.OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    ViewTenantFragment fragment = new ViewTenantFragment(getActivity());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(TENANT_STRING, tenant);
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

    private void setCardThumbnail(Card card) {
        CardThumbnail cardThumbnail = new CardThumbnail(getActivity());
        cardThumbnail.setDrawableResource(R.drawable.ic_tenant);

        card.addCardThumbnail(cardThumbnail);
    }

    private Card setCardDetails(Tenant tenant, CardHeader cardheader) {
        Card card = new Card(getActivity().getApplicationContext());
        card.setTitle(tenant.phoneNumber());
        card.addCardHeader(cardheader);
        return card;
    }

    private CardHeader setupCardHeader(Tenant tenant) {
        CardHeader cardheader = new CardHeader(getActivity());
        cardheader.setTitle(tenant.name());
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
