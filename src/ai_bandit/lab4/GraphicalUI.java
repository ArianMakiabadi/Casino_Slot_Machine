package ai_bandit.lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class GraphicalUI extends JFrame {
    private final ControlPanel controls = new ControlPanel();
    private final PlotBarPanel barPanel = new PlotBarPanel();
    private final PlotPanel linePanel = new PlotPanel();
    private final Model model;
    private PlayThread thread;
    private final double greedyEpsilon = 0.3;

    public GraphicalUI() {
        super("Multi-Bandit GUI Lab");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        model = new Model(7);
        initLayout();
        initListeners();
        updatePlots(model.getPlayCounts(), model.getProfitHistory());
    }

    private void initLayout() {
        setLayout(new BorderLayout());
        // Create charts panel (left)
        barPanel.setBorder(BorderFactory.createTitledBorder("Bandit selection count"));
        // The title of the second graph is set dynamically in PlotPanel.java
        JPanel plots = new JPanel(new GridLayout(2, 1));
        plots.add(barPanel);
        plots.add(linePanel);

        // Wrap charts and controls in a split pane to allocate approx 4/5 to charts
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, plots, controls);
        split.setResizeWeight(0.8);
        split.setOneTouchExpandable(true);
        add(split, BorderLayout.CENTER);
    }

    private void initListeners() {
        controls.play1.addActionListener(e -> playRounds(1));
        controls.play10.addActionListener(e -> playRounds(10));
        controls.play100.addActionListener(e -> playRounds(100));
        controls.reset.addActionListener(e -> reset());
        controls.auto.addActionListener(this::toggleAuto);
    }

    private void playRounds(int n) {
        // Determine epsilon based on selected strategy
        // If Random is selected, we choose epsilon as 1 else the default value
        // which is 0.3
        double epsilon = controls.randomBtn.isSelected() ? 1.0 : greedyEpsilon;
        for (int i = 0; i < n; i++) {
            model.play(epsilon);
        }
        updatePlots(model.getPlayCounts(), model.getProfitHistory());
    }

    private void reset() {
        if (thread != null && thread.isAlive()) thread.terminate();
        model.reset();
        barPanel.reset();
        linePanel.reset();
    }

    private void toggleAuto(ActionEvent e) {
        if (thread == null || !thread.isAlive()) {
            double epsilon = controls.randomBtn.isSelected() ? 1.0 : greedyEpsilon;
            thread = new PlayThread(model, this, epsilon);
            thread.start();
            controls.auto.setText("Stop");
        } else {
            thread.terminate();
            controls.auto.setText("Start");
        }
    }

    public void updatePlots(int[] counts, List<Double> profits) {
        SwingUtilities.invokeLater(() -> {
            barPanel.update(counts);
            linePanel.updateWealth(profits);
        });
    }
}