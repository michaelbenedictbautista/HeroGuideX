package com.ait8926.heroguidex.hero;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "HERO")
public class Hero implements Serializable {

    // It will autogenerate primary key which is id
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    private int id;


    @ColumnInfo(name = "HERO_NAME")
    private String heroName;

    @ColumnInfo
    private String history;
    private String role;
    private String image;

    private int attackDamage;
    private int attackSpeed;
    private int defense;
    private int votes;
    private int rating;


    // Constructor without parameter
    @Ignore
    public Hero() {
    }

    // Constructor with parameters
    public Hero(int attackDamage, int attackSpeed, int defense, int votes, int rating,
                String heroName, String history, String role, String image) {
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.defense = defense;
        this.votes = votes;
        this.rating = rating;
        this.heroName = heroName;
        this.history = history;
        this.role = role;
        this.image = image;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @NonNull
    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", heroName='" + heroName + '\'' +
                ", history='" + history + '\'' +
                ", attack role='" + role + '\'' +
                ", image=" + image +
                ", attackDamage=" + attackDamage +
                ", attack attackSpeed=" + attackSpeed +
                ", attack defense=" + defense +
                ", movement votes=" + votes +
                ", rating=" + rating +
                '}';
    }
}