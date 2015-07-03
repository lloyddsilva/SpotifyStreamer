package com.lloyddsilva.spotifystreamer.ui;

import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lloyddsilva.spotifystreamer.R;
import com.lloyddsilva.spotifystreamer.adapters.ArtistAdapter;
import com.lloyddsilva.spotifystreamer.adapters.TrackAdapter;
import com.lloyddsilva.spotifystreamer.models.ArtistData;
import com.lloyddsilva.spotifystreamer.models.TrackData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;

public class TopTenTracksActivity extends ActionBarActivity {
    private ArrayList<TrackData> mTracks;
    TrackAdapter adapter;
    ListView tracksListView;
    TextView noTracksTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten_tracks);

        mTracks = new ArrayList<TrackData>();

        handleIntent(getIntent());

        adapter = new TrackAdapter(this, mTracks);
        tracksListView = (ListView) findViewById(R.id.tracksListView);
        noTracksTextView = (TextView) findViewById(R.id.noTracksTextView);

        tracksListView.setEmptyView(noTracksTextView);
        tracksListView.setAdapter(adapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        String artistId = intent.getStringExtra(MainActivity.EXTRA_ARTIST_ID);
        loadTopTracks(artistId);
    }

    public void loadTopTracks(String artistId) {
        TopTenTracksQueryTask task = new TopTenTracksQueryTask();
        task.execute(new String[]{artistId });
    }

    private class TopTenTracksQueryTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... queries) {
            String response = "";
            for(String query : queries) {

                try {
                    //Connect to the Spotify API with the wrapper
                    SpotifyApi api = new SpotifyApi();
                    //Create a SpotifyService object that we can use to get desired data
                    SpotifyService spotify = api.getService();

                    // set country
                    Map<String, Object> options = new HashMap<>();
                    options.put("country", "US");

                    Tracks results = spotify.getArtistTopTrack(query, options);

                    mTracks.clear();
                    for(Track track : results.tracks ) {
                        mTracks.add(new TrackData(track));
                    }

                    response = results.toString();
                } catch (Exception e) {
                    response = e.getStackTrace().toString();
                }
            }

            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            adapter.notifyDataSetChanged();
            System.out.println(result);
        }
    }
}
