package com.example;

public class Settings {
    private static String selectedLanguage = "English";

    public static String getSelectedLanguage() {
        return selectedLanguage;
    }

    public static void setSelectedLanguage(String language) {
        selectedLanguage = language;
    }
}