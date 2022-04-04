package com.ait8926.heroguidex.edit_hero;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.ait8926.heroguidex.R;

import com.ait8926.heroguidex.databinding.EditHeroScrollingfragmentBinding;
import com.ait8926.heroguidex.hero.Hero;
import com.ait8926.heroguidex.hero.ui.HeroViewModel;
import com.google.android.material.snackbar.Snackbar;

public class EditHeroScrollingFragment extends Fragment {

    private EditHeroScrollingfragmentBinding binding;

    Hero hero = new Hero();

    String chosenImage;
    String chosenRole;
    String chosenName;
    String chosenHistory;
    int attackDamageValue;
    int attackSpeedValue;
    int defenseValue;
    int resID;




    /*public static EditHeroScrollingFragment newInstance() {
        return new EditHeroScrollingFragment();
    }*/

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

            hero = (Hero) bundle.getSerializable("EDIT_HERO");

            resID = binding.getRoot().getResources().getIdentifier(hero.getImage(), "drawable", binding.getRoot().getContext().getPackageName());
            binding.editImageImageView.setImageResource(resID);

            //chosenImage = hero.getImage();
            chosenRole = hero.getRole();
            attackDamageValue = hero.getAttackDamage();
            attackSpeedValue = hero.getAttackSpeed();
            defenseValue = hero.getDefense();
            chosenHistory = hero.getHistory();

           /* int resID = binding.getRoot().getResources().getIdentifier(hero.getImage(), "drawable", binding.getRoot().getContext().getPackageName());
            this.binding.editImageImageView.setImageResource(resID);*/


            Spinner spinnerImage = binding.editImageSpinner;

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.image, android.R.layout.simple_spinner_item);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

            spinnerImage.setAdapter(adapter);

            spinnerImage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    //Object item = parent.getItemAtPosition(pos);

                   /*chosenImage = parent.getItemAtPosition(pos).toString();
                    hero.setImage(chosenImage);
                    int resourceID = binding.getRoot().getResources().getIdentifier(hero.getImage(), "drawable", binding.getRoot().getContext().getPackageName().toString());
                    binding.editImageImageView.setImageResource(resourceID);*/

                    //chosenImage = parent.getItemAtPosition(pos).toString();
                    //hero.setImage(chosenImage);
                    chosenImage = parent.getItemAtPosition(pos).toString();
                    resID = binding.getRoot().getResources().getIdentifier(chosenImage, "drawable", binding.getRoot().getContext().getPackageName().toString());
                    binding.editImageImageView.setImageResource(resID);



                }

                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            // Set hero name to editNameEditText
            chosenName = hero.getHeroName();
            binding.editNameEditText.setText(chosenName);


            ////////////////addRoleSpinner/////////////
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

            ////////////addDamageSeekBar//////////
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

            ////////////addSpeedSeekBar//////////
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

            ////////////addDefenseSeekBar//////////
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

            // Get Hero history and set it to editHistoryEditText
            binding.editHistoryEditText.setText(hero.getHistory());


                // Cancel button function
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

                // Save button functions
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

