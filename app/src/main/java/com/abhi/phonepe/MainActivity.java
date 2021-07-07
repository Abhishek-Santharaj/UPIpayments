package com.abhi.phonepe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.phonepe.intent.sdk.api.PhonePe;

public class MainActivity extends AppCompatActivity {
    private EditText amount;
    String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    int GOOGLE_PAY_REQUEST_CODE = 123;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GOOGLE_PAY_REQUEST_CODE && requestCode==RESULT_OK){

            Toast.makeText(this, data.getData().toString(), Toast.LENGTH_SHORT).show();
               // Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            }


        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amount);
        Button pay = findViewById(R.id.paybutton);

        PhonePe.init(this);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amt = amount.getText().toString();
                if(amt.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }else{
                    Uri uri =
                            new Uri.Builder()
                                    .scheme("upi")
                                    .authority("pay")
                                    .appendQueryParameter("pa", "8925479775@okbizaxis")
                                    .appendQueryParameter("pn", "Abhishek")
                                    .appendQueryParameter("mc", "")
                                    .appendQueryParameter("tr", "23456")
                                    .appendQueryParameter("tn", "UPI Payment")
                                    .appendQueryParameter("am", amt)
                                    .appendQueryParameter("cu", "INR")
                                    //.appendQueryParameter("url", "your-transaction-url")
                                    .build();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(uri);

                    Intent chooser = Intent.createChooser(intent,"Pay With");
                    startActivityForResult(chooser, GOOGLE_PAY_REQUEST_CODE);

                }
            }
        });



    }
}