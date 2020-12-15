package com.ece354.coconinoshopping_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    Intent intent;
    Cart cart;
    ListAdapter listAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean emptyCart = false;
        intent = getIntent();
        cart = intent.getParcelableExtra("cart");
        TextView txtTotal = findViewById(R.id.txtTotal);

        if (cart != null) {
            List<CartDetail> items = cart.getDetails();

            if (items.size() > 0) {
                Double total = cart.getTotal();
                txtTotal.setText("Total: $ " + String.format("%.2f", total));

                int[] imageIds = new int[items.size()];
                String[] names = new String[items.size()];
                String[] quantities = new String[items.size()];

                for (int i = 0; i < items.size(); i++) {
                    imageIds[i] = items.get(i).getItem().getImageId();
                    names[i] = items.get(i).getItem().getName();
                    quantities[i] = "Quantity: " + String.valueOf(items.get(i).getQuantity());
                }

                listAdapter = new ListAdapter(CartActivity.this, names, quantities, imageIds);

                listView = (ListView) findViewById(R.id.lv_items);
                listView.setAdapter(listAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        listAdapter.notifyDataSetChanged();

                        CartDetail selected = cart.getDetails().get(i);

                        Intent myIntent = new Intent(CartActivity.this, EditCartDetailActivity.class);
                        myIntent.putExtra("selectedDetail", selected);
                        myIntent.putExtra("cart", cart);
                        startActivity(myIntent);
                    }
                });
            }
            else {
                emptyCart = true;
            }
        }
        else {
            emptyCart = true;
        }

        if(emptyCart) {
            Context context = getApplicationContext();
            CharSequence text = "No items in cart";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            txtTotal.setText("Total: $ 0.00");
        }
    }

    public void backToMain(View v) {
        Intent myIntent = new Intent(CartActivity.this, MainActivity.class);
        myIntent.putExtra("cart", cart);
        startActivity(myIntent);
        finish();
    }

    public void checkout(View v) {
        boolean cartEmpty = false;
        if (cart != null) {
            if (cart.getDetails().size() > 0) {
                Intent myIntent = new Intent(CartActivity.this, CheckoutActivity.class);
                myIntent.putExtra("cart", cart);
                startActivity(myIntent);
                finish();
            }
            else
                cartEmpty = true;
        }
        else cartEmpty = true;

        if(cartEmpty) {
            Context context = getApplicationContext();
            CharSequence text = "No items in cart";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}