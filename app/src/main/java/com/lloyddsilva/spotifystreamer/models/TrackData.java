package com.lloyddsilva.spotifystreamer.models;

import android.os.Parcel;
import android.os.Parcelable;

import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Track;

/**
 * Created by lloyddsilva on 3/7/15.
 */
public class TrackData implements Parcelable {

    private String trackName;
    private String albumName;
    private String albumSmallImageUrl;
    private String albumLargeImageUrl;
    private String trackPreviewUrl;

    public TrackData(Track track) {
        this.trackName = track.name;
        this.albumName = track.album.name;
        for(Image image : track.album.images) {
            if(image.width >= 150 && image.width <= 300) {
                this.albumSmallImageUrl = image.url;
            } else if (image.width >= 450 && image.width <= 800) {
                this.albumLargeImageUrl = image.url;
            }
        }
        this.trackPreviewUrl = track.preview_url;
    }

    private TrackData(Parcel in) {
        this.trackName = in.readString();
        this.albumName = in.readString();
        this.albumSmallImageUrl = in.readString();
        this.albumLargeImageUrl = in.readString();
        this.trackPreviewUrl = in.readString();
    }

    public String getTrackPreviewUrl() {
        return trackPreviewUrl;
    }

    public void setTrackPreviewUrl(String trackPreviewUrl) {
        this.trackPreviewUrl = trackPreviewUrl;
    }

    public String getAlbumLargeImageUrl() {
        return albumLargeImageUrl;
    }

    public void setAlbumLargeImageUrl(String albumLargeImageUrl) {
        this.albumLargeImageUrl = albumLargeImageUrl;
    }

    public String getAlbumSmallImageUrl() {
        return albumSmallImageUrl;
    }

    public void setAlbumSmallImageUrl(String albumSmallImageUrl) {
        this.albumSmallImageUrl = albumSmallImageUrl;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(trackName);
        dest.writeString(albumName);
        dest.writeString(albumSmallImageUrl);
        dest.writeString(albumLargeImageUrl);
        dest.writeString(trackPreviewUrl);
    }

    public static final Parcelable.Creator<TrackData> CREATOR = new Creator<TrackData>() {
        @Override
        public TrackData createFromParcel(Parcel source) {
            return new TrackData(source);
        }

        @Override
        public TrackData[] newArray(int size) {
            return new TrackData[size];
        }
    };
}
