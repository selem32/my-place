package com.app.myplaces.service.model.location;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LocationItem implements Parcelable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("review")
    private double review;
    @SerializedName("type")
    private String type;

    private String urlImage;

    protected LocationItem(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        review = in.readDouble();
        type = in.readString();
        urlImage = in.readString();
    }

    public static final Creator<LocationItem> CREATOR = new Creator<LocationItem>() {
        @Override
        public LocationItem createFromParcel(Parcel in) {
            return new LocationItem(in);
        }

        @Override
        public LocationItem[] newArray(int size) {
            return new LocationItem[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getReview() {
        return review;
    }

    public String getType() {
        return type;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(name);
        parcel.writeDouble(review);
        parcel.writeString(type);
        parcel.writeString(urlImage);
    }
}
