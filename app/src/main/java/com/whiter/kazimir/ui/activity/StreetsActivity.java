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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.whiter.kazimir.App;
import com.whiter.kazimir.R;
import com.whiter.kazimir.adapter.StreetsAdapter;
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

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.nav_view)
    NavigationView navigationView;
    @InjectView(R.id.streets_list)
    RecyclerView recyclerView;
    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private StreetsAdapter streetsAdapter;

    @Inject
    StreetsPresenter streetsPresenter;

    @Inject
    Intents intents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.streets_activity);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        App.component().inject(this);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        setupDrawerContent();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        streetsAdapter = new StreetsAdapter(this);
        recyclerView.setAdapter(streetsAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Street street = streetsAdapter.getStreet(position);
                intents.startPlaceListActivity(StreetsActivity.this, street);
            }
        }));
        swipeRefreshLayout.setOnRefreshListener(this);
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

    @Override
    protected void onResume() {
        super.onResume();
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
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public void setStreets(List<Street> streets) {
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
