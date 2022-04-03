package com.ait8926.heroguidex.hero;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Hero.class}, version = 1, exportSchema = false)
public abstract class HeroRoomDatabase  extends RoomDatabase {

    // Declare and create instance all my Data Access Object
    public abstract HeroDao heroDao();

    // Declare instance of HeroRoomDatabase and NUMBER_OF_THREADS
    private static volatile HeroRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    /* Provide number of thread pool to be used to run operations of
    query commands like Select, Insert, Update, Delete */
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // We only need to call instance of database to be created once
    public static HeroRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (HeroRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(), HeroRoomDatabase.class, "hero_database")
                            .addCallback(roomCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        // onCreate method will be executed ONCE when database is created.
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            populateInitialData(INSTANCE);
        }

        // onOpen method will be executed every time the database is opened or the app itself
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    // This will initialise declared data examples only. this is to populate data when we start the app
    private static void populateInitialData(HeroRoomDatabase instance) {
        //we execute database operations in threads
        HeroRoomDatabase.databaseWriteExecutor.execute( () -> {
            HeroDao heroDao = INSTANCE.heroDao();

            heroDao.insert(new Hero(75, 80, 65, 5, 1, "Alchemist", "Razzil Darkbrew, the Alchemist," +
                    " is a melee strength hero whose alchemical prowess makes him a strange but versatile fighter.", "Carry","hero_1"));
            heroDao.insert(new Hero(67,55,89,7,4,"Bristleback", "Rigwarl, the Bristleback, is a melee strength hero famous for" +
                    " his array of synergistic, simplistic, and yet effective spells with low mana costs and cooldowns.",
                    "Carry", "hero_2"));
            heroDao.insert(new Hero(98, 85, 47, 8, 5, "Chaos knight", "Chaos Knight is a melee strength hero with " +
                    "one of the highest physical damage output ceilings of all heroes.", "Carry", "hero_3"));
            heroDao.insert(new Hero(55, 88, 72, 3, 3, "GrimStroke", "Grimstroke is a ranged intelligence hero " +
                    "specializing as a nuker and offensive support.", "Support", "hero_4"));
            heroDao.insert(new Hero(80, 99,60,5,2,"Juggernaut","Yurnero, the Juggernaut, is a melee agility hero whose abilities " +
                    "allow him to sprint into battle and recklessly devastate enemies " +
                    "in an impenetrable flurry of blades.", "Carry", "hero_5"));
        });

    }

}
