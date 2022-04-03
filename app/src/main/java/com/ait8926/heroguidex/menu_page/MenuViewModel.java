package com.ait8926.heroguidex.menu_page;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ait8926.heroguidex.hero.Hero;
import com.ait8926.heroguidex.hero.HeroRepository;

import java.util.List;

// AndroidViewModel will accept the Application class
public class MenuViewModel extends AndroidViewModel {

    // Create instance of HeroRepository
    private HeroRepository heroRepository;

    // Create instance of LiveData to get all list of heroes from database through repository class
    private LiveData<List<Hero>> allHeroes;

    // Constructor of main class MenuViewModel
    public MenuViewModel(@NonNull Application application) {
        super(application);

        // Create a HeroRepository instance
         heroRepository = new HeroRepository(application);
        // Ask the repository for the monsters data and monitor LiveData an store in allMonster class here
        allHeroes = heroRepository.getAllHeroes();
    }

    public void insert(Hero hero){
        heroRepository.insert(hero);
    }

    public void delete(Hero hero){
        heroRepository.delete(hero);
    }

    public void update(Hero hero){
        heroRepository.update(hero);
    }

    public LiveData<List<Hero>> getAllHeroes(){
        return allHeroes;
    }

    public Hero findById(int id) { return heroRepository.findById(id); }



/*    public Hero findByName(String heroName){
        return heroRepository.findByName(heroName);
    }*/


}