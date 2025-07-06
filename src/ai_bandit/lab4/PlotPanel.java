package ai_bandit.lab4;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

/**
 * Panel that plots cumulative profit as an area/line chart
 * and displays a dynamic title showing current and abs max values.
 * The vertical scale adjusts each repaint so all data fits.
 */
public class PlotPanel extends JPanel {
    private List<Double> profits;
    private double absmax;  // max absolute value in current dataset

    public PlotPanel() {
        super();
        setBorder(BorderFactory.createTitledBorder("Available: 0.00 (abs max: 0.00)"));
    }

    /**
     * Update the profit history to plot and refresh the title.
     */
    public void updateWealth(List<Double> profits) {
        this.profits = profits;
        if (profits != null && !profits.isEmpty()) {
            double current = profits.get(profits.size() - 1);
            double minVal = profits.stream()
                    .mapToDouble(Double::doubleValue)
                    .min().orElse(0);
            double maxVal = profits.stream()
                    .mapToDouble(Double::doubleValue)
                    .max().orElse(0);
            absmax = Math.max(Math.abs(minVal), Math.abs(maxVal));

            TitledBorder tb = (TitledBorder) getBorder();
            tb.setTitle(String.format("Available: %.2f (abs max: %.2f)", current, absmax));
        }
        repaint();
    }

    /** Resets the plot and border title. */
    public void reset() {
        this.profits = null;
        this.absmax = 0;
        TitledBorder tb = (TitledBorder) getBorder();
        tb.setTitle("Available: 0.00 (abs max: 0.00)");
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (profits == null || profits.isEmpty() || absmax == 0) return;

        Graphics2D g2 = (Graphics2D) g.create();
        int w = getWidth();
        int h = getHeight();
        int margin = 20;

        // compute plotting area and zero line
        int plotHeight = h - 2 * margin;
        int zeroY = margin + plotHeight / 2;
        double scale = (plotHeight / 2.0) / absmax;

        // draw zero baseline
        g2.setColor(Color.BLACK);
        g2.drawLine(margin, zeroY, w - margin, zeroY);

        // draw filled areas and connecting line
        int n = profits.size();
        int prevX = margin;
        int prevY = zeroY - (int) (profits.get(0) * scale);

        for (int i = 1; i < n; i++) {
            int x = margin + (int) ((i / (double) (n - 1)) * (w - 2 * margin));
            double value = profits.get(i);
            int y = zeroY - (int) (value * scale);

            // filled polygon under segment
            Polygon poly = new Polygon(
                    new int[]{prevX, prevX, x, x},
                    new int[]{zeroY, prevY, y, zeroY},
                    4
            );
            g2.setColor(value >= 0
                    ? new Color(34, 139, 34)
                    : new Color(178, 34, 34));
            g2.fill(poly);

            // connecting line
            g2.setColor(Color.DARK_GRAY);
            g2.drawLine(prevX, prevY, x, y);

            prevX = x;
            prevY = y;
        }

        g2.dispose();
    }
}
