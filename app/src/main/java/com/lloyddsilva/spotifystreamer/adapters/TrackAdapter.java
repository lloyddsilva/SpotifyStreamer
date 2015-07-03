package com.lloyddsilva.spotifystreamer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lloyddsilva.spotifystreamer.R;
import com.lloyddsilva.spotifystreamer.models.ArtistData;
import com.lloyddsilva.spotifystreamer.models.TrackData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lloyddsilva on 3/7/15.
 */
public class TrackAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<TrackData> mTracks;

    public TrackAdapter(Context context, ArrayList<TrackData> tracks) {
        this.mContext = context;
        this.mTracks = tracks;
    }

    @Override
    public int getCount() {
        return this.mTracks.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mTracks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null) {
            //brand new
            convertView = LayoutInflater.from(mContext).inflate(R.layout.top_tracks_item, null);
            holder = new ViewHolder();
            holder.albumCoverImageView = (ImageView) convertView.findViewById(R.id.albumCoverImageView);
            holder.trackNameTextView = (TextView) convertView.findViewById(R.id.trackNameTextView);
            holder.albumNameTextView = (TextView) convertView.findViewById(R.id.albumNameTextView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TrackData trackData = (TrackData) getItem(position);

        holder.trackNameTextView.setText(trackData.getTrackName());
        holder.albumNameTextView.setText(trackData.getAlbumName());
        Picasso.with(mContext)
                .load(trackData.getAlbumSmallImageUrl())
                .placeholder(R.drawable.album_placeholder)
                .error(R.drawable.album_placeholder)
                .resize(250, 250)
                .centerCrop()
                .into(holder.albumCoverImageView);

        return convertView;
    }

    private static class ViewHolder {
        ImageView albumCoverImageView;
        TextView trackNameTextView;
        TextView albumNameTextView;
    }
}
