package templates;


import java.awt.FlowLayout;

import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    protected DoTemplate main;

    public ButtonPanel(DoTemplate main, int cols) {
        super();
        this.main = main;

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    }
}
