package com.ece354.coconinoshopping_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GroceryItemActivity extends AppCompatActivity {

    Intent intent;
    Cart cart;
    GroceryItem selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        intent = getIntent();
        cart = intent.getParcelableExtra("cart");
        selectedItem = (GroceryItem) intent.getParcelableExtra("selectedItem");

        TextView txtTitle = findViewById(R.id.txtItemTitle);
        TextView txtPrice = findViewById(R.id.txtPrice);
        TextView txtCategory = findViewById(R.id.txtCategory);
        TextView txtComments = findViewById(R.id.txtComments);

        ImageView imageView = findViewById(R.id.imageItem);

        txtTitle.setText(selectedItem.getName());
        txtPrice.setText(String.format("$ %.2f", selectedItem.getPrice()));
        txtCategory.setText(selectedItem.getCategory());
        txtComments.setText(selectedItem.getComments());

        imageView.setImageResource(selectedItem.getImageId());
    }

    public void backToMain(View v) {
        Intent myIntent = new Intent(GroceryItemActivity.this, MainActivity.class);
        myIntent.putExtra("selectedItem", selectedItem);
        myIntent.putExtra("cart", cart);
        startActivity(myIntent);
        finish();
    }

    public void addToCart(View v) {
        Intent myIntent = new Intent(GroceryItemActivity.this, AddToCartActivity.class);
        myIntent.putExtra("selectedItem", selectedItem);
        myIntent.putExtra("cart", cart);
        startActivity(myIntent);
        finish();
    }
}