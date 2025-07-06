package ai_bandit.lab4;

import java.util.Random;

/**
 * Single bandit with Gaussian payouts and resettable state.
 */
public class Bandit {
    private String name;
    private double pricePerRound;
    private double averageWin;
    private double stdDevWin;
    private Random random;
    private int roundsPlayed;
    private double overallProfit;

    /**
     * Constructor with name.
     */
    public Bandit(String name, double pricePerRound, double averageWin, double stdDevWin) {
        this.name = name;
        this.pricePerRound = pricePerRound;
        this.averageWin = averageWin;
        this.stdDevWin = stdDevWin;
        this.random = new Random();
        this.roundsPlayed = 0;
        this.overallProfit = 0.0;
    }

    /**
     * Constructor without name.
     */
    public Bandit(double pricePerRound, double averageWin, double stdDevWin) {
        this("Bandit", pricePerRound, averageWin, stdDevWin);
    }

    /**
     * Plays one round: generates a Gaussian payout, computes profit, and updates stats.
     * @return profit (win - cost)
     */
    public double play() {
        double win = averageWin + stdDevWin * random.nextGaussian();
        win = Math.max(0, win);
        win = Math.round(win * 10.0) / 10.0;
        double profit = win - pricePerRound;
        roundsPlayed++;
        overallProfit += profit;
        return profit;
    }

    /**
     * Resets the bandit’s state (counters and profit).
     */
    public void reset() {
        this.roundsPlayed = 0;
        this.overallProfit = 0.0;
        this.random = new Random();
    }

    /** Returns number of rounds played. */
    public int getRoundsPlayed() {
        return roundsPlayed;
    }

    /** Returns total accumulated profit. */
    public double getOverallProfit() {
        return overallProfit;
    }

    /** Returns name of this bandit. */
    public String getName() {
        return name;
    }

    /** Returns price paid per round. */
    public double getPricePerRound() {
        return pricePerRound;
    }

    /** Returns mean profit per round. */
    public double getMeanProfitPerRound() {
        if (roundsPlayed == 0) return 0.0;
        return overallProfit / roundsPlayed;
    }
}