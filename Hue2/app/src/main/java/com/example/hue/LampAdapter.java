package com.example.hue;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hue.fragments.detailfragment;
import com.example.hue.model.Light;
import com.example.hue.model.Lighting;
import com.example.hue.service.HueService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LampAdapter extends RecyclerView.Adapter<LampAdapter.LampViewHolder> {

    private List<Light> mLampList;
    private final LayoutInflater mInflator;
    private Fragment mMasterfragment;
    private HueService hueService;

    public LampAdapter(Context context, List<Light> projectList, Fragment masterFragment) {
        this.mMasterfragment = masterFragment;
        mInflator = LayoutInflater.from(context);
        this.mLampList = projectList;
        this.hueService = new HueService();
    }

    public void clear() {
        mLampList.clear();
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<Light> list) {
        mLampList.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mLampList.size();
    }


    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method should update the contents of the ViewHolder.itemView to
     * reflect the item at the given position.
     *
     * @param holder   The ViewHolder which should be updated to represent
     *                 the contents of the item at the given position in the
     *                 data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(LampAdapter.LampViewHolder holder, int position) {
        Light current = mLampList.get(position);
        String mCurrent = current.getName();
        holder.LampNameItemView.setText(mCurrent);

        boolean mState = current.getState().isOn();
        holder.LampSwitchItemView.setChecked(mState);
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to
     * represent an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can
     * represent the items of the given type. You can either create a new View
     * manually or inflate it from an XML layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * onBindViewHolder(ViewHolder, int, List). Since it will be reused to
     * display different items in the data set, it is a good idea to cache
     * references to sub views of the View to avoid unnecessary findViewById()
     * calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after
     *                 it is bound to an adapter position.
     * @param viewType The view type of the new View. @return A new ViewHolder
     *                 that holds a View of the given view type.
     */
    @Override
    public LampAdapter.LampViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate an item view.
        View mItemView = mInflator.inflate(
                R.layout.lamp_item, parent, false);

        return new LampViewHolder(mItemView, this);
    }







    class LampViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView LampNameItemView;
        public final Switch LampSwitchItemView;
        public final ImageView LampImageItemView;
        private final Context context;
        final LampAdapter mAdapter;

        public LampViewHolder(View itemView, LampAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            LampNameItemView = itemView.findViewById(R.id.LampName);
            LampSwitchItemView = (Switch) itemView.findViewById(R.id.LampSwitch);
            LampSwitchItemView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int mPosition = getLayoutPosition();
                    Light element = mLampList.get(mPosition);
                    element.getState().setOn(LampSwitchItemView.isChecked());
                    hueService.updateOn(Integer.toString(mPosition + 1), LampSwitchItemView.isChecked());
                    if (LampSwitchItemView.isChecked()){
                        float[] HueColors = new float[3];
                        HueColors[0] = (float) element.getState().getHue();
                        HueColors[1] = (float) element.getState().getSat();
                        HueColors[2] = (float) element.getState().getBri();
                        int setColor = Color.HSVToColor(HueColors);
                        LampImageItemView.setBackgroundColor(setColor);
                    }else{
                        LampImageItemView.setBackgroundColor(Color.BLACK);
                    }
                }
            });
            LampImageItemView = itemView.findViewById(R.id.preview_master_color);
            if (LampSwitchItemView.isChecked()){
                int mPosition = getLayoutPosition();
                Light element = mLampList.get(mPosition);
                float[] HueColors = new float[3];
                HueColors[0] = (float) element.getState().getHue();
                HueColors[1] = (float) element.getState().getSat();
                HueColors[2] = (float) element.getState().getBri();
                int setColor = Color.HSVToColor(HueColors);
                LampImageItemView.setBackgroundColor(setColor);
            }else{
                LampImageItemView.setBackgroundColor(Color.BLACK);
            }

            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Intent intent;
            int mPosition = getLayoutPosition();
            Light element = mLampList.get(mPosition);
            if (element!=null){
                Fragment LampDetailFragment = new detailfragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("LampInfo",  element); // Key, value
                bundle.putString("LampKey", Integer.toString(mPosition + 1)); //
                LampDetailFragment.setArguments(bundle);
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.Container, LampDetailFragment).commit();
            }
        }
    }

}
