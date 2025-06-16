package ai_bandit.lab1;

import java.util.Random;

public class MultiBanditSolver {
    private Random random;
    private MultiBandit bandits;
    private int[] counts; // Stores how many rounds played on each bandit
    private double[] wins; // Stores the total profit (returned amount - cost per round) for each bandit
    private double epsilon;

    public MultiBanditSolver(MultiBandit bandits) {
        this.bandits = bandits;
        this.random = new Random();
        int numberOfBandits = bandits.getNumberBandits();
        this.counts = new int[numberOfBandits];
        this.wins = new double[numberOfBandits];
        // Initialize counts and wins to 0
        for (int i = 0; i < numberOfBandits; i++) {
            this.counts[i] = 0;
            this.wins[i] = 0.0;
        }
        this.epsilon = 0.0; // Default epsilon
    }

    public void setGreedyEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    public void addBanditResponse(int banditIndex, double win) {
        if (banditIndex >= 0 && banditIndex < bandits.getNumberBandits()) {
            this.counts[banditIndex]++;
            this.wins[banditIndex] += win; // 'win' here is already profit (returned amount - cost)
        }
    }

    public double getAverageWin(int banditIndex) {
        if (banditIndex < 0 || banditIndex >= bandits.getNumberBandits() || counts[banditIndex] == 0) {
            return 0.0; // Avoid division by zero
        }
        return wins[banditIndex] / counts[banditIndex];
    }

    public int chooseRandom() {
        return random.nextInt(bandits.getNumberBandits());
    }

    public int chooseGreedy() {
        if (random.nextDouble() <= epsilon) {
            // Explore: Choose a random bandit
            return chooseRandom();
        } else {
            // Exploit: Choose the bandit with the highest average win
            int bestBanditIndex = 0;
            double maxAverageWin = -Double.MAX_VALUE; // Initialize with a very small number

            // Handle cases where no bandit has been played yet or all have 0 average win
            boolean allCountsZero = true;
            for(int count : counts) {
                if (count > 0) {
                    allCountsZero = false;
                    break;
                }
            }

            if (allCountsZero) {
                // If no bandit has been played, choose randomly to get initial stats
                return chooseRandom();
            }

            for (int i = 0; i < bandits.getNumberBandits(); i++) {
                // Only consider bandits that have been played at least once
                if (counts[i] > 0) {
                    double currentAverageWin = getAverageWin(i);
                    if (currentAverageWin > maxAverageWin) {
                        maxAverageWin = currentAverageWin;
                        bestBanditIndex = i;
                    }
                }
            }
            return bestBanditIndex;
        }
    }
}