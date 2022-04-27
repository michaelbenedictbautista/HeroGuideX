package com.ait8926.heroguidex.add;


import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.ait8926.heroguidex.R;
import com.ait8926.heroguidex.databinding.AddHeroScrollingFragmentBinding;

import com.ait8926.heroguidex.hero.Hero;
import com.ait8926.heroguidex.hero.ui.HeroViewModel;
import com.google.android.material.snackbar.Snackbar;


public class AddHeroScrollingFragment extends Fragment {

    private HeroViewModel mViewModel;
    private AddHeroScrollingFragmentBinding binding;

    String chosenImage;
    String chosenRole;
    String chosenName;
    String chosenHistory;
    String chosenAudio;
    int attackDamageValue;
    int attackSpeedValue;
    int defenseValue;
    int resID;

    public static AddHeroScrollingFragment newInstance() {
        return new AddHeroScrollingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddHeroScrollingFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HeroViewModel.class);

        try {
            Hero hero = new Hero();
            // Instantiate NavController to navigate to other destinations/fragments
            NavController navController = Navigation.findNavController(view);
            // Instantiate Bundle to wrap data. For incoming or transferring data.
            Bundle bundle = new Bundle();
            Context context = this.getContext();

            ////////////////addImageSpinner//////////////
            Spinner spinnerImage = binding.addImageSpinner;
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.image, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinnerImage.setAdapter(adapter);

            spinnerImage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    //Object item = parent.getItemAtPosition(pos);
                    chosenImage = parent.getItemAtPosition(pos).toString();

                    //binding.addImageImageView.setImageResource((R.drawable.hero.get);
                    hero.setImage(chosenImage);
                    resID = binding.getRoot().getResources().getIdentifier(hero.getImage(), "drawable", binding.getRoot().getContext().getPackageName());
                    binding.addImageImageView.setImageResource(resID);
                }

                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            ////////////////addRoleSpinner/////////////
            Spinner spinnerRole = binding.addRoleSpinner;
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(context, R.array.role, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinnerRole.setAdapter(adapter2);

            spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    chosenRole = parent.getItemAtPosition(pos).toString();
                    //binding.addRoleTextView.setText(chosenRole);
                }

                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            ////////////addDamageSeekBar//////////
            binding.addDamageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    attackDamageValue = i;
                    binding.addDmgProgTextView.setVisibility(view.VISIBLE);
                    binding.addDmgProgTextView.setText(attackDamageValue + "/100");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            ////////////addDamageSeekBar//////////
            binding.addDamageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    attackDamageValue = i;
                    binding.addDmgProgTextView.setVisibility(view.VISIBLE);
                    binding.addDmgProgTextView.setText(attackDamageValue + "/100");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            ////////////addSpeedSeekBar//////////
            binding.addSpeedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    attackSpeedValue = i;
                    binding.addSpdProgTextView.setVisibility(view.VISIBLE);
                    binding.addSpdProgTextView.setText(attackSpeedValue + "/100");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            ////////////addDefenseSeekBar//////////
            binding.addDefenseSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    defenseValue = i;
                    binding.addDefProgTextView.setVisibility(view.VISIBLE);
                    binding.addDefProgTextView.setText(defenseValue + "/100");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            ////////////////addAudioSpinner/////////////
            Spinner spinnerAudio = binding.addAudioSpinner;
            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(context, R.array.audio, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinnerAudio.setAdapter(adapter3);

            spinnerAudio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    chosenAudio = parent.getItemAtPosition(pos).toString();
                    int resourceID = binding.getRoot().getResources().getIdentifier(chosenAudio, "raw", binding.getRoot().getContext().getPackageName());
                    MediaPlayer mediaPlayer =  MediaPlayer.create(binding.getRoot().getContext(), resourceID);
                    mediaPlayer.start();
                }

                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            ////////////// addHeroAddButton method declaration and definition/////////////
            binding.addHeroAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chosenName =  binding.addNameEditText.getText().toString().trim();// Get hero name
                    chosenHistory =  binding.addHistoryEditText.getText().toString().trim(); // Get hero history
                    if (chosenName.trim().isEmpty()) {
                        Snackbar.make(view, "Hero name is required", Snackbar.LENGTH_SHORT).show();
                        binding.addNameEditText.getText().clear(); // clear empty spaces
                        binding.addNameEditText.requestFocus(); // clear empty spaces

                    } else if (chosenHistory.trim().isEmpty()){
                        binding.addHistoryEditText.setText("Defense of the Ancient hero " + chosenName + " will play as " + chosenRole +
                                "role to fight against radiant team.");
                    } else {
                        Hero hero = new Hero(); // create instance of hero class

                        // Set all the values getting from the add/create hero page
                        hero.setImage(chosenImage);
                        hero.setHeroName(chosenName);
                        hero.setRole(chosenRole);
                        hero.setAttackDamage(attackDamageValue);
                        hero.setAttackSpeed(attackSpeedValue);
                        hero.setDefense(defenseValue);
                        hero.setHistory(chosenHistory);
                        hero.setAudio(chosenAudio);

                        bundle.putSerializable("ADD_HERO", hero);
                        navController.navigate(R.id.action_addHeroScrollingFragment_to_heroFragment, bundle);
                    }
                }
            });

            ///////////////addHeroCancelButton method declaration and definition////////////
            binding.addHeroCancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(context)
                            .setTitle("Cancel create hero?")
                            .setMessage("Are you sure?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    if (hero != null) {

                                        navController.navigate(R.id.action_addHeroScrollingFragment_to_heroFragment);
                                        Snackbar.make(view, "Unsuccessful", Snackbar.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton(android.R.string.no, null).show();
                    binding.addNameEditText.requestFocus(); // Set cursor back to addNameEditText
                }
            });

        }catch (Exception exception){
            Toast.makeText(getContext(), "System application error occurred. Try again.", Toast.LENGTH_SHORT).show();
            exception.printStackTrace();
        }
    }
}
