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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by lloyddsilva on 29/6/15.
 */
public class ArtistAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ArtistData> mArtists;

    public ArtistAdapter(Context context, ArrayList<ArtistData> artists) {
        this.mContext = context;
        this.mArtists = artists;
    }

    @Override
    public int getCount() {
        return this.mArtists.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mArtists.get(position);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.artist_results_item, null);
            holder = new ViewHolder();
            holder.artistCoverImageView = (ImageView) convertView.findViewById(R.id.artistCoverImageView);
            holder.artistNameTextView = (TextView) convertView.findViewById(R.id.artistNameTextView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ArtistData artistData = (ArtistData) getItem(position);

        holder.artistNameTextView.setText(artistData.getArtistName());
        Picasso.with(mContext)
                .load(artistData.getArtistImageUrl())
                .placeholder(R.drawable.album_placeholder)
                .error(R.drawable.album_placeholder)
                .resize(250, 250)
                .centerCrop()
                .into(holder.artistCoverImageView);

        return convertView;
    }

    private static class ViewHolder {
        ImageView artistCoverImageView;
        TextView artistNameTextView;
    }
}
