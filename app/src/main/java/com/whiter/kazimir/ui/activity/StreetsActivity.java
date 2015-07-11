package com.whiter.kazimir.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.whiter.kazimir.App;
import com.whiter.kazimir.R;
import com.whiter.kazimir.adapter.StreetsAdapter;
import com.whiter.kazimir.databinding.StreetsActivityBinding;
import com.whiter.kazimir.model.Street;
import com.whiter.kazimir.presenter.StreetsPresenter;
import com.whiter.kazimir.ui.decorator.SimpleDividerItemDecoration;
import com.whiter.kazimir.ui.view.RecyclerItemClickListener;
import com.whiter.kazimir.utils.Intents;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class StreetsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, StreetsPresenter.Contract {

    private static final String TAG = StreetsActivity.class.getSimpleName();

    private StreetsAdapter streetsAdapter;

    @Inject
    StreetsPresenter streetsPresenter;

    @Inject
    Intents intents;
    private StreetsActivityBinding streetsActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        streetsActivityBinding = DataBindingUtil.setContentView(this, R.layout.streets_activity);
        ButterKnife.inject(this);
        setSupportActionBar(streetsActivityBinding.toolbar);
        App.component().inject(this);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        setupDrawerContent();
        streetsActivityBinding.streetsList.setLayoutManager(new LinearLayoutManager(this));
        streetsActivityBinding.streetsList.addItemDecoration(new SimpleDividerItemDecoration(this));
        streetsAdapter = new StreetsAdapter(this);
        streetsActivityBinding.streetsList.setAdapter(streetsAdapter);
        streetsActivityBinding.streetsList.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Street street = streetsAdapter.getStreet(position);
                intents.startPlaceListActivity(StreetsActivity.this, street);
            }
        }));
        streetsActivityBinding.swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                streetsActivityBinding.drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        streetsActivityBinding.navView.getMenu().getItem(0).setChecked(true);
        streetsPresenter.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        streetsPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        streetsPresenter.onDestroy();
    }

    @Override
    public void onRefresh() {
        streetsPresenter.refresh();
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        streetsActivityBinding.swipeRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public void setStreets(List<Street> streets) {
        streetsAdapter.swapStreets(streets);
    }

    private void setupDrawerContent() {
        streetsActivityBinding.navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        StreetsActivity.this.onNavigationItemSelected(menuItem);
                        return true;
                    }
                });
    }

    private void onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        if (R.id.nav_map == menuItem.getItemId()) {
            goToMap();
        }
        streetsActivityBinding.drawerLayout.closeDrawers();
    }

    private void goToMap() {
        intents.startMapActivity(this, streetsPresenter.getStreets());
    }
}
