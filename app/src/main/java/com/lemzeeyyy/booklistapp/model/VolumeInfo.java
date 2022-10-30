package com.lemzeeyyy.booklistapp.model;

import java.util.List;
import javax.annotation.Generated;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class VolumeInfo implements Parcelable
{

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("authors")
    @Expose
    private List<String> authors = null;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;

    @SerializedName("publisher")
    @Expose
    private String publisher;

    @SerializedName("publishedDate")
    @Expose
    private String publishedDate;

    @SerializedName("contentVersion")
    @Expose
    private String contentVersion;

    @SerializedName("previewLink")
    @Expose
    private String previewLink;

    @SerializedName("infoLink")
    @Expose
    private String infoLink;

    @SerializedName("averageRating")
    @Expose
    private Integer averageRating;

    @SerializedName("imageLinks")
    @Expose
    private ImageLinks imageLinks;

    public final static Creator<VolumeInfo> CREATOR = new Creator<VolumeInfo>() {

        @SuppressWarnings({
                "unchecked"
        })

        public VolumeInfo createFromParcel(android.os.Parcel in) {
            return new VolumeInfo(in);
        }

        public VolumeInfo[] newArray(int size) {
            return (new VolumeInfo[size]);
        }

    }
            ;

    protected VolumeInfo(android.os.Parcel in) {
        this.title = ((String) in.readValue((String.class.getClassLoader())));

        in.readList(this.authors, (java.lang.String.class.getClassLoader()));

        this.description = ((String) in.readValue((String.class.getClassLoader())));

        this.pageCount = ((Integer) in.readValue((Integer.class.getClassLoader())));

        this.publisher = ((String) in.readValue((String.class.getClassLoader())));

        this.publishedDate = ((String) in.readValue((String.class.getClassLoader())));

        this.contentVersion = ((String) in.readValue((String.class.getClassLoader())));

        this.previewLink = ((String) in.readValue((String.class.getClassLoader())));

        this.infoLink = ((String) in.readValue((String.class.getClassLoader())));

        this.averageRating = ((Integer) in.readValue((Integer.class.getClassLoader())));

        this.imageLinks = ((ImageLinks) in.readValue((ImageLinks.class.getClassLoader())));
       }

    public VolumeInfo() {
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPageCount() {
        return pageCount;
    }
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getContentVersion() {
        return contentVersion;
    }
    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public String getPreviewLink() {
        return previewLink;
    }
    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }
    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public Integer getAverageRating() {
        return averageRating;
    }
    public void setAverageRating(Integer averageRating) {
        this.averageRating = averageRating;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }
    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeList(authors);

        dest.writeValue(description);
        dest.writeValue(pageCount);

        dest.writeValue(publisher);
        dest.writeValue(publishedDate);

        dest.writeValue(contentVersion);
        dest.writeValue(previewLink);

        dest.writeValue(infoLink);
        dest.writeValue(averageRating);

        dest.writeValue(imageLinks);

    }

    public int describeContents() {
        return 0;
    }

}