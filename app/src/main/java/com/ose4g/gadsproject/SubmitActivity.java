package com.ose4g.gadsproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SubmitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        ((ImageView) findViewById(R.id.back)).setOnClickListener(new buttonClickListener());
        ((Button) findViewById(R.id.submit_button)).setOnClickListener(new buttonClickListener());



    }



    class buttonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.back)
                finish();
            if(view.getId()==R.id.submit_button)
            {
                final View alertView = LayoutInflater.from(SubmitActivity.this).inflate(R.layout.alert_confirm,null);

                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(SubmitActivity.this);
                alertDialogBuilder.setView(alertView);
                alertDialogBuilder.setCancelable(true);
                final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();

                ((ImageView) alertView.findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        View alertView = LayoutInflater.from(SubmitActivity.this).inflate(R.layout.not_successful,null);

                        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(SubmitActivity.this);
                        alertDialogBuilder.setView(alertView);
                        alertDialogBuilder.setCancelable(true);
                        final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        alertDialog.setCanceledOnTouchOutside(true);
                        alertDialog.show();
                    }
                });


                ((Button) alertView.findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        View alertView = LayoutInflater.from(SubmitActivity.this).inflate(R.layout.successful,null);

                        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(SubmitActivity.this);
                        alertDialogBuilder.setView(alertView);
                        alertDialogBuilder.setCancelable(true);
                        final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        alertDialog.setCanceledOnTouchOutside(true);
                        alertDialog.show();
                    }
                });







            }
        }

    }
}

