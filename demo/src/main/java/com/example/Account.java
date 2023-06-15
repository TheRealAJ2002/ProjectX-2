package com.example;

import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private ArrayList<Gesprek> gesprekken = new ArrayList<Gesprek>();

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addGesprek(Gesprek gesprek) {
        gesprekken.add(gesprek);
    }

    public void deleteGesprek(Gesprek gesprek) {
        gesprekken.remove(gesprek);
    }

    public void deleteAllGesprekken() {
        gesprekken.clear();
    }

    public void updateGesprekken() {
        for(Gesprek gesprek: gesprekken) {
            gesprek.update();
        }
    }

    public ArrayList<Gesprek> getGesprekken() {
        return gesprekken;
    }
}
