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

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    private List<Place> places;
    private Context context;

    private ItemClickListener itemClickListener;

    public PlaceListRecyclerViewAdapter(List<Place> places, Context context) {
        this.places = places;
        this.context = context;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
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

        InternalClickListener internalClickListener = new InternalClickListener(position);
        holder.placeImage.setOnClickListener(internalClickListener);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.place_list_row_title)
        TextView placeName;

        @InjectView(R.id.place_list_row_image)
        ImageView placeImage;

        @InjectView(R.id.place_list_row_description)
        TextView placeDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    private class InternalClickListener implements View.OnClickListener {

        private static final int DEFAULT_POSITION = -1;
        private int position = DEFAULT_POSITION;

        public InternalClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (DEFAULT_POSITION == position) {
                return;
            }
            if (itemClickListener == null) {
                return;
            }
            switch (v.getId()) {
                case R.id.place_list_row_image:
                    itemClickListener.onItemClick(position);
                    break;
            }
        }
    }

}
