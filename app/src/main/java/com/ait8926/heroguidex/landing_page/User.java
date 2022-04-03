package com.ait8926.heroguidex.landing_page;

import java.io.Serializable;

public class User implements Serializable {

    private static String userName;

    // Constructor without parameters
    public User() {

    }

    //  // Constructor with parameters
    public User(String userName) {
        this.userName = userName;
    }

    // Getters and setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) { this.userName = userName; }

  /*  @Override
    public String toString() {
        return "User{" +
                "username='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }*/
}
