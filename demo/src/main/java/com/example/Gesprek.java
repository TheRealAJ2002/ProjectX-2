package com.example;

import java.util.ArrayList;
import java.util.Random;

public class Gesprek implements Observable {
    private Account gebruiker;
    private String titel;
    private ArrayList<String> input = new ArrayList<String>();
    private ArrayList<String> output = new ArrayList<String>();

    public Gesprek(Account account, String titel) {
        this.gebruiker = account;
        this.titel = titel;
        gebruiker.addGesprek(this);
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getTitel() {
        return titel;
    }

    public void addVraag(String vraag) {
        input.add(vraag);
    }

    public String maakAntwoord(String vraag) {
        String antwoord = "Hier is een antwoord op je vraag: " + vraag;
        output.add(antwoord);
        return antwoord;
    }

    public String getRecenteVraag() {
        return input.get(input.size() - 1);
    }

    public String getRecenteAntwoord() {
        return output.get(output.size() - 1);
    }

    public void update() {
        for(String vraag: input) {

        }
        for(String antwoord: output) {

        }
    }
}
