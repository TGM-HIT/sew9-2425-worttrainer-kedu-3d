package worttrainer.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Paare von Wörtern mit den dazugehörigen Bildern
 * @author Kevin Duchon 5DHIT
 * @version 2024-10-20
 */
public class Worteintrag {
    private String url;
    private String wort;

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
            URL imageUrl = new URL(url);
            BufferedImage image = ImageIO.read(imageUrl);

            return image != null;
        } catch (IOException e) {
            return false;
        }
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
