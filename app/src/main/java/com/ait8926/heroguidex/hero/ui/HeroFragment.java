package com.ait8926.heroguidex.hero.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ait8926.heroguidex.R;
import com.ait8926.heroguidex.databinding.HeroFragmentBinding;
import com.ait8926.heroguidex.edit_hero.OnItemClickListener;
import com.ait8926.heroguidex.hero.Hero;

import java.util.List;
import java.util.zip.Inflater;

public class HeroFragment extends Fragment implements OnItemClickListener {

    private HeroViewModel mViewModel;
    private HeroFragmentBinding binding;

    // Create instance of Bundle to wrap data

    public static HeroFragment newInstance() {
        return new HeroFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = HeroFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HeroViewModel.class);


        // Create object for the NavController
        NavController navController = Navigation.findNavController(view);
        // Create instance of Bundle to get or transfer data to and from other destination or fragment
        Bundle bundle = getArguments();


        // Get the ADD_HERO bundle from addHeroScrollingFragment or page
        if (bundle != null && bundle.containsKey("ADD_HERO")) {
            Hero hero = (Hero) getArguments().getSerializable("ADD_HERO");
            mViewModel.insert(hero);
        }


        //////////////////////////////////////////////////////////////
        // Get the EDITED_HERO bundle from addHeroScrollingFragment or page
        if (bundle != null && bundle.containsKey("EDIT_HERO")) {
            Hero hero = (Hero) getArguments().getSerializable("EDIT_HERO");
            mViewModel.update(hero);
        }

        //Recycler View
        binding.heroRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.heroRecyclerView.setHasFixedSize(true);

        // Implement  heroRecyclerView class  that implements OnItemClickListener interface
        HeroRecyclerViewAdapter adapter = new HeroRecyclerViewAdapter(this);
        binding.heroRecyclerView.setAdapter(adapter);


        // this is responsible of updating the GUI for any changes  in our database
        final Observer<List<Hero>> allHeroesObserver = new Observer<List<Hero>>() {
            @Override
            public void onChanged(List<Hero> heroes) {
                /*//////////////////////////////////
                adapter.submitList(heroes);
*/
                //update RecyclerView
                adapter.setHeroes(heroes);
                adapter.notifyDataSetChanged();

            }
        };
        mViewModel.getAllHeroes().observe(getViewLifecycleOwner(), allHeroesObserver);


        binding.addHeroFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Navigate through resource Id and pass the bundle to the parameter of navController
                navController.navigate(R.id.action_heroFragment_to_addHeroScrollingFragment, bundle);
            }
        });
    }

    // Onclick method declaration and definition
    @Override
    public void Onclick(Hero hero, View view) {
        // This will be implemented once image hero image being clicked

        Log.i("XYZ", hero.toString());
        Bundle bundle = new Bundle();
        bundle.putSerializable("EDIT_HERO", hero);

        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_heroFragment_to_editHeroScrollingFragment, bundle);
    }
}

