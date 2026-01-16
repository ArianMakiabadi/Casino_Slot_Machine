package ai_bandit.models;

import ai_bandit.core.Gambling;

public class Bandit extends Gambling {
    private double averageWin;   // µ
    private double stdDevWin;    // σ

    // Constructor with name
    public Bandit(String name, double pricePerRound, double averageWin, double stdDevWin) {
        super(name, pricePerRound);
        this.averageWin = averageWin;
        this.stdDevWin = stdDevWin;
    }

    // Constructor without name
    public Bandit(double pricePerRound, double averageWin, double stdDevWin) {
        this("Bandit", pricePerRound, averageWin, stdDevWin); // delegate
    }

    // Gaussian payout override
    @Override
    protected double determineWin() {
        double win = averageWin + stdDevWin * random.nextGaussian();
        win = Math.max(0, win);
        return Math.round(win * 10.0) / 10.0;
    }
}
