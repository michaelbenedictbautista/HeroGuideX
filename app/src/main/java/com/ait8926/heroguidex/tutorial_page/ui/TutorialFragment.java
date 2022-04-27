package com.ait8926.heroguidex.tutorial_page.ui;

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
import android.widget.Toast;

import com.ait8926.heroguidex.R;
import com.ait8926.heroguidex.databinding.TutorialFragmentBinding;

public class TutorialFragment extends Fragment {

    private TutorialViewModel mViewModel;
    private TutorialFragmentBinding binding;

    public static TutorialFragment newInstance() {
        return new TutorialFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding =  TutorialFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TutorialViewModel.class);

        try {
            // Set image to ImageView
            this.binding.tutorialImageView.setImageResource(R.drawable.clock_tutorial);

            // tutorialForwardImageButton
            binding.tutorialForwardImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Create object for the Navigation host
                    NavController navController = Navigation.findNavController(view);

                    // Navigate through resource Id and pass the bundle to the parameter of navController
                    navController.navigate(R.id.action_tutorialFragment_to_collectionFragment);
                }
            });

            // tutorialBackwardImageButton
            binding.tutorialBackwardImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Create object for the Navigation host
                    NavController navController = Navigation.findNavController(view);

                    // Navigate through resource Id and pass the bundle to the parameter of navController
                    navController.navigate(R.id.action_tutorialFragment_to_landingFragment);
                }
            });

            // landingForwardImageButton
            binding.tutorialSkipButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Create object for the Navigation host
                    NavController navController = Navigation.findNavController(view);

                    //navigate through resource Id and pass the bundle to the parameter of navController
                    navController.navigate(R.id.action_tutorialFragment_to_landingFragment2);
                }
            });
        } catch (Exception exception) {
            Toast.makeText(getContext(), "System application error occurred. Try again.", Toast.LENGTH_SHORT).show();
            exception.printStackTrace();
        }
    }
}
