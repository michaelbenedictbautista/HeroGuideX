package com.ait8926.heroguidex.hero.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ait8926.heroguidex.databinding.HeroRecyclerViewBinding;
import com.ait8926.heroguidex.edit_hero.OnItemClickListener;
import com.ait8926.heroguidex.hero.Hero;
import java.util.List;

public class HeroRecyclerViewAdapter extends RecyclerView.Adapter<HeroViewHolder> {

    // Create instance of HeroRecyclerViewBinding,ist<Hero>, OnItemClickListener
    private HeroRecyclerViewBinding binding;
    private List<Hero> heroes;
    private OnItemClickListener onItemClickListener;

    public HeroRecyclerViewAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        binding = HeroRecyclerViewBinding.inflate(inflater, parent, false);
        HeroViewHolder heroViewHolder = new HeroViewHolder(binding);
        return heroViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {
        // Getting the data based on the position in the view
        Hero hero = heroes.get(position);

        // pass the hero object to the parameter of updatedHero
        holder.updateHero(hero);

        // bind the onItemClickListener interface to the UI
        holder.bind(hero, onItemClickListener);
    }

    // Display all the number of heroes registered to our database
    @Override
    public int getItemCount() {
        return heroes != null ? heroes.size(): 0;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
        notifyDataSetChanged();
    }
}
