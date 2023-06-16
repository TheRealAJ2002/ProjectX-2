import com.example.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
    private Account testAccount;


    @BeforeEach
    public void setup() {
        testAccount = new Account("testNaam", "testWachtwoord");
    }

    @Test
    public void setUsernaamTest() {
        testAccount.setUsername("nieuwNaam");
        Assertions.assertEquals("nieuwNaam", testAccount.getUsername());
    }

    @Test
    public void setPasswordTest() {
        testAccount.setPassword("nieuwWachtwoord");
        Assertions.assertEquals("nieuwWachtwoord", testAccount.getPassword());
    }
}
