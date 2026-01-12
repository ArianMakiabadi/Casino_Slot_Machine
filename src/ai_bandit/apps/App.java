package ai_bandit.apps;

import ai_bandit.ui.gui.GraphicalUI;

public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new GraphicalUI().setVisible(true));
    }
}