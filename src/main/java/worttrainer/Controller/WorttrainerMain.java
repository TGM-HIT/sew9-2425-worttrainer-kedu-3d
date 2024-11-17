package worttrainer.Controller;

import worttrainer.Model.Worteintrag;
import worttrainer.Model.Worttrainer;
import worttrainer.Persistenz.WorttrainerBackup;
import worttrainer.View.WortFrame;
import worttrainer.View.Worttraining;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

public class WorttrainerMain implements ActionListener {

    private Worttrainer worttrainer;
    private Worttraining worttraining;

    public WorttrainerMain() throws Exception {
        worttrainer = new Worttrainer();
        initializeWorteintraege(); // Beispiel-Einträge laden
        worttraining = new Worttraining(this, worttrainer); // View und Model verbinden
        new WortFrame("Wort-Trainer", worttraining); // GUI starten
    }

    private void initializeWorteintraege() {
        try {
            // Beispiel-Worteinträge hinzufügen
            worttrainer.addWorteintrag(new Worteintrag(
                    "https://www.shutterstock.com/shutterstock/photos/2485889799/display_1500/stock-vector-cute-cartoon-corgi-dog-vector-kawaii-puppy-character-with-a-cheerful-face-expression-corgi-animal-2485889799.jpg", "Hund"));
            worttrainer.addWorteintrag(new Worteintrag(
                    "https://media.os.fressnapf.com/cms/2020/05/Ratgeber_Katze_Erziehung_KittenOrange_1200x527.jpg", "Katze"));
            worttrainer.addWorteintrag(new Worteintrag(
                    "https://upload.wikimedia.org/wikipedia/commons/b/b6/Hellroter.jpg", "Papagei"));
        } catch (MalformedURLException e) {
            System.err.println("Fehler beim Initialisieren der Worteinträge: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "textfield":
                handleTextfield();
                break;

            case "zurueck":
                handleReset();
                break;

            case "speichern":
                handleSave();
                break;

            case "laden":
                handleLoad();
                break;

            case "liste":
                handleShowList();
                break;

            case "hilfe":
                handleHelp();
                break;

            case "worthinzu":
                handleHinzu();
                break;

            default:
                System.out.println("Unbekannter Befehl: " + command);
        }
    }

    private void handleTextfield() {
        String input = worttraining.getTextfield();
        if (worttrainer.checkWort(input)) {
            worttraining.wortRichtig(); // Zählt richtiges Wort hoch und aktualisiert Anzeige
        } else {
            worttraining.wortFalsch(); // Zählt falsches Wort hoch und aktualisiert Anzeige
        }

        worttraining.resetTextfeld(); // Eingabefeld zurücksetzen
        worttraining.changeImage(); // Neues Bild anzeigen
    }

    private void handleReset() {
        worttrainer.setRichtigeWorte(0); // Zähler zurücksetzen
        worttrainer.setFalscheWorte(0); // Zähler zurücksetzen
        worttraining.updateStatistics();
    }


    private void handleSave() {
        try {
            WorttrainerBackup.speichern(worttrainer);
            JOptionPane.showMessageDialog(null, "Fortschritt erfolgreich gespeichert!");
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    private void handleLoad() {
        try {
            this.worttrainer = WorttrainerBackup.laden(); // Controller aktualisieren
            worttraining.worttrainerLaden(this.worttrainer); // View aktualisieren

            // GUI-Elemente anpassen
            worttraining.updateStatistics();
            worttraining.changeImage(worttrainer.getBildUrl());
            JOptionPane.showMessageDialog(null, "Fortschritt erfolgreich geladen!");
        } catch (IOException e) {
            System.err.println("Fehler beim Laden: " + e.getMessage());
        }
    }

    private void handleShowList() {
        JOptionPane.showMessageDialog(null,
                worttrainer.listenAusgabe(),
                "Wortliste",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleHelp() {
        JOptionPane.showMessageDialog(null,
                "Anleitung:\n" +
                        "1. Geben Sie das Wort im Textfeld ein und drücken Sie Enter.\n" +
                        "2. Der Trainer zeigt Ihnen ein neues Bild, wenn Ihre Antwort korrekt war.\n" +
                        "3. Nutzen Sie die Buttons, um Fortschritte zu speichern oder zurückzusetzen.",
                "Hilfe",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleHinzu() {
        try {
            // Eingabe für das neue Wort
            String neuesWort = JOptionPane.showInputDialog(null, "Bitte geben Sie das neue Wort ein!");
            if (neuesWort == null || neuesWort.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Kein Wort eingegeben! Abbruch.", "Fehler", JOptionPane.ERROR_MESSAGE);
                return; // Keine Eingabe, Abbruch
            }

            // Eingabe für die URL
            String neuerURL = JOptionPane.showInputDialog(null, "Bitte geben Sie die dazugehörige URL an!");
            if (neuerURL == null || neuerURL.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Keine URL eingegeben! Abbruch.", "Fehler", JOptionPane.ERROR_MESSAGE);
                return; // Keine Eingabe, Abbruch
            }

            // Neues Worteintrag-Objekt erstellen und hinzufügen
            Worteintrag neuerEintrag = new Worteintrag(neuerURL, neuesWort); // URL, dann Wort
            worttrainer.addWorteintrag(neuerEintrag);

            // Optional: Erfolgsmeldung anzeigen
            JOptionPane.showMessageDialog(null, "Neuer Worteintrag erfolgreich hinzugefügt!");

            // GUI aktualisieren, falls nötig
            worttraining.changeImage(); // Zufälliges neues Bild anzeigen
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Die eingegebene URL ist ungültig!", "Fehler", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) throws Exception {
        new WorttrainerMain(); // Hauptprogramm starten
    }
}
