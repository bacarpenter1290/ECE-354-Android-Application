package com.ece354.coconinoshopping_android;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class GroceryItem implements Parcelable {

    private String name;
    private double price;
    private int imageId;
    private String category;
    private String comments;

    public GroceryItem() {
        name = "";
        price = 0;
        imageId = 0;
        category = "";
        comments = "";
    }

    public GroceryItem(String aName, double aPrice, int anImageId, String aCategory, String aComment) {
        name = aName;
        price = aPrice;
        imageId = anImageId;
        category = aCategory;
        comments = aComment;
    }

    protected GroceryItem(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        imageId = in.readInt();
        category = in.readString();
        comments = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeInt(imageId);
        dest.writeString(category);
        dest.writeString(comments);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GroceryItem> CREATOR = new Creator<GroceryItem>() {
        @Override
        public GroceryItem createFromParcel(Parcel in) {
            return new GroceryItem(in);
        }

        @Override
        public GroceryItem[] newArray(int size) {
            return new GroceryItem[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
