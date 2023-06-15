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

    public ArrayList<String> getVragen() {
        return input;
    }

    public ArrayList<String> getAntwoorden() {
        return output;
    }

    public void addVraag(String vraag) {
        input.add(vraag);
    }

    public String maakAntwoord(String vraag) {
        String antwoord = generateAntwoord(vraag);
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
        output.clear();
        for(String vraag: input) {
            output.add(generateAntwoord(vraag));
        }
    }

    private String generateAntwoord(String input) {
        switch (Settings.getSelectedLanguage()) {
            case "English": return "The answer on your question in English: " + input;

            case "Dutch": return "Het antwoord op je vraag in het Nederlands: " + input;

            case "German": return "Die Antwort auf Ihre Frage auf Deutsch: " + input;
        }
        return "No language selected";
    }
}
