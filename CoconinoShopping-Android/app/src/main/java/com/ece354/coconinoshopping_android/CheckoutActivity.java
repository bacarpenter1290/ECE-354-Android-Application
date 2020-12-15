package com.ece354.coconinoshopping_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CheckoutActivity extends AppCompatActivity {

    Intent intent;
    Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
    }

    @Override
    protected void onResume() {
        super.onResume();

        intent = getIntent();
        cart = intent.getParcelableExtra("cart");
    }

    public void confirmCheckout(View v) {

        EditText txtName = findViewById(R.id.editName);
        EditText txtAddress1 = findViewById(R.id.editAddress1);
        EditText txtCity = findViewById(R.id.editCity);
        EditText txtState = findViewById(R.id.editState);
        EditText txtZipCode = findViewById(R.id.editZip);
        EditText txtPhone = findViewById(R.id.editPhone);
        EditText txtCreditCard = findViewById(R.id.editCreditCard);

        if (txtName.getText().toString().isEmpty() ||
                txtAddress1.getText().toString().isEmpty() ||
                txtCity.getText().toString().isEmpty() ||
                txtState.getText().toString().isEmpty() ||
                txtZipCode.getText().toString().isEmpty() ||
                txtPhone.getText().toString().isEmpty() ||
                txtCreditCard.getText().toString().isEmpty()) {

            Context context = getApplicationContext();
            CharSequence text = "Please fill in all fields (except address line 2 if applicable)";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {

            AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutActivity.this);
            Double total = cart.getTotal();
            builder.setMessage("Total: $ " + String.format("%.2f", total)).setTitle("Checkout complete");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                    Intent myIntent = new Intent(CheckoutActivity.this, MainActivity.class);
                    myIntent.putExtra("cart", new Cart());
                    startActivity(myIntent);
                    finish();
                }
            });

            AlertDialog dialog = builder.create();

            dialog.show();
        }
    }

    public void cancelCheckout(View v) {
        Intent myIntent = new Intent(CheckoutActivity.this, MainActivity.class);
        myIntent.putExtra("cart", cart);
        startActivity(myIntent);
        finish();
    }
}