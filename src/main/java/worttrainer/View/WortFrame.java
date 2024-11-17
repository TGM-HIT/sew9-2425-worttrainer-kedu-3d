package worttrainer.View;

import javax.swing.*;

public class WortFrame extends JFrame {

    public WortFrame(String title, Worttraining worttraining) {
        super(title);
        this.add(worttraining);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(400, 100, 600, 500);
        this.setVisible(true);
    }


}
