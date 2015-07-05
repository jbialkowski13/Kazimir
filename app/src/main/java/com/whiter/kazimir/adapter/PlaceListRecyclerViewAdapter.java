package com.whiter.kazimir.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.whiter.kazimir.R;
import com.whiter.kazimir.model.Place;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by whiter
 */
public class PlaceListRecyclerViewAdapter extends RecyclerView.Adapter<PlaceListRecyclerViewAdapter.ViewHolder> {

    private List<Place> places;
    private Context context;

    public PlaceListRecyclerViewAdapter(List<Place> places, Context context) {
        this.places = places;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.place_list_row, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Place place = places.get(position);

        holder.placeName.setText(place.getDetails().getDetails().getName());
        holder.placeDescription.setText(place.getDetails().getDetails().getDescription());

        String medium = place.getPhotos().get(0).getImages().getMedium();
        Glide.with(context).load(medium).into(holder.placeImage);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @InjectView(R.id.place_list_row_title)
        TextView placeName;

        @InjectView(R.id.place_list_row_image)
        ImageView placeImage;

        @InjectView(R.id.place_list_row_description)
        TextView placeDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }

}
