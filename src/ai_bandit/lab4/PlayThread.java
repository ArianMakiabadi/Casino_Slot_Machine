package ai_bandit.lab4;

public class PlayThread extends Thread {
    private final Model model;
    private final GraphicalUI ui;
    private volatile boolean running = true;
    private final double epsilon;

    public PlayThread(Model model, GraphicalUI ui, double epsilon) {
        this.model = model;
        this.ui = ui;
        this.epsilon = epsilon;
    }

    public void terminate() {
        running = false;
        interrupt();
    }

    @Override
    public void run() {
        while (running) {
            model.play(epsilon);
            ui.updatePlots(model.getPlayCounts(), model.getProfitHistory());
            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
        }
    }
}