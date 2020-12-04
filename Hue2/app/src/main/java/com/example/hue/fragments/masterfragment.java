package com.example.hue.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.hue.LampAdapter;
import com.example.hue.R;
import com.example.hue.model.Light;
import com.example.hue.model.Lighting;
import com.example.hue.service.HueService;

import java.util.ArrayList;
import java.util.List;

public class masterfragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LampAdapter mAdapter;
    private List<Light> mLampList;
    private SwipeRefreshLayout swipeContainer;
    private HueService hueService = new HueService();

    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.masterfragment, container, false);
        mLampList = new ArrayList<Light>(Lighting.getINSTANCE().getLights().values());

        // Create recycler view.
        mRecyclerView = RootView.findViewById(R.id.recyclerView);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new LampAdapter(getContext(), mLampList, this);
        // Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        swipeContainer = (SwipeRefreshLayout) RootView.findViewById(R.id.swipeContainer);

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchLampListAsync();
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        return RootView;
    }

    public void fetchLampListAsync() {
        hueService.getLights(true);

        mAdapter.clear();
        mAdapter.addAll(new ArrayList<Light>(Lighting.getINSTANCE().getLights().values()));

        //Source: https://guides.codepath.com/android/implementing-pull-to-refresh-guide
        Toast toast = Toast.makeText(getContext().getApplicationContext(), getResources().getString(R.string.list_refreshed), Toast. LENGTH_SHORT); toast. show();
        swipeContainer.setRefreshing(false);

    }


}
