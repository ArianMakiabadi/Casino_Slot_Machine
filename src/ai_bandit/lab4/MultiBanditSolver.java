package ai_bandit.lab4;

import java.util.Random;

/**
 * ε-greedy solver using existing MultiBandit API.
 * Minimal changes: reset(), getCounts().
 */
public class MultiBanditSolver {
    private final MultiBandit bandits;
    private final Random random;
    private int[] counts;        // rounds played per bandit
    private double[] wins;       // total profit per bandit
    private double epsilon;

    public MultiBanditSolver(MultiBandit bandits) {
        this.bandits = bandits;
        this.random = new Random();
        int n = bandits.getNumberBandits();
        this.counts = new int[n];
        this.wins = new double[n];
        this.epsilon = 0.0;
    }

    /** Set exploration rate: ε=1.0 => pure random */
    public void setGreedyEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    /** Choose an index via ε-greedy */
    public int chooseGreedy() {
        if (random.nextDouble() <= epsilon) {
            return random.nextInt(bandits.getNumberBandits());
        } else {
            // exploit best average
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

    /** Record the profit from playing a bandit */
    public void addBanditResponse(int index, double profit) {
        counts[index]++;
        wins[index] += profit;
    }

    /** Return play-counts for plotting */
    public int[] getCounts() {
        return counts.clone();
    }

    /** Reset solver state (counts, wins, epsilon) */
    public void reset() {
        int n = bandits.getNumberBandits();
        this.counts = new int[n];
        this.wins = new double[n];
        this.epsilon = 0.0;
    }
}
