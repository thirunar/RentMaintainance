package com.rentmaintainance.app.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.rentmaintainance.app.R;

public class BottomMenuFragment extends Fragment implements OnClickListener {

    private ImageButton plusButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_menu, container, false);
        plusButton = (ImageButton) view.findViewById(R.id.plusButton);

        plusButton.setOnClickListener(this);

        return view;
    }

    public ImageButton plusButton() {
        return plusButton;
    }
    @Override
    public void onClick(View v) {
            FragmentManager fragmentManager = getFragmentManager();
        switch (v.getId()) {
            case R.id.plusButton:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new AddPropertyFragment(getActivity()))
                        .commit();

                break;
        }
    }
}

class Animal {

}

class Dog extends  Animal{

}
