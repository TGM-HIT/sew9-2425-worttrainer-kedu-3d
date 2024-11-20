package worttrainer.Controller;

import worttrainer.Model.Worteintrag;
import worttrainer.Model.Worttrainer;
import worttrainer.Persistenz.SaveJSON;
import worttrainer.Persistenz.SaveLoad;
import worttrainer.View.WortFrame;
import worttrainer.View.Worttraining;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

/**
 * Controller des Worttrainers
 * Hier werden Button klicks usw gehandelt
 * @author Kevin Duchon 5DHIT
 * @version 2024-10-20
 */
public class WorttrainerMain implements ActionListener {
    private Worttrainer worttrainer;
    private Worttraining worttraining;
    private SaveLoad saveLoad = new SaveJSON();


    /**
     * Konstruktor
     */
    public WorttrainerMain() {
        this.worttrainer = new Worttrainer();
        initializeWorteintraege();
        worttraining = new Worttraining(this, worttrainer);
        new WortFrame("Wort-Trainer", worttraining);
    }

    /**
     * Methode um Beispiel Worteintraege zu initialisieren
     */
    private void initializeWorteintraege() {
        try {
            // Beispiel-Worteinträge hinzufügen
            worttrainer.addWorteintrag(new Worteintrag(
                    "https://www.shutterstock.com/shutterstock/photos/2485889799/display_1500/stock-vector-cute-cartoon-corgi-dog-vector-kawaii-puppy-character-with-a-cheerful-face-expression-corgi-animal-2485889799.jpg", "Hund"));
            worttrainer.addWorteintrag(new Worteintrag(
                    "https://media.os.fressnapf.com/cms/2020/05/Ratgeber_Katze_Erziehung_KittenOrange_1200x527.jpg", "Katze"));
            worttrainer.addWorteintrag(new Worteintrag(
                    "https://upload.wikimedia.org/wikipedia/commons/b/b6/Hellroter.jpg", "Papagei"));
            // Worthinzufuegen test: https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeWyF_4oGUtv4kFW5xCSMQPLHRIoNaYkwIfw&s.png, Pelikan
        } catch (MalformedURLException e) {
            System.err.println("Fehler beim Initialisieren der Worteinträge: " + e.getMessage());
        }
    }

    /**
     * ActionPerformed über die GUI und falls ein Ereignis wie ein Button klick geschieht, dann arbeitet er damit.
     * @param e the event to be processed
     */
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

    /**
     * Bei Textfeld Bestätigung wird gecheckt ob die Eingabe valide ist
     */
    private void handleTextfield() {
        String input = worttraining.getTextfield();
        if (worttrainer.checkWort(input)) {
            worttraining.wortRichtig();
        } else {
            worttraining.wortFalsch();
        }
        worttraining.resetTextfeld();
        worttraining.changeImage();
    }

    /**
     * Methode für zurücksetzen des Fortschritts
     */
    private void handleReset() {
        worttraining.reset();
    }

    /**
     * Methode zum Speichern eines Fortschritts
     */
    private void handleSave() {
        saveLoad.speichern(worttrainer, "worttrainer_session.json");
        JOptionPane.showMessageDialog(null, "Fortschritt erfolgreich gespeichert!");
    }

    /**
     * Methode zum Laden eines Fortschritts
     */
    private void handleLoad() {
        this.worttrainer = saveLoad.laden("worttrainer_session.json");
        worttraining.worttrainerLaden(this.worttrainer);
        worttraining.updateStatistics();
        worttraining.changeImage(worttrainer.getBildUrl());
        JOptionPane.showMessageDialog(null, "Fortschritt erfolgreich geladen!");
    }

    /**
     * Methode zur Ausgabe der Wortliste
     */
    private void handleShowList() {
        JOptionPane.showMessageDialog(null,
                worttrainer.listenAusgabe(),
                "Wortliste",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Methode für eine Beschreibung des Worttrainers
     */
    private void handleHelp() {
        JOptionPane.showMessageDialog(null,
                "Anleitung:\n" +
                        "1. Geben Sie das Wort im Textfeld ein und drücken Sie Enter.\n" +
                        "2. Der Trainer zeigt Ihnen ein neues Bild und Sie sehen Ihren Punktestand links unten.\n" +
                        "3. Nutzen Sie die Buttons, um Fortschritte zu speichern oder zurückzusetzen.",
                "Hilfe",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Methode zum Hinzufügen eines Wortes (inkl. Url)
     */
    private void handleHinzu() {
        try {
            // Benutzer wird nach dem neuen Wort gefragt
            String neuesWort = JOptionPane.showInputDialog(null, "Bitte geben Sie das neue Wort ein!");
            if (neuesWort == null || neuesWort.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Kein Wort eingegeben! Abbruch.", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Benutzer wird nach der zugehörigen URL gefragt
            String neuerURL = JOptionPane.showInputDialog(null, "Bitte geben Sie die dazugehörige URL an!");
            if (neuerURL == null || neuerURL.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Keine URL eingegeben! Abbruch.", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Überprüfen, ob die URL gültig ist
            Worteintrag neuerEintrag = new Worteintrag(neuerURL, neuesWort);
            if (!neuerEintrag.checkUrl()) {
                // Falls die URL ungültig ist, wird eine Fehlermeldung angezeigt
                JOptionPane.showMessageDialog(null, "Die angegebene URL ist ungültig oder verweist nicht auf ein Bild.", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Hinzufügen des neuen Wortes und der URL zur Liste
            worttrainer.addWorteintrag(neuerEintrag);
            JOptionPane.showMessageDialog(null, "Neuer Worteintrag erfolgreich hinzugefügt!");

            // Bild für den neuen Eintrag aktualisieren
            worttraining.changeImage();

        } catch (Exception e) {
            // Allgemeine Fehlerbehandlung
            JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * Starten
     */
    public static void main(String[] args) throws Exception {
        new WorttrainerMain();
    }
}