package studentGUI;

import javax.swing.JPanel;
import java.util.HashMap;

public class PanelManager {
    private final HashMap<String, JPanel> paneles;

    public PanelManager() {
        paneles = new HashMap<>();
    }

    public void addPanel(String nombre, JPanel panel) {
        paneles.put(nombre, panel);
    }

    public JPanel getPanel(String nombre) {
        return paneles.get(nombre);
    }
}
