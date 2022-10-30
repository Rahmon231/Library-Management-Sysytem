package com.lemzeeyyy.booklistapp.model;

import javax.annotation.Generated;

import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ImageLinks implements Parcelable
{

    @SerializedName("smallThumbnail")
    @Expose
    private String smallThumbnail;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    public final static Creator<ImageLinks> CREATOR = new Creator<ImageLinks>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ImageLinks createFromParcel(android.os.Parcel in) {
            return new ImageLinks(in);
        }

        public ImageLinks[] newArray(int size) {
            return (new ImageLinks[size]);
        }

    }
            ;

    protected ImageLinks(android.os.Parcel in) {
        this.smallThumbnail = ((String) in.readValue((String.class.getClassLoader())));
        this.thumbnail = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ImageLinks() {
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(smallThumbnail);
        dest.writeValue(thumbnail);
    }

    public int describeContents() {
        return 0;
    }

}