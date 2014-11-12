package com.rentmaintainance.app.customviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.rentmaintainance.app.R;
import it.gmariotti.cardslib.library.internal.Card;

public class CustomCard extends Card {

    protected TextView cardHeaderTextView;
    protected TextView cardDetailsTextView;
    protected TextView cardDateTextView;
    protected TextView cardAmountTextView;

    private String amount;
    /**
     * Constructor with a custom inner layout
     *
     * @param context
     * @param card_inner_layout
     * @param s
     */
    public CustomCard(Context context, int card_inner_layout, String amount) {
        this(context, R.layout.card_inner_layout);
        this.amount = amount;
    }

    /**
     * @param context
     * @param innerLayout
     */
    public CustomCard(Context context, int innerLayout) {
        super(context, innerLayout);
        init();
    }

    /**
     * Init
     */
    private void init() {

        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getContext(), "Click Listener card=" + cardHeaderTextView.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        View innerView = LayoutInflater.from(mContext)
                .inflate(R.layout.card_inner_layout, parent, false);
        cardHeaderTextView = (TextView) innerView.findViewById(R.id.cardHeader);
        cardDetailsTextView = (TextView) innerView.findViewById(R.id.cardDetails);
        cardDateTextView = (TextView) innerView.findViewById(R.id.cardDate);
        cardAmountTextView = (TextView) innerView.findViewById(R.id.cardAmount);
        cardAmountTextView.setText(amount);
    }

    public void setHeader(String headerText) {
        cardHeaderTextView.setText(headerText);
        Toast.makeText(getContext(), "Click Listener card =" + cardHeaderTextView.getText().toString(), Toast.LENGTH_LONG).show();
    }

    public void setDetails(String detailsText) {
        cardDetailsTextView.setText(detailsText);
    }

    public void setDate(String dateText) {
        cardDateTextView.setText(dateText);
    }

    public void setAmount(String amountText) {
        cardAmountTextView.setText(amountText);
    }
}