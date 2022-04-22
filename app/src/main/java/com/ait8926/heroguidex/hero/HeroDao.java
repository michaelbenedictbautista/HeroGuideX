package com.ait8926.heroguidex.hero;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Hero hero);

    @Update
    void update(Hero hero);

    @Delete
    void delete(Hero hero);

    @Query("SELECT * FROM HERO WHERE ID = :id")
    Hero findById(int id);

    @Query ("SELECT * FROM HERO WHERE HERO_NAME LIKE :heroName")
    Hero findByName(String heroName);

    @Query("SELECT * FROM HERO")
    LiveData<List<Hero>> findAll();

}
