package com.example.tp06_contentprovider;/*
package com.example.tp06_contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.tp06_contentprovider.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
            && checkSelfPermission(Manifest.permission.READ_SMS)
        != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_SMS},2500);
        }

        binding.btStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(serviceIntent);
                binding.btStartService.setEnabled(false);
                binding.btStopService.setEnabled(true);
            }
        });

        binding.btStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(serviceIntent);
                binding.btStartService.setEnabled(true);
                binding.btStopService.setEnabled(false);
            }
        });

        Intent intent =new Intent(this, MensajeService.class);
        this.startService(intent);

    }
}*/

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_SMS},2500);
        }
        intent = new Intent(this, MensajeService.class);
    }
    public void iniciar(View view){

        this.startService(intent);
    }
    public void detener(View view){
        this.stopService(intent);
    }
}
