package com.whiter.kazimir.ui.activity;

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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.whiter.kazimir.App;
import com.whiter.kazimir.R;
import com.whiter.kazimir.adapter.StreetsAdapter;
import com.whiter.kazimir.event.RefreshEvent;
import com.whiter.kazimir.event.StreetsEvent;
import com.whiter.kazimir.model.Street;
import com.whiter.kazimir.service.ServiceCaller;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PlacesActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = PlacesActivity.class.getSimpleName();

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.nav_view)
    NavigationView navigationView;
    @InjectView(R.id.places_list)
    RecyclerView recyclerView;
    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    Bus bus;

    List<Street> streets;

    private StreetsAdapter streetsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        App.component().inject(this);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        setupDrawerContent();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        streetsAdapter = new StreetsAdapter(this);
        recyclerView.setAdapter(streetsAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (streets == null) {
            ServiceCaller.getStreets(this);
        }
    }

    @Override
    protected void onPause() {
        bus.unregister(this);
        super.onPause();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onStreetsEvent(StreetsEvent streetsEvent) {
        setStreets(streetsEvent.getStreets());
    }

    @Override
    public void onRefresh() {
        ServiceCaller.refreshStreets(this);
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent refreshEvent) {
        boolean success = refreshEvent.isSuccess();
        if (success) {
            setStreets(refreshEvent.getStreets());
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    private void setStreets(List<Street> streets) {
        if (streets == null) {
            Log.w(TAG, "streets are null");
            return;
        }
        streetsAdapter.swapStreets(streets);
    }

    private void setupDrawerContent() {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }


}
