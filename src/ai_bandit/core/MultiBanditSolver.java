package ai_bandit.core;

import java.util.Random;

public class MultiBanditSolver {
    private final MultiBandit bandits;
    private final Random random;
    private int[] counts;
    private double[] wins;
    private double epsilon;

    public MultiBanditSolver(MultiBandit bandits) {
        this.bandits = bandits;
        this.random = new Random();
        int n = bandits.getNumberBandits();
        this.counts = new int[n];
        this.wins = new double[n];
        this.epsilon = 0.0;
    }

    public void setGreedyEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    public int chooseGreedy() {
        if (random.nextDouble() <= epsilon) {
            return random.nextInt(bandits.getNumberBandits());
        } else {
            boolean allZero = true;
            for (int c : counts) if (c > 0) { allZero = false; break; }
            if (allZero) {
                return random.nextInt(bandits.getNumberBandits());
            }
            int best = 0;
            double maxAvg = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] > 0) {
                    double avg = wins[i] / counts[i];
                    if (avg > maxAvg) {
                        maxAvg = avg;
                        best = i;
                    }
                }
            }
            return best;
        }
    }

    public void addBanditResponse(int index, double profit) {
        counts[index]++;
        wins[index] += profit;
    }

    public int[] getCounts() {
        return counts.clone();
    }

    public void reset() {
        int n = bandits.getNumberBandits();
        this.counts = new int[n];
        this.wins = new double[n];
        this.epsilon = 0.0;
    }
}
