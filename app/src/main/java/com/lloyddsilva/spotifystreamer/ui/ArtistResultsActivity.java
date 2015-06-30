package com.lloyddsilva.spotifystreamer.ui;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lloyddsilva.spotifystreamer.R;
import com.lloyddsilva.spotifystreamer.adapters.ArtistAdapter;
import com.lloyddsilva.spotifystreamer.models.ArtistData;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;

public class ArtistResultsActivity extends ListActivity {
    private ArrayList<ArtistData> mArtists;
    ArtistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_results);
        mArtists = new ArrayList<ArtistData>();

        handleIntent(getIntent());

        adapter = new ArtistAdapter(this, mArtists);
        setListAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_artist_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        // Verify the action and get the query
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }

    public void doSearch(String query) {

        ArtistQueryTask task = new ArtistQueryTask();
        task.execute(new String[] { query });

    }

    private class ArtistQueryTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... queries) {
            String response = "";
            for(String query : queries) {

                try {
                    //Connect to the Spotify API with the wrapper
                    SpotifyApi api = new SpotifyApi();
                    //Create a SpotifyService object that we can use to get desired data
                    SpotifyService spotify = api.getService();

                    ArtistsPager results = spotify.searchArtists(query);

                    mArtists.clear();
                    for(Artist artist : results.artists.items) {
                        mArtists.add(new ArtistData(artist));
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
