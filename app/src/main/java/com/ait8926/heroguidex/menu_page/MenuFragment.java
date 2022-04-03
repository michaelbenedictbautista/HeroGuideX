package com.ait8926.heroguidex.menu_page;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ait8926.heroguidex.R;
import com.ait8926.heroguidex.databinding.MenuFragmentBinding;
import com.ait8926.heroguidex.hero.Hero;
import com.ait8926.heroguidex.landing_page.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class MenuFragment extends Fragment {

    // Create instance of MenuViewModel class
    private MenuViewModel mViewModel;
    // Create instance of MenuFragmentBinding class
    private MenuFragmentBinding binding;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = MenuFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(MenuViewModel.class);

        // Create instance of Observer to manage any changes
        final Observer<List<Hero>> allHeroesObserver = new Observer<List<Hero>>() {
            @Override
            public void onChanged(List<Hero> heroes) {
                binding.menuTotalRegTextView.setText("Total heroes registered is: " + heroes.size());
                binding.menuHeroImageView.setImageResource(R.drawable.bristle_menu);
                binding.menuFindIdEditText.getText().clear();
                binding.menuFindIdEditText.requestFocus();
            }
        };
        mViewModel.getAllHeroes().observe(getViewLifecycleOwner(), allHeroesObserver);

        Context context = this.getContext();

        // Create instance of Bundle
        Bundle bundle = getArguments();

        if (bundle != null && bundle.containsKey("UserName")) {
            User user = (User) bundle.getSerializable("UserName"); // cast it as User object

            // Get username from my previous destination and set it to my CreateHeroTextView
            binding.menuUserNameTextView.setText
                    ("Hello " + user.getUserName() + "!");
        } else binding.menuUserNameTextView.setText("Hello");

        // Set image to ImageView. tinker hero//
        this.binding.menuHeroImageView.setImageResource(R.drawable.hero_14);

        // menuViewAllHeroButton method declaration and definition
        binding.menuViewAllHeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create object for the Navigation Controller
                NavController navController = Navigation.findNavController(view);

                // Navigate through resource Id and pass the bundle to the parameter of navController
                navController.navigate(R.id.action_menuFragment_to_heroFragment, bundle);

            }
        });

        // method declaration and definition
        binding.menuFindHeroByIdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.menuFindIdEditText.getText())) {
                    Toast.makeText(getContext(), "Please input an Hero ID", Toast.LENGTH_SHORT).show();
                    binding.menuResultTextView.requestFocus();

                } else {
                    String id = binding.menuFindIdEditText.getText().toString();
                    Hero hero = mViewModel.findById(Integer.parseInt(id));


                    if (hero != null) {
                        // Set hero name and role to menuResultTextView
                        binding.menuResultTextView.setText("Hero name " + hero.getHeroName() +
                                                            " will play as a role of " + hero.getRole());

                        // Display image as well in menuHeroImageView
                        int resourceID = binding.getRoot().getResources().getIdentifier(hero.getImage(), "drawable", binding.getRoot().getContext().getPackageName());
                        binding.menuHeroImageView.setImageResource(resourceID);


                        ////////////////////////// Toast///////////////////////
                    } else {
                        Toast.makeText(getContext(), "Hero ID not registered. Try again.", Toast.LENGTH_LONG).show();
                        binding.menuFindIdEditText.getText().clear();
                        binding.menuFindIdEditText.requestFocus();
                    }
                }
            }
        });

        // Delete hero by ID method declaration and definition
        binding.menuDeleteHeroByIdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.menuFindIdEditText.getText())) {
                    Toast.makeText(getContext(), "Please input a Hero ID", Toast.LENGTH_SHORT).show();
                } else {
                    String id = binding.menuFindIdEditText.getText().toString();
                    Hero hero = mViewModel.findById(Integer.parseInt(id));

                    new AlertDialog.Builder(context)
                            .setTitle("Delete hero?")
                            .setMessage("Are you sure you want to delete " + hero.getHeroName() + ".")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    if (hero != null) {

                                        mViewModel.delete(hero);
                                        Snackbar.make(view, "Hero successfully deleted", Snackbar.LENGTH_SHORT).show();
                                        binding.menuFindIdEditText.requestFocus();
                                    }
                                }
                            })
                            .setNegativeButton(android.R.string.no, null).show();
                    binding.menuFindIdEditText.getText().clear();
                    binding.menuFindIdEditText.requestFocus();
                }
            }
        });
    }
}


