package com.ait8926.heroguidex;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        try  {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.drawable.hero_guide_x_logo_b);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }catch (Exception exception) {
            Toast.makeText(this, "System application error occurred. Try again.", Toast.LENGTH_SHORT).show();
            exception.printStackTrace();
        }

    }
}
