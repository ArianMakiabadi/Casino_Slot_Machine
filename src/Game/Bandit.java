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
    public Bandit(String name, double pricePerRound, double averageWin, double stdDevWin) {
        this.name = name;
        this.pricePerRound = pricePerRound;
        this.averageWin = averageWin;
        this.stdDevWin = stdDevWin;
        this.random = new Random();
        this.overallProfit = 0.0;
        this.roundsPlayed = 0;
    }

    // Constructor without name
    public Bandit(double pricePerRound, double averageWin, double stdDevWin) {
        this("One-Armed Bandit", pricePerRound, averageWin, stdDevWin);
    }
}
