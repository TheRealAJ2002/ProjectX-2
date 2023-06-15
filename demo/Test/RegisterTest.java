import com.example.Account;
import com.example.AccountManager;
import com.example.Register;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegisterTest {
    private Register register = new Register();

    // User registration tests

    @Test
    public void testUserRegistration() {
        AccountManager.addAccount(new Account("existingUsername", "password1234"));
        AccountManager.addAccount(new Account("exists", "password1234"));

        // Conditions = 0 && 0 && 0
        Assertions.assertFalse(register.isValidUserRegistration("exists", "invalid", "alsoInvalid"));

        // Conditions = 0 && 0 && 1
        Assertions.assertFalse(register.isValidUserRegistration("password1234", "password1234", "invalid"));

        // Conditions = 0 && 1 && 0
        Assertions.assertFalse(register.isValidUserRegistration("testUser", "invalid", "alsoInvalid"));

        // Conditions = 0 && 1 && 1
        Assertions.assertFalse(register.isValidUserRegistration("existingUsername", "password1234", "password1234"));

        // Conditions = 1 && 0 & 0
        Assertions.assertFalse(register.isValidUserRegistration("short", "invalid", "alsoInvalid"));

        // Conditions = 1 && 0 & 1
        Assertions.assertFalse(register.isValidUserRegistration("short", "password12345", "password1234"));

        // Conditions = 1 && 1 & 0
        Assertions.assertFalse(register.isValidUserRegistration("testUser", "invalid", "alsoInvalid"));

        // Conditions = 1 && 1 && 1
        Assertions.assertTrue(register.isValidUserRegistration("testUser", "password1234", "password1234"));

    }

    // Password validation tests

    @Test
    public void testPasswordValidation() {
        // conditions = 0 && 0 && 0
        Assertions.assertFalse(register.isValidPassword("password1", "noNumbersPassword"));

        // conditions = 0 && 0 && 1
        Assertions.assertFalse(register.isValidPassword("short", "short"));

        // conditions = 0 && 1 && 0
        Assertions.assertFalse(register.isValidPassword("password1", "differentPassword1"));

        // conditions = 0 && 1 && 1
        Assertions.assertFalse(register.isValidPassword("password1", "password1"));

        // conditions = 1 && 0 && 0
        Assertions.assertFalse(register.isValidPassword("noNumbersPassword", "differentPassword"));

        // conditions = 1 && 0 && 1
        Assertions.assertFalse(register.isValidPassword("noNumbersPassword", "noNumbersPassword"));

        // conditions = 1 && 1 && 0
        Assertions.assertFalse(register.isValidPassword("password1234", "differentPassword1"));

        // conditions = 1 && 1 && 1
        Assertions.assertTrue(register.isValidPassword("password1234", "password1234"));
    }
}
