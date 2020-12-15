package com.ece354.coconinoshopping_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private ListView listView;
    private Intent intent;
    private Cart cart;
    private ListAdapter listAdapter;

    private GroceryItem[] items = {
            new GroceryItem("Grapes", 2.59, R.drawable.grapes, "Fruit", "Bag of purple grapes"),
            new GroceryItem("Banana", 0.99, R.drawable.banana, "Fruit", "A single banana"),
            new GroceryItem("Toilet Paper", 5.00, R.drawable.toiletpaper, "Bathroom", "12 rolls of toilet paper"),
            new GroceryItem("Dish Soap", 2.49, R.drawable.dishsoap, "Cleaning", "Dish soap for cleaning dishes"),
            new GroceryItem("Chicken Nuggets", 5.99, R.drawable.chickennuggets, "Meat", "A bag of chicken nuggets"),
            new GroceryItem("Paper Towels", 1.50, R.drawable.papertowels, "Cleaning", "A roll of paper towels"),
            new GroceryItem("Steak", 6.87, R.drawable.steak, "Meat", "A cut of steak"),
            new GroceryItem("Butter", 1.99, R.drawable.butter, "Cooking", "A stick of salt less butter"),
            new GroceryItem("White Bread", 1.50, R.drawable.whitebread, "Grain", "A load of white bread")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(MainActivity.this, GroceryItemActivity.class);
        cart = new Cart();
        intent.putExtra("cart", cart);

        int[] imageIds = new int[items.length];
        String[] names = new String[items.length];
        String[] prices = new String[items.length];

        for (int i = 0; i < items.length; i++) {
            imageIds[i] = items[i].getImageId();
            names[i] = items[i].getName();
            prices[i] = String.format("$ %.2f", items[i].getPrice());
        }

        listAdapter = new ListAdapter(MainActivity.this, names, prices, imageIds);
    }

    @Override
    protected void onResume() {
        super.onResume();

        cart = getIntent().getParcelableExtra("cart");

        listView = (ListView) findViewById(R.id.lv_items);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listAdapter.notifyDataSetChanged();

                GroceryItem selected = items[i];

                intent.putExtra("selectedItem", selected);
                intent.putExtra("cart", cart);
                startActivity(intent);
            }
        });
    }

    public void cartButtonClicked(View v) {
        Intent myIntent = new Intent(MainActivity.this, CartActivity.class);
        myIntent.putExtra("cart", cart);
        startActivity(myIntent);
    }
}