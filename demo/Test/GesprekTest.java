import com.example.Account;
import com.example.Gesprek;
import com.example.Settings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

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

    @Test
    public void testNederlandsAntwoord() {
        Settings.setSelectedLanguage("Dutch");
        String vraag = "vraag";
        String expectedAntwoord = "Het antwoord op je vraag in het Nederlands: vraag";

        Assertions.assertEquals(expectedAntwoord, nieuwGesprek.maakAntwoord(vraag));
    }

    @Test
    public void testEngelsAntwoord() {
        Settings.setSelectedLanguage("English");
        String vraag = "vraag";
        String expectedAntwoord = "The answer on your question in English: vraag";

        Assertions.assertEquals(expectedAntwoord, nieuwGesprek.maakAntwoord(vraag));
    }

    @Test
    public void testDuitsAntwoord() {
        Settings.setSelectedLanguage("German");
        String vraag = "vraag";
        String expectedAntwoord = "Die Antwort auf Ihre Frage auf Deutsch: vraag";

        Assertions.assertEquals(expectedAntwoord, nieuwGesprek.maakAntwoord(vraag));
    }
}
