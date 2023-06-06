package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private static List<Account> accounts = new ArrayList<>();

    static {
        accounts.add(new Account("Alexander", "welkom123456"));
    }
    public static List<Account> getAccounts() {
        return accounts;
    }

    public static void addAccount(Account account) {
        accounts.add(account);
    }
}
