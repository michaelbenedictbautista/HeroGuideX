package com.ait8926.heroguidex.landing_page.ui;



import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ait8926.heroguidex.R;
import com.ait8926.heroguidex.databinding.LandingWelcomeFragmentBinding;
import com.ait8926.heroguidex.landing_page.User;
import com.google.android.material.snackbar.Snackbar;


public class LandingWelcomeFragment extends Fragment {

    private LandingWelcomeViewModel mViewModel;
    private LandingWelcomeFragmentBinding binding;


    public static LandingWelcomeFragment newInstance() {
        return new LandingWelcomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = LandingWelcomeFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LandingWelcomeViewModel.class);

        this.binding.landingWelcomeImageView.setImageResource(R.drawable.ember_landing);

        // Sample initialise App username
        binding.landingNameEditText.setText("John Doe");

        // landingLetsStartButton
        binding.landingLetsStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create object for the Navigation host
                NavController navController = Navigation.findNavController(view);
                User user = new User();
                user.setUserName(binding.landingNameEditText.getText().toString());
                String userNameValue = binding.landingNameEditText.getText().toString();
                // Display message for user to fill up
                if (userNameValue.trim().isEmpty()) {
                    Snackbar.make(view, "Enter nickname above.", Snackbar.LENGTH_SHORT).show();
                    //binding.addHeroNameEditText.getText().clear(); // clear the empty spaces
                    binding.landingNameEditText.requestFocus(); // refocus the cursor on desired editText

                } else {

                    // Pass the data  to menu destination fragment by wrapping data to a bundle
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("UserName", user);

                    //navigate through resource Id and pass the bundle to the parameter of navController
                    navController.navigate(R.id.action_landingFragment_to_menuFragment, bundle);
                }
            }
        });

        // landingForwardImageButton
        binding.landingForwardImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create object for the Navigation host
                NavController navController = Navigation.findNavController(view);

                //////////////////////////////Main code//////////////////
                User user = new User();
                user.setUserName(binding.landingNameEditText.getText().toString());

                // Pass the data the next destination
                Bundle bundle = new Bundle();

                // Wrap the whole object as one
                bundle.putSerializable("UserName", user);

                /*Individual bundle passing of attributes example
                bundle.putString("LoggedInUsername", user.getUsername());
                bundle.putString("LoggedInPassword", user.getPassword());*/

                //navigate through resource Id and pass the bundle to the parameter of navController
                navController.navigate(R.id.action_landingFragment_to_tutorialFragment, bundle);

            }
        });
    }
}
