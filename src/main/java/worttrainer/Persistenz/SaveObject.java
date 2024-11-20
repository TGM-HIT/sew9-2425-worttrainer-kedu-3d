package worttrainer.Persistenz;

import worttrainer.Model.Worttrainer;

import java.io.*;

/**
 * Klasse zum Speichern und Laden von Worttrainer-Objekten
 * @author Kevin Duchon 5DHIT
 * @version 2024-10-20
 */
public class SaveObject implements SaveLoad {

    /**
     * Speichert ein Worttrainer-Objekt in einer Datei
     * @param worttrainer Das zu speichernde Worttrainer-Objekt.
     * @param pfad        Der Dateipfad, unter dem das Objekt gespeichert werden soll.
     */
    public void speichern(Worttrainer worttrainer, String pfad) {
        if (worttrainer == null) {
            throw new IllegalArgumentException("Worttrainer darf nicht null sein.");
        }

        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(pfad))) {
            os.writeObject(worttrainer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lädt ein Worttrainer-Objekt aus einer Datei.
     * @param pfad Der Dateipfad, von dem das Objekt geladen werden soll.
     * @return Das geladene Worttrainer-Objekt.
     */
    public Worttrainer laden(String pfad) {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(pfad))) {
            return (Worttrainer) is.readObject();
        } catch (ClassNotFoundException | IOException e) {
            try {
                throw new IOException("Die Klasse des gespeicherten Objekts konnte nicht gefunden werden.", e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
