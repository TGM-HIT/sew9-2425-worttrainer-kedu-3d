package worttrainer.Model;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Das Model des Rechtschreibtrainers
 * @author Kevin Duchon 5DHIT
 * @version 2024-10-20
 */
public class Worttrainer {

    private List<Worteintrag> wortliste = new ArrayList<>();

    private int richtigeWorte = 0;
    private int falscheWorte = 0;

    private Worteintrag aktuellerWorteintrag;

    public void addWorteintrag(Worteintrag worteintrag) throws MalformedURLException {
        if (worteintrag != null && worteintrag.checkUrl()) {
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
                aktuellerWorteintrag = null;
                return true;
            } else {
                falscheWorte++;
            }
        }
        return false;
    }

    /**
     * Methode zur ausgabe der Liste für die GUI
     * @return Liste mit allen Wörtern und der dazugehörigen URL
     */
    public String listenAusgabe() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < wortliste.size(); i++) {
            builder.append(wortliste.get(i)).append("\n");
        }

        return builder.toString();
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

    public Worteintrag getAktuellerWorteintrag() {
        return aktuellerWorteintrag;
    }

    public void setAktuellerWorteintrag(Worteintrag aktuellerWorteintrag) {
        this.aktuellerWorteintrag = aktuellerWorteintrag;
    }

    public List<Worteintrag> getWortliste() {
        return wortliste;
    }

    public void setWortliste(List<Worteintrag> wortliste) {
        this.wortliste = wortliste;
    }

    public void setRichtigeWorte(int richtigeWorte) {
        this.richtigeWorte = richtigeWorte;
    }

    public void setFalscheWorte(int falscheWorte) {
        this.falscheWorte = falscheWorte;
    }
}
