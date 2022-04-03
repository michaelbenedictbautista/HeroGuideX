package com.ait8926.heroguidex.collection_page;

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
import com.ait8926.heroguidex.databinding.CollectionFragmentBinding;


public class CollectionFragment extends Fragment {

    private CollectionViewModel mViewModel;
    private CollectionFragmentBinding binding;


    public static CollectionFragment newInstance() {
        return new CollectionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = CollectionFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CollectionViewModel.class);

        // Set image to ImageView
        this.binding.collectionImageView.setImageResource(R.drawable.invoker_collection);

        // Create instance of Bundle
        Bundle bundle = getArguments();

        // collectionBackwardImageButton
        binding.collectionBackwardImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create object for the NavController
                NavController navController = Navigation.findNavController(view);

                //Navigate through resource Id and pass the bundle to the parameter of navController
                navController.navigate(R.id.action_collectionFragment_to_tutorialFragment, bundle);
            }
        });

    }
}