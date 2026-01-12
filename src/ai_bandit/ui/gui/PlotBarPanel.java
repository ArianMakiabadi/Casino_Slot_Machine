package ai_bandit.ui.gui;

import javax.swing.*;
import java.awt.*;

public class PlotBarPanel extends JPanel {
    private int[] counts;

    public void update(int[] counts) {
        this.counts = counts.clone();
        repaint();
    }

    public void reset() {
        this.counts = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (counts == null) return;
        Graphics2D g2 = (Graphics2D) g;
        int n = counts.length, w = getWidth(), h = getHeight();
        int max = 1;
        for (int c : counts) max = Math.max(max, c);
        int barW = w / n;
        for (int i = 0; i < n; i++) {
            int barH = (int) (counts[i] / (double) max * (h - 20));
            int x = i * barW + 5;
            int y = h - barH - 5;
            g2.fillRect(x, y, barW - 10, barH);
            g2.drawString(String.valueOf(counts[i]), x, y - 5);
        }
    }
}
