package com.ece354.coconinoshopping_android;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Cart implements Parcelable {
    private List<CartDetail> details;

    public Cart() {
        details = new ArrayList<CartDetail>();
    }

    public Cart(List<CartDetail> cartDetails) {
        details = cartDetails;
    }

    protected Cart(Parcel in) {
        details = in.createTypedArrayList(CartDetail.CREATOR);
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public List<CartDetail> getDetails() {
        return details;
    }

    public void setDetails(List<CartDetail> details) {
        this.details = details;
    }

    public void updateDetail (CartDetail detail) {
        for (int i = 0; i < details.size(); i++) {
            if (details.get(i).getItem().getName().equals(detail.getItem().getName())) {
                if(detail.getQuantity() == 0)
                    details.remove(i);
                else
                    details.get(i).setQuantity(detail.getQuantity());
            }
        }
    }

    public Double getTotal() {
        Double total = 0.0;
        for (int i = 0; i < details.size(); i++) {
            total += details.get(i).getItem().getPrice() * details.get(i).getQuantity();
        }
        return total;
    }

    public void addDetail(CartDetail detail) {
        details.add(detail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(details);
    }
}
