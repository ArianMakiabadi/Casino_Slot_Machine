package ai_bandit.core;

import java.util.Random;

public abstract class Gambling {
    protected Random random;
    private String name;
    private double pricePerRound = 1;
    private double overallProfit;
    private int roundsPlayed = 0;

    public Gambling(String name, double pricePerRound){
        this.random = new Random();
        this.overallProfit = 0.0;
        this.roundsPlayed = 0;
        this.name = name;
        this.pricePerRound = pricePerRound;
    }

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

    public double play() {
        double win = determineWin();
        overallProfit += win - pricePerRound;
        roundsPlayed++;
        return win;
    }

    protected abstract double determineWin();
}
