import com.example.Account;
import com.example.Gesprek;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GesprekTest {
    private Account testAccount;
    private Gesprek nieuwGesprek;

    @BeforeEach
    public void setup() {
        testAccount = new Account("testNaam", "testWachtwoord");
        nieuwGesprek = new Gesprek(testAccount, "testTitel");
    }

    @Test
    public void testVraag() {
        String vraag = "Nieuwe vraag";
        nieuwGesprek.addVraag(vraag);

        Assertions.assertEquals(vraag, nieuwGesprek.getRecenteVraag());
    }

    @Test
    public void testAntwoord() {
        String vraag = "Nieuwe vraag";
        nieuwGesprek.addVraag(vraag);
        String testAntwoord = nieuwGesprek.maakAntwoord(vraag);

        Assertions.assertEquals(testAntwoord, nieuwGesprek.getRecenteAntwoord());
    }

    @Test
    public void testDeleteGesprekGeschiedenis() {
        testAccount.deleteAllGesprekken();
        Assertions.assertTrue(testAccount.getGesprekken().isEmpty());
    }
}
