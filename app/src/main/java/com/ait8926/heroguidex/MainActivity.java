package com.ait8926.heroguidex;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;

import com.ait8926.heroguidex.landing_page.ui.LandingWelcomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // This is only for app without navigation
      /*  if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LandingWelcomeFragment.newInstance())
                    .commitNow();
        }*/
        // For my later implementation
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.drawable.ic_back);*/
    }

}
    /*int resourceID = binding.getRoot().getResources().getIdentifier(hero.getImage(), "drawable", binding.getRoot().getContext().getPackageName());
            this.binding.heroImageView.setImageResource(resourceID);*/