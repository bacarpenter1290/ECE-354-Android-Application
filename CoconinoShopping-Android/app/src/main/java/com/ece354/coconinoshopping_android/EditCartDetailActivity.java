package com.ece354.coconinoshopping_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EditCartDetailActivity extends AppCompatActivity {

    Intent intent;
    Cart cart;
    CartDetail selectedDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cart_detail);
    }

    @Override
    protected void onResume() {
        super.onResume();

        intent = getIntent();
        cart = intent.getParcelableExtra("cart");
        selectedDetail = intent.getParcelableExtra("selectedDetail");

        TextView title = findViewById(R.id.txtEditCartTitle);
        TextView quantity = findViewById(R.id.editTextQuantity);

        title.setText(selectedDetail.getItem().getName());
        quantity.setText(String.valueOf(selectedDetail.getQuantity()));
    }

    public void backToCart(View v) {
        Intent myIntent = new Intent(EditCartDetailActivity.this, CartActivity.class);
        myIntent.putExtra("cart", cart);
        startActivity(myIntent);
        finish();
    }

    public void editCart(View v) {
        TextView txtQuantity = findViewById(R.id.editTextQuantity);
        int quantity = 0;
        try {
            String value = txtQuantity.getText().toString();
            quantity = Integer.parseInt(value);

            CharSequence text;

            if (quantity > 0) {
                text = "Quantity updated";
            }
            else {
                text = "Item removed";
            }

            CartDetail detail = new CartDetail(selectedDetail.getItem(), quantity);

            if (cart == null) {
                cart = new Cart();
            }

            cart.updateDetail(detail);

            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent myIntent = new Intent(EditCartDetailActivity.this, CartActivity.class);
            myIntent.putExtra("cart", cart);
            startActivity(myIntent);
            finish();
        }
        catch(Exception e) { }
    }
}