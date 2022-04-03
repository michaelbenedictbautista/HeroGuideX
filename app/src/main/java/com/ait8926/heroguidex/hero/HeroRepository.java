package com.ait8926.heroguidex.hero;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HeroRepository {

    // Create instance of HeroRoomDatabase class
    private HeroRoomDatabase db;

    // Create instance of HeroDao class and also other Data Access Object(DAO)
    private HeroDao heroDao;

    // Create instance LiveData for List of heroes registered in our database
    private LiveData<List<Hero>> allHeroes;

    // Create instance of Hero class
    private Hero hero;


    // Constructor for HeroRepository
    public HeroRepository(Application application) {
        // get database connection to communicate with our HeroRepository
        db = HeroRoomDatabase.getDatabase(application);

        // get data from our HeroDao class and store here in repository class
        // from object heroDao we created above.
        heroDao = db.heroDao();

        // get all hero registered in our database
        allHeroes = heroDao.findAll();
    }

    public void insert(Hero hero) {
        // This operation prevent crashing  of the app
        HeroRoomDatabase.databaseWriteExecutor.execute(() -> {
            heroDao.insert(hero);
        });
    }

    public void update(Hero hero){
        HeroRoomDatabase.databaseWriteExecutor.execute( () -> {
            heroDao.update(hero);
        });
    }

    public void delete(Hero monster){
        HeroRoomDatabase.databaseWriteExecutor.execute( () -> {
            heroDao.delete(hero);
        });
    }

    public LiveData<List<Hero>> getAllHeroes() {
        //Room library will implement the LiveData execution & keep updating our database
        // only applicable for Select all query
        return allHeroes;
    }

    // Find hero by hero id
    public Hero findById(int id){

        Callable c = () -> {   // Lambda Expression
            Hero hero = heroDao.findById(id);
            return hero;
        };

        // Execute the thread but the future.get() method will wait for the result before proceeding.
        // this method of will be use mostly if we need to get particular result.
        Future<Hero> future = HeroRoomDatabase.databaseWriteExecutor.submit(c);
        try {
            hero = future.get();

        } catch (ExecutionException e) {
            e.printStackTrace();
            //e.getMessage();
        } catch (InterruptedException e) {
            e.printStackTrace();
            //e.getMessage();;
        }
        return hero;
    }

   /* // Find hero by hero name
    public Hero findByName(String heroName){

        Callable c = () -> {   // Lambda Expression
            Hero hero = heroDao.findByName(heroName);
            return hero;
        };

        // Execute the thread but the future.get() method will wait for the result before proceeding.
        // this method of will be use mostly if we need to get particular result.
        Future<Hero> future = HeroRoomDatabase.databaseWriteExecutor.submit(c);
        try {
            hero = future.get();
        } catch (ExecutionException e) {
            e.getMessage();
        } catch (InterruptedException e) {
            e.getMessage();;
        }
        return hero;
    }*/
}
