package com.ait8926.heroguidex.hero.ui;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ait8926.heroguidex.R;
import com.ait8926.heroguidex.databinding.HeroRecyclerViewBinding;
import com.ait8926.heroguidex.edit_hero.OnItemClickListener;
import com.ait8926.heroguidex.hero.Hero;

// The view holder class will control view and representing the hero_recycler_view.xml
public class HeroViewHolder extends RecyclerView.ViewHolder {

    private HeroRecyclerViewBinding binding;

    public HeroViewHolder(@NonNull HeroRecyclerViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void updateHero(Hero hero){
        this.binding.heroNameTextView.setText(hero.getHeroName());
        this.binding.heroHistoryTextView.setText(hero.getHistory());
        this.binding.heroRoleTextView.setText("Role: " + hero.getRole());
        this.binding.attackDamageTextView.setText("Damage: " + hero.getAttackDamage());
        this.binding.attackSpeedTextView.setText("Speed: " + hero.getAttackSpeed());
        this.binding.defenseTextView.setText("Defense: " + hero.getDefense());
        this.binding.heroVotesTextView.setText("Votes: " + hero.getVotes());
        this.binding.heroStarRatingBar.setRating(hero.getRating());

        // Set default image
        if(hero.getImage().isEmpty()) {
            this.binding.heroImageView.setImageResource(R.drawable.storm_spirit);
        }else{
            // get the desired image locally from our resource drawable folder
            int resourceID = binding.getRoot().getResources().getIdentifier(hero.getImage(), "drawable", binding.getRoot().getContext().getPackageName());
            this.binding.heroImageView.setImageResource(resourceID);
        }

//        int resourceID = binding.getRoot().getResources().getIdentifier(hero.getAudio(), "raw", binding.getRoot().getContext().getPackageName());
//        MediaPlayer mediaPlayer =  MediaPlayer.create(binding.getRoot().getContext(), resourceID);
//        this.binding.playAudioImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mediaPlayer.start();
//
//                //Navigate through resource Id and pass the bundle to the parameter of navController
//                //navController.navigate(R.id.action_heroFragment_to_addHeroScrollingFragment, bundle);
//            }
//        });

        if(hero.getAudio().isEmpty()) {

            this.binding.playAudioImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(binding.getRoot().getContext(), R.raw.hero5_audio);
                    mediaPlayer.start();
                }
            });
        } else {
            int resourceID = binding.getRoot().getResources().getIdentifier(hero.getAudio(), "raw", binding.getRoot().getContext().getPackageName());

            this.binding.playAudioImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MediaPlayer mediaPlayer =  MediaPlayer.create(binding.getRoot().getContext(), resourceID);
                    mediaPlayer.start();

                    //Navigate through resource Id and pass the bundle to the parameter of navController
                    //navController.navigate(R.id.action_heroFragment_to_addHeroScrollingFragment, bundle);
                }
            });
        }
    }

    public void bind(Hero hero, OnItemClickListener onItemClickListener) {
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.Onclick(hero, view);
            }
        });
    }
}