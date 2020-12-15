package com.ece354.coconinoshopping_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddToCartActivity extends AppCompatActivity {

    Intent intent;
    Cart cart;
    GroceryItem selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
    }

    @Override
    protected void onResume() {
        super.onResume();

        intent = getIntent();
        cart = intent.getParcelableExtra("cart");
        selectedItem = (GroceryItem) intent.getParcelableExtra("selectedItem");

        TextView txtTitle = findViewById(R.id.txtEditCartTitle);
        txtTitle.setText(selectedItem.getName());
    }

    public void backToItem(View v) {
        Intent myIntent = new Intent(AddToCartActivity.this, GroceryItemActivity.class);
        myIntent.putExtra("selectedItem", selectedItem);
        myIntent.putExtra("cart", cart);
        startActivity(myIntent);
        finish();
    }

    public void addToCart(View v) {
        TextView txtQuantity = findViewById(R.id.editTextQuantity);
        int quantity = 0;
        try {
            String value = txtQuantity.getText().toString();
            quantity = Integer.parseInt(value);

            if (quantity > 0) {
                CartDetail detail = new CartDetail(selectedItem, quantity);

                if (cart == null) {
                    cart = new Cart();
                }
                boolean newItem = true;
                CharSequence text = "";
                for (int i = 0; i < cart.getDetails().size(); i++) {
                    if (cart.getDetails().get(i).getItem().getName().equals(detail.getItem().getName())) {
                        detail.setQuantity(detail.getQuantity() + cart.getDetails().get(i).getQuantity());
                        cart.updateDetail(detail);
                        text = "More items added to cart";
                        newItem = false;
                    }
                }
                if (newItem) {
                    cart.addDetail(detail);
                    text = "Item added to cart";
                }

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Intent myIntent = new Intent(AddToCartActivity.this, MainActivity.class);
                myIntent.putExtra("selectedItem", selectedItem);
                myIntent.putExtra("cart", cart);
                startActivity(myIntent);
                finish();
            }
            else {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                CharSequence text = "Please enter a valid number (> 0)";
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        catch(Exception e) { }
    }
}