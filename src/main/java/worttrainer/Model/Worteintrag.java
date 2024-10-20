package worttrainer.Model;

import java.net.MalformedURLException;
import java.net.URL;

public class Worteintrag {
    private static String url;
    private String wort;

    /**
     * Objekte dieser Klasse sind durch entsprechende Checks zu jeder Zeit in einem gültigen Zustand.
     * z.B. bzgl. null-Werte oder ungültiger URLs
     * @return true oder false
     * @throws MalformedURLException, falls die URL ungültig ist
     */
    public static boolean checkUrl() throws MalformedURLException {
        if (url == null || url.isEmpty()) {
            return false;
        }

        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            throw new MalformedURLException();
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
