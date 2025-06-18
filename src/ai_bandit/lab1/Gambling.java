package ai_bandit.lab1;

import java.util.Random;

public abstract class Gambling {
    protected Random random;
    private String name;                        // machine name
    private double pricePerRound = 1;
    private double overallProfit;
    private int roundsPlayed = 0;              // count of rounds played

    public Gambling(String name, double pricePerRound){
        this.random = new Random();
        this.overallProfit = 0.0;
        this.roundsPlayed = 0;
        this.name = name;
        this.pricePerRound = pricePerRound;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPricePerRound() {
        return pricePerRound;
    }

    public double getOverallProfit() {
        return overallProfit;
    }

    public int getRoundsPlayed() {
        return roundsPlayed;
    }

    public double getMeanProfitPerRound(){
        if (roundsPlayed==0) return 0.0;
        else return overallProfit / roundsPlayed;
    }

    // Play method
    public double play() {
        double win = determineWin();
        overallProfit += win - pricePerRound;
        roundsPlayed++;
        return win;
    }

    // Gaussian payout
    protected abstract double determineWin();
}
