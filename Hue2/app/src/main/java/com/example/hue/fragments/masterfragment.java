package com.example.hue.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.hue.Lamp;
import com.example.hue.LampAdapter;
import com.example.hue.R;
import com.example.hue.model.Light;
import com.example.hue.model.Lighting;
import com.example.hue.service.HueService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class masterfragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LampAdapter mAdapter;
    private List<Light> mLampList;
    private SwipeRefreshLayout swipeContainer;
    private HueService service = new HueService();

    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.masterfragment, container, false);
        mLampList = new ArrayList<Light>(Lighting.getINSTANCE().getLights().values());
//        Button SettingsButton = (Button) RootView.findViewById(R.id.SettingsButton);
//        SettingsButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                final Intent intent;
//                    Fragment LampDetailFragment = new settingsfragment();
//                    //((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.Container, LampDetailFragment).commit();
//
//            }
//        });
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
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
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
        //Source: https://guides.codepath.com/android/implementing-pull-to-refresh-guide

        //TODO: remove testcode:
        Toast toast = Toast. makeText(getContext().getApplicationContext(), "You refreshed the list", Toast. LENGTH_SHORT); toast. show();
        swipeContainer.setRefreshing(false);
        //End testcode

        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.

        //TODO: fix this to acttually work
        //TODO: this is the handler to pull new data from the emulator
//        client.getHomeTimeline(new OkHttpResponseHandler() {
//            public void onSuccess() {
//                mAdapter.clear();
//                mAdapter.addAll(new LinkedList<Lamp>());
//                swipeContainer.setRefreshing(false);
//            }
//
//            public void onFailure(Throwable e) {
//                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
//            }
//        });
    }


}
