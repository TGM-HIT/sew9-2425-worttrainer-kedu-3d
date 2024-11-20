package worttrainer.View;
import worttrainer.Controller.WorttrainerMain;
import worttrainer.Model.Worttrainer;
import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * View des Worttrainers
 * @author Kevin Duchon 5DHIT
 * @version 2024-10-20
 */
public class Worttraining extends JPanel {
    private Worttrainer wortTrainer;
    private JLabel l1, l2, l3, l4, l5;
    private JButton zuruecksetzen, worthinzu, speichern, laden, printListe, hilfe;
    private ImageIcon icon;
    private JTextField t1;
    private JLabel lImage;
    private int rWorte;
    private int gWorte;

    /**
     * View des Worttrainers (GUI)
     * Erstellen der Layouts, Textfelder und Buttons
     * @param cr WorttrainerMain
     * @param wortTrainer Model
     */
    public Worttraining(WorttrainerMain cr, Worttrainer wortTrainer) {
        this.wortTrainer = wortTrainer;
        this.wortTrainer.randomWorteintrag();
        BorderLayout bl = new BorderLayout(40, 20);
        this.setLayout(bl);
        l1 = new JLabel("Welches Wort wird unten dargestellt (Eingabe zum Ueberpruefen)?");
        t1 = new JTextField();
        t1.addActionListener(cr);
        t1.setActionCommand("textfield");
        JPanel obenLinks = new JPanel(new GridLayout(2, 1));
        obenLinks.add(l1);
        obenLinks.add(t1);
        this.add(obenLinks, BorderLayout.PAGE_START);
        String imageString = wortTrainer.getBildUrl();
        icon = loadImageIcon(imageString);
        lImage = new JLabel(icon);
        this.add(lImage, BorderLayout.CENTER);
        JPanel rechts = new JPanel();
        rechts.setLayout(new BoxLayout(rechts, BoxLayout.PAGE_AXIS));
        speichern = createButton("Fortschritt speichern", "speichern", cr);
        laden = createButton("Fortschritt laden", "laden", cr);
        printListe = createButton("Wortliste ausgeben", "liste", cr);
        hilfe = createButton("Hilfe", "hilfe", cr);
        rechts.add(speichern);
        rechts.add(laden);
        rechts.add(printListe);
        rechts.add(hilfe);
        this.add(rechts, BorderLayout.EAST);
        JPanel unten = new JPanel(new GridLayout(2, 3));
        l2 = new JLabel("Richtige Woerter:");
        l3 = new JLabel("Anzahl Woerter:");
        l4 = new JLabel("" + rWorte);
        l5 = new JLabel("" + gWorte);
        zuruecksetzen = createButton("Zuruecksetzen", "zurueck", cr);
        worthinzu = createButton("Wort hinzufuegen", "worthinzu", cr);
        unten.add(l2);
        unten.add(l4);
        unten.add(zuruecksetzen);
        unten.add(l3);
        unten.add(l5);
        unten.add(worthinzu);
        this.add(unten, BorderLayout.PAGE_END);
    }

    /**
     * Methode zum Vereinfachen der vielen Button Erstellungen
     * @param text Text welcher im Button steht
     * @param actionCommand Um Ereignisquellen zu unterscheiden
     * @param cr Button an den Controller binden
     * @return fertige Button
     */
    private JButton createButton(String text, String actionCommand, WorttrainerMain cr) {
        JButton button = new JButton(text);
        button.setActionCommand(actionCommand);
        button.addActionListener(cr);
        return button;
    }

    /**
     * Methode um Bild anhand einer URL zu laden
     * @param imageString Das zu ladene Bild
     * @return Bild oder null falls Exception
     */
    private ImageIcon loadImageIcon(String imageString) {
        try {
            URL url = new URL(imageString);
            ImageIcon newIcon = new ImageIcon(url);
            Image img = newIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (MalformedURLException e) {
            System.out.println("Bitte gueltige URL eingeben!\nDiese muss wie folgt aufgebaut sein: https://a.a oder http://a.a");
            return null;
        }
    }

    /**
     * Methode um die View zurueckzusetzen
     */
    public void reset() {
        wortTrainer.setRichtigeWorte(0);
        wortTrainer.setFalscheWorte(0);
        updateStatistics();
    }

    /**
     * GUI updaten nach Eingabe eines Wortes
     */
    public void wortRichtig() {
        updateStatistics();
    }
    /**
     * GUI updaten nach Eingabe eines Wortes
     */
    public void wortFalsch() {
        updateStatistics();
    }

    /**
     * Zufällig generierts Bild anzeigen
     */
    public void changeImage() {
        wortTrainer.randomWorteintrag();
        lImage.setIcon(loadImageIcon(wortTrainer.getBildUrl()));
    }

    /**
     * Ausgewähltes Bild anzeigen
     * @param bildURL ausgewähltes Bild
     */
    public void changeImage(String bildURL) {
        lImage.setIcon(loadImageIcon(bildURL));
    }
    public String getTextfield() {
        return t1.getText();
    }

    /**
     * Methode um View vom Textfeld nach einer Eingabe zuruecksetzen zu koennen
     */
    public void resetTextfeld() {
        t1.setText("");
    }

    /**
     * Methode um Worttrainer zu laden
     * @param neuesWorttrainer Der neue geladene Worttrainer
     */
    public void worttrainerLaden(Worttrainer neuesWorttrainer) {
        this.wortTrainer = neuesWorttrainer;
        updateStatistics();
    }

    /**
     * View updaten, falls Wortanzahl erhoeht wird
     */
    public void updateStatistics() {
        rWorte = wortTrainer.getRichtigeWorte();
        gWorte = rWorte + wortTrainer.getFalscheWorte();
        l4.setText("" + rWorte);
        l5.setText("" + gWorte);
    }
}