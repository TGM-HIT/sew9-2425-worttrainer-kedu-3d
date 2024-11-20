package worttrainer.Persistenz;

import worttrainer.Model.Worteintrag;
import worttrainer.Model.Worttrainer;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasse um den Fortschritt des Wortrainers speichern und laden zu koennen
 * @author Kevin Duchon 5DHIT
 * @version 2024-10-20
 */
public class WorttrainerBackup {
    private static final String FILE_PATH = "worttrainer_session.json";

    /**
     * Speichert die aktuelle Session des Worttrainers in einer JSON-Datei
     * @param worttrainer zu speichernde Session
     * @throws IOException falls ein Fehler bei der Speicherung geschieht
     */
    public static void speichern(Worttrainer worttrainer) throws IOException {
        JSONObject jsonObj = new JSONObject();

        // Statistiken speichern
        jsonObj.put("richtigeWorte", worttrainer.getRichtigeWorte());
        jsonObj.put("falscheWorte", worttrainer.getFalscheWorte());

        // Aktueller Worteintrag
        Worteintrag aktueller = worttrainer.getAktuellerWorteintrag();
        if (aktueller != null) {
            jsonObj.put("aktuellerWorteintrag", new JSONObject()
                    .put("url", aktueller.getUrl())
                    .put("wort", aktueller.getWort()));
        }

        // Wortliste speichern
        JSONArray wortArray = new JSONArray();
        for (Worteintrag eintrag : worttrainer.getWortliste()) {
            JSONObject wortObj = new JSONObject();
            wortObj.put("url", eintrag.getUrl());
            wortObj.put("wort", eintrag.getWort());
            wortArray.put(wortObj);
        }
        jsonObj.put("wortliste", wortArray);

        // In Datei schreiben
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(jsonObj.toString(4)); // schöne Ausgabe
        }
    }

    /**
     * Lädt die gespeicherte Session aus der JSON-Datei und stellt den Zustand des Worttrainers wieder her
     * @return Die Worttrainer Session
     * @throws IOException Falls ein Fehler beim Laden des Worttrainers geschieht
     */
    public static Worttrainer laden() throws IOException {
        Worttrainer worttrainer = new Worttrainer();

        try (FileReader reader = new FileReader(FILE_PATH)) {
            StringBuilder sb = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                sb.append((char) i);
            }
            String jsonContent = sb.toString();

            JSONObject jsonObj = new JSONObject(jsonContent);

            // Statistiken wiederherstellen
            worttrainer.setRichtigeWorte(jsonObj.getInt("richtigeWorte"));
            worttrainer.setFalscheWorte(jsonObj.getInt("falscheWorte"));

            // Aktueller Worteintrag
            if (jsonObj.has("aktuellerWorteintrag")) {
                JSONObject aktuellerJson = jsonObj.getJSONObject("aktuellerWorteintrag");
                Worteintrag aktueller = new Worteintrag();
                aktueller.setUrl(aktuellerJson.getString("url"));
                aktueller.setWort(aktuellerJson.getString("wort"));
                worttrainer.setAktuellerWorteintrag(aktueller);
            }

            // Wortliste wiederherstellen
            JSONArray wortArray = jsonObj.getJSONArray("wortliste");
            List<Worteintrag> wortliste = new ArrayList<>();
            for (int j = 0; j < wortArray.length(); j++) {
                JSONObject wortObj = wortArray.getJSONObject(j);
                Worteintrag eintrag = new Worteintrag();
                eintrag.setUrl(wortObj.getString("url"));
                eintrag.setWort(wortObj.getString("wort"));
                wortliste.add(eintrag);
            }
            worttrainer.setWortliste(wortliste);
        }

        return worttrainer;
    }
}
