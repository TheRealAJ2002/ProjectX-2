package com.example;

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

    public boolean accountExists(String username, AccountManager accountManager) {
        return accountManager.getAccounts().stream()
                .anyMatch(account -> account.getUsername().equals(username));
    }

}
