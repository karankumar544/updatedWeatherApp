package com.mitit.weathe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mitit.weathe.Models.MausamData;
import com.mitit.weathe.Models.hari;
import com.mitit.weathe.Models.main;
import com.mitit.weathe.Models.sys;
import com.mitit.weathe.Models.weather;
import com.mitit.weathe.Models.wind;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
ImageView LongOut,Find;
EditText Search_city_name;
TextView City_name,temperature,description,sunriseTime,windSpeed,sunsetTime,humidity_in_percentage
        ,pressure_in_pascal;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        temperature = findViewById(R.id.temperature);
        sunriseTime = findViewById(R.id.sunriseTime);
        sunsetTime = findViewById(R.id.sunsetTime);
        windSpeed = findViewById(R.id.windSpeed);
        humidity_in_percentage = findViewById(R.id.humidity_in_percentage);
        pressure_in_pascal = findViewById(R.id.pressure_in_pascal);
        City_name = findViewById(R.id.City_name);
        description = findViewById(R.id.description);
        TextView about = findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(HomeActivity.this,aboutActivity.class);
                startActivity(newIntent);
            }
        });
        fetchWeather();
    }
    private void fetchWeather() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherInterface WeatherInterface = retrofit.create(weatherInterface.class);
        Call<MausamData> call = WeatherInterface.getData("Muzaffarpur","8967795249f9ac5fdc3c8e887cc08b90"
        ,"metric");

        call.enqueue(new Callback<MausamData>() {
            @Override
            public void onResponse(@NonNull Call<MausamData> call, @NonNull Response<MausamData> response) {
                if(response.isSuccessful()){
                    MausamData mausamData = response.body();
                    assert mausamData != null;
                    main to = mausamData.getMain();
                    temperature.setText(String.valueOf(to.getTemp()));
                    humidity_in_percentage.setText((String.valueOf(to.getHumidity())));
                    pressure_in_pascal.setText((String.valueOf(to.getPressure())));
                    City_name.setText(mausamData.getName());
                    List<weather> weathers = mausamData.getWeather();

                    for (weather data:weathers)
                    {
                        description.setText(data.getDescription());
                    }
                    sys s = mausamData.getSys();

                    long sunrise = s.getSunrise();
                    long sunset = s.getSunset();
                    hari H = new hari();
                    String SunriseTime = H.getSunriseTimestamp(sunrise);
                    sunriseTime.setText(SunriseTime);
                    String  SunsetTime = H.getSunsetTimestamp(sunset);
                    sunsetTime.setText(SunsetTime);
                    wind W = mausamData.getWind();
                    windSpeed.setText(String.valueOf(W.getSpeed()));
                }

            }

            @Override
            public void onFailure(@NonNull Call<MausamData> call, @NonNull Throwable t) {

            }
        });
    }
}