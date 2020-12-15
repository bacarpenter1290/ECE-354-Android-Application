package com.ece354.coconinoshopping_android;

import android.os.Parcel;
import android.os.Parcelable;

public class CartDetail implements Parcelable {

    private GroceryItem item;
    private int quantity;

    public CartDetail() {
        item = new GroceryItem();
        quantity = 0;
    }

    public CartDetail(GroceryItem anItem, int aQuantity) {
        item = anItem;
        quantity = aQuantity;
    }

    protected CartDetail(Parcel in) {
        item = in.readParcelable(GroceryItem.class.getClassLoader());
        quantity = in.readInt();
    }

    public static final Creator<CartDetail> CREATOR = new Creator<CartDetail>() {
        @Override
        public CartDetail createFromParcel(Parcel in) {
            return new CartDetail(in);
        }

        @Override
        public CartDetail[] newArray(int size) {
            return new CartDetail[size];
        }
    };

    public GroceryItem getItem() {
        return item;
    }

    public void setItem(GroceryItem item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(item, flags);
        dest.writeInt(quantity);
    }
}
