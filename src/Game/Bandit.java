package Game;

import java.util.Random;

public class Bandit {
    //private attributes
    private Random random;
    private String name;
    private double pricePerRound;
    private double averageWin;
    private double stdDevWin;
    private double overallProfit;
    private int roundsPlayed;

    // Constructor with name
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
        this.name = name; //overriding the name
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
}
