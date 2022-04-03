package com.ait8926.heroguidex.hero.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ait8926.heroguidex.hero.Hero;
import com.ait8926.heroguidex.hero.HeroRepository;

import java.util.List;

// AndroidViewModel will accept the Application class
public class HeroViewModel extends AndroidViewModel {

    private HeroRepository heroRepository; // this will get data from DAO

    private LiveData<List<Hero>> allHeroes; // monitor any changes

    // Constructor which has no return
    public HeroViewModel(@NonNull Application application) {
        super(application);

        heroRepository = new HeroRepository(application);

        allHeroes = heroRepository.getAllHeroes();
    }
    public void insert(Hero hero) {
        heroRepository.insert(hero);
    }

    public void delete(Hero hero) {
        heroRepository.delete(hero);
    }

    public void  update(Hero hero) { heroRepository.update(hero); }


    public LiveData<List<Hero>> getAllHeroes(){
        return heroRepository.getAllHeroes();
    }


    public Hero findById(int id) {
        return heroRepository.findById(id);
    }



/*
//////////////////////////////////////////
    public Hero findByName(String heroName) {
        return heroRepository.findByName(heroName);
    }
*/

}