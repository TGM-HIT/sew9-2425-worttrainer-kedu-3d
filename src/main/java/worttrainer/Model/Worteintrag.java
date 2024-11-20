package worttrainer.Model;

import javax.swing.*;
import java.io.IOException;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Paare von Wörtern mit den dazugehörigen Bildern
 * @author Kevin Duchon 5DHIT
 * @version 2024-10-20
 */
public class Worteintrag {
    private String url;
    private String wort;

    // Define a pattern for validating image URLs
    private static final Pattern IMG_PATTERN = Pattern.compile(".*\\.(jpg|jpeg|png|webp|avif|gif)$", Pattern.CASE_INSENSITIVE);

    /**
     * Konstruktor
     * @param url Url zur Bild File
     * @param wort Wort welches erraten wird
     */
    public Worteintrag(String url, String wort) {
        this.url = url;
        this.wort = wort;
    }

    /**
     * Konstruktor
     */
    public Worteintrag() {
        this.url = "";
        this.wort = "";
    }

    /**
     * Objekte dieser Klasse sind durch entsprechende Checks zu jeder Zeit in einem gültigen Zustand.
     * z.B. bzgl. null-Werte oder ungültiger URLs
     * @return true oder false
     */
    public boolean checkUrl() {
        if (url == null || url.isBlank()) {
            return false;
        }

        try {
            URL urlObj = new URL(url);
            urlObj.toURI(); // Syntaktische Prüfung der URL

            // Zusätzliche HTTP-Anfrage, um sicherzustellen, dass die URL erreichbar ist und ein Bild zurückgibt
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.connect();

            // Überprüfen, ob die URL existiert und ein gültiges Bild zurückgibt
            String contentType = connection.getContentType();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK || !contentType.startsWith("image/")) {
                JOptionPane.showMessageDialog(null, "Die URL existiert nicht oder verweist nicht auf ein Bild.");
                return false;
            }

            // Prüfung auf Bild-Dateiendung als zusätzliche Absicherung
            return isImgUrl(url);

        } catch (URISyntaxException | MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Sie haben eine ungültige URL bei der Erstellung von einem Wort benutzt.");
            return false;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Die URL konnte nicht erreicht werden.");
            return false;
        }
    }

    /**
     * Method to check if the given URL points to an image.
     *
     * @param url The URL to check.
     * @return true if the URL is an image URL, false otherwise.
     */
    public static boolean isImgUrl(String url) {
        if (url == null) {
            return false;
        }
        Matcher matcher = IMG_PATTERN.matcher(url);
        return matcher.matches();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWort() {
        return wort;
    }

    public void setWort(String wort) {
        this.wort = wort;
    }

    @Override
    public String toString() {
        return "Worteintrag{" +
                "url='" + url + '\'' +
                ", wort='" + wort + '\'' +
                '}';
    }
}
