import com.example.Account;
import com.example.AccountManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest {

    @Test
    public void testExistingLogin() {
        Account existingAccount = new Account("testAccount", "welkom123456");
        AccountManager accManager = new AccountManager();
        accManager.addAccount(existingAccount);

        Assertions.assertTrue(accManager.accountExists("testAccount", accManager));
    }

    @Test
    public void testNonExistingLogin() {
        Account nonExistingAccount = new Account("testAccount", "welkom123456");
        AccountManager accountManager = new AccountManager();

        Assertions.assertFalse(accountManager.accountExists("testAccount", accountManager));
    }
}