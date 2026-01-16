package ai_bandit.ui.gui;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    final JRadioButton randomBtn = new JRadioButton("Random bandit");
    final JRadioButton greedyBtn = new JRadioButton("Epsilon-greedy");
    final JButton reset = new JButton("Reset bandits");
    final JButton play1 = new JButton("Play 1x");
    final JButton play10 = new JButton("Play 10x");
    final JButton play100 = new JButton("Play 100x");
    final JButton auto = new JButton("Start");

    public ControlPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(new JLabel("Selection strategy:"));
        ButtonGroup group = new ButtonGroup();
        group.add(randomBtn);
        group.add(greedyBtn);
        randomBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        greedyBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        randomBtn.setSelected(true);
        add(randomBtn);
        add(Box.createVerticalStrut(10));
        add(greedyBtn);
        add(Box.createVerticalStrut(20));
        for (JButton btn : new JButton[]{reset, play1, play10, play100, auto}) {
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            btn.setMaximumSize(new Dimension(120, btn.getPreferredSize().height));
            add(btn);
            add(Box.createVerticalStrut(10));
        }
    }
}
