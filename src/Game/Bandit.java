package Game;

import java.util.Random;

public class Bandit {
    //private attributes
    private Random random;
    private String name;                   // machine name
    private double pricePerRound;          // cost to play one round
    private double averageWin;
    private double stdDevWin;
    private double overallProfit;
    private int roundsPlayed;              // count of rounds played

    // Constructor without name
    public Bandit(double pricePerRound, double averageWin, double stdDevWin) {
        this.pricePerRound = pricePerRound;
        this.averageWin = averageWin;
        this.stdDevWin = stdDevWin;
        this.random = new Random();
        this.overallProfit = 0.0;
        this.roundsPlayed = 0;
        this.name = "One-Armed Bandit";  // Default name
    }

    // Constructor with name
    public Bandit(String name, double pricePerRound, double averageWin, double stdDevWin) {
        this(pricePerRound, averageWin, stdDevWin); // Call the default constructor
        this.name = name; //overriding the default name
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

    // Gaussian payout
    private double determineWin() {
        double win = averageWin + stdDevWin * random.nextGaussian();
        win = Math.max(0, win);
        return Math.round(win * 10.0) / 10.0;
    }
}
