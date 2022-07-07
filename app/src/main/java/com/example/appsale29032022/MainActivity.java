package com.example.appsale29032022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Xehoi xehoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Banhxe banhxe = new Banhxe();
//        Dongco dongco = new Dongco();
//
//        Xehoi xehoi;
    }
}
