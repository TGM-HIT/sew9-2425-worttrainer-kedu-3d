import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import worttrainer.Model.Worteintrag;
import worttrainer.Model.Worttrainer;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

public class WorttrainerTest {

    private Worttrainer worttrainer;

    @BeforeEach
    public void setUp() {
        worttrainer = new Worttrainer();
    }

    @Test
    public void testAddWorteintrag() throws MalformedURLException {
        Worteintrag worteintrag = new Worteintrag("https://www.shutterstock.com/shutterstock/photos/2485889799/display_1500/stock-vector-cute-cartoon-corgi-dog-vector-kawaii-puppy-character-with-a-cheerful-face-expression-corgi-animal-2485889799.jpg", "Hund");
        worttrainer.addWorteintrag(worteintrag);

        assertEquals(1, worttrainer.getWortliste().size(), "Worteintrag sollte hinzugefügt werden.");
        assertEquals("Hund", worttrainer.getWortliste().get(0).getWort(), "Das Wort sollte 'Haus' sein.");
    }

    @Test
    public void testAddInvalidWorteintrag() throws MalformedURLException {
        Worteintrag invalidEintrag = new Worteintrag("invalid-url", "Baum");
        worttrainer.addWorteintrag(invalidEintrag);

        assertEquals(0, worttrainer.getWortliste().size(), "Ungültiger Worteintrag sollte nicht hinzugefügt werden.");
    }

    @Test
    public void testCheckWort() throws MalformedURLException {
        Worteintrag worteintrag = new Worteintrag("https://www.shutterstock.com/shutterstock/photos/2485889799/display_1500/stock-vector-cute-cartoon-corgi-dog-vector-kawaii-puppy-character-with-a-cheerful-face-expression-corgi-animal-2485889799.jpg", "Auto");
        worttrainer.addWorteintrag(worteintrag);
        worttrainer.selectWorteintrag(0);

        assertTrue(worttrainer.checkWort("Auto"), "Das Wort sollte korrekt erkannt werden.");
        assertEquals(1, worttrainer.getRichtigeWorte(), "Die Anzahl der richtigen Worte sollte 1 sein.");
    }

    @Test
    public void testCheckIncorrectWort() throws MalformedURLException {
        Worteintrag worteintrag = new Worteintrag("https://media.os.fressnapf.com/cms/2020/05/Ratgeber_Katze_Erziehung_KittenOrange_1200x527.jpg", "Fahrrad");
        worttrainer.addWorteintrag(worteintrag);
        worttrainer.selectWorteintrag(0);

        assertFalse(worttrainer.checkWort("Auto"), "Das Wort sollte als falsch erkannt werden.");
        assertEquals(1, worttrainer.getFalscheWorte(), "Die Anzahl der falschen Worte sollte 1 sein.");
    }

    @Test
    public void testRandomWorteintrag() throws MalformedURLException {
        worttrainer.addWorteintrag(new Worteintrag("https://www.shutterstock.com/shutterstock/photos/2485889799/display_1500/stock-vector-cute-cartoon-corgi-dog-vector-kawaii-puppy-character-with-a-cheerful-face-expression-corgi-animal-2485889799.jpg", "Haus"));
        worttrainer.addWorteintrag(new Worteintrag("https://www.shutterstock.com/shutterstock/photos/2485889799/display_1500/stock-vector-cute-cartoon-corgi-dog-vector-kawaii-puppy-character-with-a-cheerful-face-expression-corgi-animal-2485889799.jpg", "Baum"));
        worttrainer.randomWorteintrag();

        assertNotNull(worttrainer.getAktuellerWorteintrag(), "Ein zufälliger Worteintrag sollte ausgewählt werden.");
    }

    @Test
    public void testListenAusgabe() throws MalformedURLException {
        worttrainer.addWorteintrag(new Worteintrag("https://www.shutterstock.com/shutterstock/photos/2485889799/display_1500/stock-vector-cute-cartoon-corgi-dog-vector-kawaii-puppy-character-with-a-cheerful-face-expression-corgi-animal-2485889799.jpg", "Haus"));
        worttrainer.addWorteintrag(new Worteintrag("https://www.shutterstock.com/shutterstock/photos/2485889799/display_1500/stock-vector-cute-cartoon-corgi-dog-vector-kawaii-puppy-character-with-a-cheerful-face-expression-corgi-animal-2485889799.jpg", "Baum"));
        String liste = worttrainer.listenAusgabe();

        assertTrue(liste.contains("Haus"), "Die Ausgabe sollte das Wort 'Haus' enthalten.");
        assertTrue(liste.contains("Baum"), "Die Ausgabe sollte das Wort 'Baum' enthalten.");
    }
}
