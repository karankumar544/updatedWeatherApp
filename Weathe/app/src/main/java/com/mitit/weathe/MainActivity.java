package com.mitit.weathe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();
        SharedPreferences sharedPreferences = getSharedPreferences("Permissions",MODE_PRIVATE);
        boolean ans = sharedPreferences.getBoolean("flag",false);
        connectionSet();
        handler.postDelayed(new Runnable() {
            @Override
            public void run(){
                if(ans){
                Intent nextIn = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(nextIn);
                }else{
                Intent retryIn = new Intent(MainActivity.this , RetryActivity.class);
                startActivity(retryIn);
                }
                finish();
            }
        }, 4000);
    }
    private void connectionSet()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        SharedPreferences sharedPreference = getSharedPreferences("Permissions",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();
        if (networkInfo!=null)
        {
            editor.putBoolean("flag",true);
            editor.apply();
        }else{
            editor.putBoolean("flag",false);
            editor.apply();
        }
    }
}