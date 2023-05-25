import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private List<Account> accounts;

    public AccountManager() {
        accounts = new ArrayList<>();
    }

    public boolean login(String username, String password) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean createAccount(String username, String password) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return false; // Username already exists
            }
        }

        Account newAccount = new Account(username, password);
        accounts.add(newAccount);
        return true;
    }
}
