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
import com.rentmaintainance.app.model.Income;
import com.rentmaintainance.app.utils.DateUtil;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;

import java.util.ArrayList;
import java.util.List;

import static com.rentmaintainance.app.AllConstants.INCOME_STRING;

public class ViewIncomesFragment extends Fragment {

    private static final String TAG = "ViewIncomesFragment";
    private FloatingActionButton floatingActionButton;

    public ViewIncomesFragment(Activity activity) {
        Context.getInstance().updateApplicationContext(activity.getApplicationContext());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_incomes, container, false);
        initializeView(view);
        return view;
    }

    private void initializeView(final View rootView) {
        setupKenBurnsView(rootView);
        setupStickyScrollView(rootView);
        setupIncomeListView(rootView);
        setupActionButton(rootView);
    }

    private void setupActionButton(View rootView) {
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.add_income_button);
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new AddIncomeFragment(getActivity()))
                        .commit();
            }
        });
    }

    private void setupIncomeListView(View rootView) {
        ListView incomesListView = (ListView) rootView.findViewById(R.id.incomesListView);
        List<Income> incomes = Context.getInstance().allIncome().getAllIncome();
        CardArrayAdapter incomeListAdaptor = new CardArrayAdapter(getActivity(), getCardsFor(incomes));
        incomesListView.setAdapter(incomeListAdaptor);
    }

    private void setupStickyScrollView(View rootView) {
        StickyScrollView stickyScroll = (StickyScrollView) rootView.findViewById(R.id.sticky_scroll);
        stickyScroll.setShadowHeight(50);
    }

    private void setupKenBurnsView(View rootView) {
        KenBurnsView headerPicture = (KenBurnsView) rootView.findViewById(R.id.header_picture);
        headerPicture.setResourceIds(R.drawable.picture0, R.drawable.picture1);
    }

    private List<Card> getCardsFor(List<Income> incomes) {
        List<Card> cards = new ArrayList<Card>();
        for (final Income income : incomes) {
            CardHeader cardheader = new CardHeader(getActivity());
            cardheader.setTitle(income.details());

            Card card = new Card(getActivity().getApplicationContext());
            card.setTitle(Float.toString(income.amount()) + " \nDate: " + DateUtil.formatDateTime(income.date()));
            card.addCardHeader(cardheader);

            final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            card.setOnClickListener(new Card.OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    ViewIncomeFragment fragment = new ViewIncomeFragment(getActivity());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(INCOME_STRING, income);
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
