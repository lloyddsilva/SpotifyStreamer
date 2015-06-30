package com.lloyddsilva.spotifystreamer.models;

import android.os.Parcel;
import android.os.Parcelable;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by lloyddsilva on 30/6/15.
 */
public class ArtistData implements Parcelable {

    private String artistId;
    private String artistName;
    private String artistImageUrl;

    public ArtistData(Artist artist) {
        this.artistId = artist.id;
        this.artistName = artist.name;
        this.artistImageUrl = artist.images.get(0).url;
    }

    private ArtistData(Parcel in) {
        this.artistId = in.readString();
        this.artistName = in.readString();
        this.artistImageUrl = in.readString();
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistImageUrl() {
        return artistImageUrl;
    }

    public void setArtistImageUrl(String artistImageUrl) {
        this.artistImageUrl = artistImageUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(artistId);
        dest.writeString(artistName);
        dest.writeString(artistImageUrl);
    }

    public static final Parcelable.Creator<ArtistData> CREATOR = new Creator<ArtistData>() {
        @Override
        public ArtistData createFromParcel(Parcel source) {
            return new ArtistData(source);
        }

        @Override
        public ArtistData[] newArray(int size) {
            return new ArtistData[size];
        }
    };
}
