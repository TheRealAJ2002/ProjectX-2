import com.example.Account;
import com.example.AccountManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest() {

    @Test
    private void testExistingLogin() {
        Account existingAccount = new Account("testAccount", "welkom123456");
        AccountManager accManager = new AccountManager();
        accManager.addAccount(existingAccount);

        assertTrue(accManager.accountExists("testAccount", accManager));
    }
}