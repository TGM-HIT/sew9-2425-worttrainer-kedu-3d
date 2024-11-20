import org.junit.jupiter.api.Test;
import worttrainer.Model.Worteintrag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorteintragTest {

    @Test
    public void testValidUrl() {
        Worteintrag worteintrag = new Worteintrag("https://www.shutterstock.com/shutterstock/photos/2485889799/display_1500/stock-vector-cute-cartoon-corgi-dog-vector-kawaii-puppy-character-with-a-cheerful-face-expression-corgi-animal-2485889799.jpg", "Hund");
        assertTrue(worteintrag.checkUrl(), "Die URL sollte gültig sein.");
    }

    @Test
    public void testInvalidUrl() {
        Worteintrag worteintrag = new Worteintrag("invalid-url", "Haus");
        assertFalse(worteintrag.checkUrl(), "Die URL sollte ungültig sein.");
    }

    @Test
    public void testEmptyUrl() {
        Worteintrag worteintrag = new Worteintrag("", "Haus");
        assertFalse(worteintrag.checkUrl(), "Eine leere URL sollte als ungültig betrachtet werden.");
    }

    @Test
    public void testIsImgUrl() {
        assertTrue(Worteintrag.isImgUrl("https://example.com/image.png"));
        assertFalse(Worteintrag.isImgUrl("https://example.com/document.pdf"));
    }
}
