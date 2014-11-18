package com.rentmaintainance.app.view;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.R;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = Context.getInstance().updateApplicationContext(getApplicationContext());
        context.initRepository();
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        getSupportFragmentManager().popBackStackImmediate();
        if (position == 0)
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new ViewPropertiesFragment(this))
                    .commit();
        if (position == 1)
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new AddTenantFragment(this))
                    .commit();
        if (position == 2)
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new AddIncomeFragment(this))
                    .commit();
        if (position == 3)
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new AddIncomeFragment(this))
                    .commit();
        if (position == 4)
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new ViewIncomesFragment(this))
                    .commit();

        if (position == 5)
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new ViewExpensesFragment(this))
                    .commit();

    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(R.drawable.abc_ab_transparent_light_holo));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(R.drawable.abc_ab_transparent_light_holo));
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
