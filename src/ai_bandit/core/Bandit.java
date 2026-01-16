package ai_bandit.core;

import java.util.Random;

public class Bandit {
    private String name;
    private double pricePerRound;
    private double averageWin;
    private double stdDevWin;
    private Random random;
    private int roundsPlayed;
    private double overallProfit;

    public Bandit(String name, double pricePerRound, double averageWin, double stdDevWin) {
        this.name = name;
        this.pricePerRound = pricePerRound;
        this.averageWin = averageWin;
        this.stdDevWin = stdDevWin;
        this.random = new Random();
        this.roundsPlayed = 0;
        this.overallProfit = 0.0;
    }

    public Bandit(double pricePerRound, double averageWin, double stdDevWin) {
        this("Bandit", pricePerRound, averageWin, stdDevWin);
    }

    public double play() {
        double win = averageWin + stdDevWin * random.nextGaussian();
        win = Math.max(0, win);
        win = Math.round(win * 10.0) / 10.0;
        double profit = pricePerRound - win;
        roundsPlayed++;
        overallProfit += profit;
        return win;
    }


    public void reset() {
        this.roundsPlayed = 0;
        this.overallProfit = 0.0;
        this.random = new Random();
    }

    public int getRoundsPlayed() {
        return roundsPlayed;
    }

    public double getOverallProfit() {
        return overallProfit;
    }

    public String getName() {
        return name;
    }

    public double getPricePerRound() {
        return pricePerRound;
    }

    public double getMeanProfitPerRound() {
        if (roundsPlayed == 0) return 0.0;
        return overallProfit / roundsPlayed;
    }
}