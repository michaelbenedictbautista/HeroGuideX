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
                    " is a melee strength hero whose alchemical prowess makes him a strange but versatile fighter.", "Carry","hero_1","hero1_audio"));
            heroDao.insert(new Hero(67,55,89,7,4,"Bristleback", "Rigwarl, the Bristleback, is a melee strength hero famous for" +
                    " his array of synergistic, simplistic, and yet effective spells with low mana costs and cooldowns.",
                    "Carry", "hero_2", "hero2_audio"));
            heroDao.insert(new Hero(98, 85, 47, 8, 5, "Chaos knight", "Chaos Knight is a melee strength hero with " +
                    "one of the highest physical damage output ceilings of all heroes.", "Carry", "hero_3", "hero3_audio"));
            heroDao.insert(new Hero(55, 88, 72, 3, 3, "GrimStroke", "Grimstroke is a ranged intelligence hero " +
                    "specializing as a nuker and offensive support.", "Support", "hero_4", "hero4_audio"));
            heroDao.insert(new Hero(80, 99,60,5,2,"Juggernaut","No one has ever seen the face hidden beneath the mask of Yurnero the Juggernaut. " +
                    "It is only speculation that he even has one. For defying a corrupt lord, Yurnero was exiled from the ancient Isle of Masks--a punishment that saved his life. " +
                    "The isle soon after vanished beneath the waves in a night of vengeful magic. He alone remains to carry on the Isle's long Juggernaut tradition, one of ritual and swordplay.", "Carry", "hero_5", "hero5_audio"));
            heroDao.insert(new Hero(70, 80,50,9,4,"Shadow Fiend","It is said that Nevermore the Shadow Fiend has the soul of a poet, " +
                    "and in fact he has thousands of them. Over the ages he has claimed the souls of poets, priests, emperors, beggars, slaves, philosophers, criminals and " +
                    "(naturally) heroes; no sort of soul escapes him.", "Carry", "hero_6", "hero6_audio"));
            heroDao.insert(new Hero(85, 90,60,10,5,"Phantom Assasin","Through a process of divination, children are selected " +
                    "for upbringing by the Sisters of the Veil, an order that considers assassination a sacred part of the natural order. " +
                    "The Veiled Sisters identify targets through meditation and oracular utterances.", "Carry", "hero_7", "hero7_audio"));
            heroDao.insert(new Hero(62, 65,55,9,4,"Puck","While Puck seems at first glance a mischievous, childish character, " +
                    "this quality masks an alien personality. The juvenile form of a Faerie Dragon, a creature that lives for eons, Puck spends countless millennia in its childish form. " +
                    "So while it is technically true that Puck is juvenile, it will continue to be so when the cities of the present age have sloughed away into dust.", "Initiator", "hero_8", "hero8_audio"));
            heroDao.insert(new Hero(72, 50,90,10,4,"Pudge","In the Fields of Endless Carnage, far to the south of Quoidge, a corpulent figure works tirelessly through the night--dismembering, " +
                    "disembowelling, piling up the limbs and viscera of the fallen that the battlefield might be clear by dawn. " +
                    "In this cursed realm, nothing can decay or decompose; no corpse may ever return to the earth from which it sprang, no matter how deep you dig the grave.", "Initiator", "hero_9", "hero9_audio"));
            heroDao.insert(new Hero(90, 75,84,9,5,"Wraith King","For untold years, King Ostarion built a kingdom from the remains of his enemies. " +
                    "It was an obsessive's errand, done to pass the long eternities of a monarchy that seemed fated never to end. He believed that as long as he built up the towers of his palace, he could not die. " +
                    "But eventually he learned that he had been deluded... that bone itself could perish.", "Carry", "hero_10", "hero10_audio"));
        });

    }

}
