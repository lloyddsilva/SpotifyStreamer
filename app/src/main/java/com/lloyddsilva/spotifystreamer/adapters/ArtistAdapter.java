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

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by lloyddsilva on 29/6/15.
 */
public class ArtistAdapter extends BaseAdapter {
    private Context mContext;
    private Artist[] mArtists;

    public ArtistAdapter(Context context, Artist[] artists) {
        this.mContext = context;
        this.mArtists = artists;
    }

    @Override
    public int getCount() {
        return this.mArtists.length;
    }

    @Override
    public Object getItem(int position) {
        return this.mArtists[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        SquaredImageView view = (SquaredImageView) convertView;
//        if(view == null) {
//            view = new SquaredImageView(mContext);
//        }
//
//        Artist artist = (Artist) getItem(position);

        //String url = artist.images.get(0);

        //Picasso.with(mContext).load(url).into(view);

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

        Artist artist = (Artist) getItem(position);
        ArtistData artistData = new ArtistData(artist);

        holder.artistNameTextView.setText(artistData.getArtistName());
        Picasso.with(mContext).load(artistData.getArtistImageUrl()).into(holder.artistCoverImageView);

        return null;
    }

    private static class ViewHolder {
        ImageView artistCoverImageView;
        TextView artistNameTextView;
    }
}
