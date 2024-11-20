package worttrainer.View;

import javax.swing.*;

/**
 * Klasse um ein eigenes Fenster für den Worttrainer zu haben
 * @author Kevin Duchon 5DHIT
 * @version 2024-10-20
 */
public class WortFrame extends JFrame {

    /**
     * Konstruktor
     * @param title Titel des Fensters
     * @param worttraining View (GUI)
     */
    public WortFrame(String title, Worttraining worttraining) {
        super(title);
        this.add(worttraining);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(400, 100, 600, 500);
        this.setVisible(true);
    }


}
