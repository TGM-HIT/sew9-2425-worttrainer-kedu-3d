package worttrainer.View;

import worttrainer.Controller.WorttrainerMain;
import worttrainer.Model.Worttrainer;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Worttraining extends JPanel {

    private Worttrainer wortTrainer;
    private JLabel l1, l2, l3, l4, l5;
    private JButton zuruecksetzen, worthinzu, speichern, laden, printListe, hilfe;
    private ImageIcon icon;
    private JTextField t1;
    private JLabel lImage;
    private int rWorte;
    private int gWorte;

    public Worttraining(WorttrainerMain cr, Worttrainer wortTrainer) throws Exception {
        this.wortTrainer = wortTrainer;
        this.wortTrainer.randomWorteintrag(); // Ein zufälliges Paar auswählen

        // Layout und Initialisierung der Benutzeroberfläche
        BorderLayout bl = new BorderLayout(40, 20);
        this.setLayout(bl);

        // Oben: Eingabeaufforderung und Textfeld
        l1 = new JLabel("Welches Wort wird unten dargestellt (Eingabe zum Überprüfen)?");
        t1 = new JTextField();
        t1.addActionListener(cr);
        t1.setActionCommand("textfield");

        JPanel obenLinks = new JPanel(new GridLayout(2, 1));
        obenLinks.add(l1);
        obenLinks.add(t1);
        this.add(obenLinks, BorderLayout.PAGE_START);

        // Center: Bildanzeige
        String imageString = wortTrainer.getBildUrl();
        icon = loadImageIcon(imageString);
        lImage = new JLabel(icon);
        this.add(lImage, BorderLayout.CENTER);

        // Rechts: Steuerungsbuttons
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

        // Unten: Statistiken und Zusatzoptionen
        JPanel unten = new JPanel(new GridLayout(2, 3));
        l2 = new JLabel("Richtige Wörter:");
        l3 = new JLabel("Anzahl Wörter:");
        l4 = new JLabel("" + rWorte);
        l5 = new JLabel("" + gWorte);
        zuruecksetzen = createButton("Zurücksetzen", "zurueck", cr);
        worthinzu = createButton("Wort hinzufügen", "worthinzu", cr);

        unten.add(l2);
        unten.add(l4);
        unten.add(zuruecksetzen);
        unten.add(l3);
        unten.add(l5);
        unten.add(worthinzu);
        this.add(unten, BorderLayout.PAGE_END);
    }

    private JButton createButton(String text, String actionCommand, WorttrainerMain cr) {
        JButton button = new JButton(text);
        button.setActionCommand(actionCommand);
        button.addActionListener(cr);
        return button;
    }

    private ImageIcon loadImageIcon(String imageString) {
        try {
            URL url = new URL(imageString);
            ImageIcon newIcon = new ImageIcon(url);
            Image img = newIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (MalformedURLException e) {
            System.out.println("Bitte gültige URL eingeben!\nDiese muss wie folgt aufgebaut sein: https://a.a oder http://a.a");
            return null;
        }
    }

    public void reset() {
        wortTrainer.setRichtigeWorte(0);
        wortTrainer.setFalscheWorte(0);
        updateStatistics();
    }

    public void wortRichtig() {
        wortTrainer.setRichtigeWorte(wortTrainer.getRichtigeWorte() + 1);
        updateStatistics();
    }

    public void wortFalsch() {
        wortTrainer.setFalscheWorte(wortTrainer.getFalscheWorte() + 1);
        updateStatistics();
    }

    public void changeImage() {
        wortTrainer.randomWorteintrag();
        lImage.setIcon(loadImageIcon(wortTrainer.getBildUrl()));
    }

    public void changeImage(String bildURL) {
        lImage.setIcon(loadImageIcon(bildURL));
    }


    public String getTextfield() {
        return t1.getText();
    }

    public void resetTextfeld() {
        t1.setText("");
    }

    public void worttrainerLaden(Worttrainer neuesWorttrainer) {
        this.wortTrainer = neuesWorttrainer; // Neues Model setzen
        updateStatistics();
    }


    public void updateStatistics() {
        rWorte = wortTrainer.getRichtigeWorte();
        gWorte = rWorte + wortTrainer.getFalscheWorte();
        l4.setText("" + rWorte);
        l5.setText("" + gWorte);
    }
}
