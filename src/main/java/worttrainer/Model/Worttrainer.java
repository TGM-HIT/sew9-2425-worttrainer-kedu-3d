package worttrainer.Model;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worttrainer {

    private List<Worteintrag> wortliste = new ArrayList<>();    // Der Rechtschreibtrainer hat eine Menge an Wort-Bild-Paaren zur Verfügung. Anfangs ist kein Paar ausgewählt.

    private int richtigeWorte = 0;  // Statistik richtig
    private int falscheWorte = 0;   // Statistik falsch

    private Worteintrag aktuellerWorteintrag;     // Aktueller Worteintrag fuer die Vergleiche

    public void addWorteintrag(Worteintrag worteintrag) throws MalformedURLException {
        if (worteintrag != null && Worteintrag.checkUrl()) {
            wortliste.add(worteintrag);
        }
    }

    /**
     *  Random: Um zu trainieren kann ein Wort-Bild-Paar ausgewählt werden: entweder wird (mittels Index)
     *  ein bestimmtes Paar gewählt, oder ein zufälliges wird ausgewählt.
     */
    public void randomWorteintrag() {
        if (!wortliste.isEmpty()) {
            Random random = new Random();
            aktuellerWorteintrag = wortliste.get(random.nextInt(wortliste.size()));
        }
    }

    /**
     *  Auswahl: Um zu trainieren kann ein Wort-Bild-Paar ausgewählt werden: entweder wird (mittels Index)
     *  ein bestimmtes Paar gewählt, oder ein zufälliges wird ausgewählt.
     */
    public void selectWorteintrag(int index) {
        if (index >= 0 && index < wortliste.size()) {
            aktuellerWorteintrag = wortliste.get(index);
        }
    }

    /**
     * Nachdem ein Paar ausgewählt wurde, kann die Bild-URL abgerufen werden und das zugehörige Wort geraten werden.
     * Bei einer falschen Antwort bleibt das Wort-Bild-Paar ausgewählt;
     * Bei einer richtigen Antwort ist das Paar danach nicht mehr ausgewählt und es muss vor dem nächsten Raten
     * ein neues Paar ausgewählt werden.
     * @param wort Das Wort, welches zum Bild überprüft
     * @return true oder false
     */
    public boolean checkWort(String wort) {
        if (aktuellerWorteintrag != null && wort != null && !wort.isEmpty()) {
            if (wort.equalsIgnoreCase(aktuellerWorteintrag.getWort())) {
                richtigeWorte++;
                aktuellerWorteintrag = null;  // Paar ist richtig geraten, also deaktivieren
                return true;
            } else {
                falscheWorte++;
            }
        }
        return false;
    }

    public String getBildUrl() {
        if (aktuellerWorteintrag != null) {
            return aktuellerWorteintrag.getUrl();
        }
        return null;
    }

    public int getRichtigeWorte() {
        return richtigeWorte;
    }

    public int getFalscheWorte() {
        return falscheWorte;
    }
}
