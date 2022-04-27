package com.ait8926.heroguidex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        try {
            new Handler(). postDelayed(new Runnable() {
                @Override
                public void  run()
                {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }

            }, 2000);
        }catch  (Exception exception) {
            Toast.makeText(this, "System application error occurred. Try again.", Toast.LENGTH_SHORT).show();
            exception.printStackTrace();
        }

    }
}
