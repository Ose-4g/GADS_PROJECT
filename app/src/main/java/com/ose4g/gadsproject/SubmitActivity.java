package com.ose4g.gadsproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        findViewById(R.id.the_blur).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.back)).setOnClickListener(new buttonClickListener());
        ((Button) findViewById(R.id.submit_button)).setOnClickListener(new buttonClickListener());



    }

    private void setVisibility(boolean on)
    {
        if(on)
        {
            findViewById(R.id.first_name).setVisibility(View.VISIBLE);
            findViewById(R.id.last_name).setVisibility(View.VISIBLE);
            findViewById(R.id.email_address).setVisibility(View.VISIBLE);
            findViewById(R.id.github_link).setVisibility(View.VISIBLE);
            findViewById(R.id.submit_button).setVisibility(View.VISIBLE);
            findViewById(R.id.the_blur).setVisibility(View.GONE);

        }
        else
        {
            findViewById(R.id.first_name).setVisibility(View.INVISIBLE);
            findViewById(R.id.last_name).setVisibility(View.INVISIBLE);
            findViewById(R.id.email_address).setVisibility(View.INVISIBLE);
            findViewById(R.id.github_link).setVisibility(View.INVISIBLE);
            findViewById(R.id.submit_button).setVisibility(View.INVISIBLE);
            findViewById(R.id.the_blur).setVisibility(View.VISIBLE);
        }

    }


    private void sendDetails()
    {
        /*
        Send data to the form
         */

        FormService formService = new Retrofit.Builder()
                                     .baseUrl("https://docs.google.com/forms/u/0/d/e/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build().create(FormService.class);//ServicesBuilder.buildService(FormService.class);
        String email = ((EditText) findViewById(R.id.email_address)).getText().toString();// "osemudiamenitua@gmail.com";
        String fName = ((EditText) findViewById(R.id.first_name)).getText().toString();//"ose";
        String lName = ((EditText) findViewById(R.id.last_name)).getText().toString();//"Itua";
        String link = ((EditText) findViewById(R.id.github_link)).getText().toString();//"https://github.com";

        Call<Void> sendData = formService.postDetails(email,fName,lName,link);

        sendData.enqueue(new Callback<Void>()
        {
            //if the response is positive a successful alert appears
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                View alertView = LayoutInflater.from(SubmitActivity.this).inflate(R.layout.successful,null);
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(SubmitActivity.this);
                alertDialogBuilder.setView(alertView);
                alertDialogBuilder.setCancelable(true);
                final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();
                alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        setVisibility(true);
                    }
                });


            }

            //else a not successful alert appears
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                View alertView = LayoutInflater.from(SubmitActivity.this).inflate(R.layout.not_successful,null);

                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(SubmitActivity.this);
                alertDialogBuilder.setView(alertView);
                alertDialogBuilder.setCancelable(true);
                final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();
                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        setVisibility(true);
                    }
                });

            }


        });
    }



    class buttonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.back)
                finish();

            if(view.getId()==R.id.submit_button)
            {
                setVisibility(false);
                final View alertView = LayoutInflater.from(SubmitActivity.this).inflate(R.layout.alert_confirm,null);

                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(SubmitActivity.this);
                alertDialogBuilder.setView(alertView);
                alertDialogBuilder.setCancelable(true);
                final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                alertDialog.setCanceledOnTouchOutside(true);

                alertDialog.show();
                alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        setVisibility(true);
                    }
                });

                ((ImageView) alertView.findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        setVisibility(true);
                    }
                });


                ((Button) alertView.findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        sendDetails();
                    }
                });

            }
        }

    }
}

