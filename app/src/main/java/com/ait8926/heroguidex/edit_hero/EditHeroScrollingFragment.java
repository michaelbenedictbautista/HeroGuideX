package com.ait8926.heroguidex.edit_hero;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.SeekBar;
import android.widget.Spinner;


import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.ait8926.heroguidex.R;

import com.ait8926.heroguidex.databinding.EditHeroScrollingfragmentBinding;
import com.ait8926.heroguidex.hero.Hero;

import com.google.android.material.snackbar.Snackbar;

public class EditHeroScrollingFragment extends Fragment {

    private EditHeroScrollingfragmentBinding binding;

    Hero hero = new Hero();

    String chosenImage;
    String chosenRole;
    String chosenName;
    String chosenHistory;
    String chosenAudio;
    int attackDamageValue;
    int attackSpeedValue;
    int defenseValue;
    int resID;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = EditHeroScrollingfragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Check for incoming bundle of data from Edit hero class named "EDIT_HERO"
        Context context = this.getContext();
        NavController navController = Navigation.findNavController(view);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("EDIT_HERO")) {

            hero = (Hero) getArguments().getSerializable("EDIT_HERO");

            // Assign variable
            chosenImage = hero.getImage();
            chosenName = hero.getHeroName();
            chosenRole = hero.getRole();
            attackDamageValue = hero.getAttackDamage();
            attackSpeedValue = hero.getAttackSpeed();
            defenseValue = hero.getDefense();
            chosenHistory = hero.getHistory();
            chosenAudio = hero.getAudio();


            ////////////////Current Information of hero/////////////
            resID = binding.getRoot().getResources().getIdentifier(chosenImage, "drawable", binding.getRoot().getContext().getPackageName());
            binding.editCurrentImageImageView.setImageResource(resID);
            binding.editCurrentImageTextView.setText("Current image: " + chosenImage);
            binding.editNameTextView.setText("Current name: " + chosenName);
            binding.editRoleTextView.setText("Current role: " + chosenRole);
            binding.editDamageTextView.setText("Current damage: " + attackDamageValue);
            binding.editSpeedTextView.setText("Current speed: " + attackSpeedValue);
            binding.editDefenseTextView.setText("Current defense: " + defenseValue);
            binding.editHistoryTextView.setText("Current history:");
            binding.editAudioTextView.setText("Current audio: " + chosenAudio);

            ////////////////editImageSpinner/////////////
            Spinner spinnerImage = binding.editImageSpinner;
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.image, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinnerImage.setAdapter(adapter);

            spinnerImage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    chosenImage = parent.getItemAtPosition(pos).toString();
                    int resID2 = binding.getRoot().getResources().getIdentifier(chosenImage, "drawable", binding.getRoot().getContext().getPackageName().toString());
                    binding.editImageImageView.setImageResource(resID2);
                }

                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ////////////////editNameEditText////////////
            binding.editNameEditText.setText(chosenName);

            ////////////////editRoleSpinner/////////////
            Spinner spinnerRole = binding.editRoleSpinner;
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(context, R.array.role, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinnerRole.setAdapter(adapter2);

            spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                    chosenRole = parent.getItemAtPosition(pos).toString();
                    //binding.addRoleTextView.setText(chosenRole);

                    chosenRole = parent.getItemAtPosition(pos).toString();
                    hero.setRole(chosenRole);
                }

                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            ////////////editDamageSeekBar//////////
            binding.editDmgProgTextView.setText(attackDamageValue + "/100");
            binding.editDamageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    attackDamageValue = i;
                    binding.editDmgProgTextView.setText(attackDamageValue + "/100");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            ////////////editSpeedSeekBar//////////
            binding.editSpdProgTextView.setText(attackSpeedValue + "/100");
            binding.editSpeedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    attackSpeedValue = i;
                    binding.editSpdProgTextView.setText(attackSpeedValue + "/100");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            ////////////editDefenseSeekBar//////////
            binding.editDefProgTextView.setText(defenseValue + "/100");
            binding.editDefenseSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    defenseValue = i;
                    binding.editDefProgTextView.setText(defenseValue + "/100");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            ////////////////editHistoryEditText////////
            binding.editHistoryEditText.setText(hero.getHistory());

            ////////////////addRoleSpinner/////////////
            Spinner spinnerAudio = binding.editAudioSpinner;
            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(context, R.array.audio, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinnerAudio.setAdapter(adapter3);

            spinnerAudio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    chosenAudio = parent.getItemAtPosition(pos).toString();
                    //binding.addRoleTextView.setText(chosenRole);
                }

                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            /////////////// Cancel button function//////
            binding.editHeroCancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(context)
                            .setTitle("Cancel update hero?")
                            .setMessage("Are you sure?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    if (hero != null) {
                                        navController.navigate(R.id.action_editHeroScrollingFragment_to_heroFragment);
                                        Snackbar.make(view, "Unsuccessful!", Snackbar.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton(android.R.string.no, null).show();
                    binding.editNameEditText.requestFocus(); // Set cursor back to addNameEditText
                }
            });

            ///////////// Save button function//////////////
            binding.editHeroSaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String chosenName = binding.editNameEditText.getText().toString().trim();// Get hero name
                    String chosenHistory = binding.editHistoryEditText.getText().toString().trim(); // Get hero history
                    if (chosenName.trim().isEmpty()) {
                        Snackbar.make(view, "Hero name is required", Snackbar.LENGTH_SHORT).show();
                        binding.editNameEditText.getText().clear(); // clear empty spaces
                        binding.editNameEditText.requestFocus(); // clear empty spaces

                    } else if (chosenHistory.trim().isEmpty()) {
                        binding.editHistoryEditText.setText("Defense of the Ancient hero " + chosenName + " will play as " + chosenRole +
                                "role to fight against radiant team.");

                    } else {
                        // Set all the values getting from the add/create hero page
                        hero.setImage(chosenImage);
                        hero.setHeroName(chosenName);
                        hero.setRole(chosenRole);
                        hero.setAttackDamage(attackDamageValue);
                        hero.setAttackSpeed(attackSpeedValue);
                        hero.setDefense(defenseValue);
                        hero.setHistory(chosenHistory);
                        hero.setAudio(chosenAudio);

                        Bundle bundle1 = new Bundle();
                        bundle1.putSerializable("EDIT_HERO", hero);

                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.action_editHeroScrollingFragment_to_heroFragment, bundle1);

                        Snackbar.make(view, "Hero successfully updated", Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

