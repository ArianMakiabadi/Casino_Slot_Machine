package ai_bandit.lab1;

public class MultiBandit {
    // Attributes
    private int numberBandits;
    private Bandit[] bandits;

    // Constructor
    public MultiBandit(int numberBandits) {
        this.numberBandits = numberBandits;
        this.bandits = new Bandit[numberBandits];
        int winner = (int)(Math.random() * (double)numberBandits);

        for(int i = 0; i < numberBandits; ++i) {
            double averageWin;
            if (winner == i) {
                averageWin = Math.random() * 0.2D + 1.1D;
            } else {
                averageWin = Math.random() * 0.3D + 0.5D;
            }

            this.bandits[i] = new Bandit(1.0D, averageWin, 1.0D);
        }

    }

    // Getters
    public int getNumberBandits() {
        return this.numberBandits;
    }

    public double getPricePerRound() {
        double totalPrice = 0.0D;

        for(int i = 0; i < this.numberBandits; ++i) {
            totalPrice += this.bandits[i].getPricePerRound();
        }

        return totalPrice / (double)this.numberBandits;
    }

    public double getOverallProfit() {
        double totalProfit = 0.0D;

        for(int i = 0; i < this.numberBandits; ++i) {
            totalProfit += this.bandits[i].getOverallProfit();
        }

        return totalProfit;
    }

    public double getMeanProfitPerRound() {
        return this.getRoundsPlayed() == 0 ? 0.0D : this.getOverallProfit() / (double)this.getRoundsPlayed();
    }

    public int getRoundsPlayed() {
        int totalRoundsPlayed = 0;

        for(int i = 0; i < this.numberBandits; ++i) {
            totalRoundsPlayed += this.bandits[i].getRoundsPlayed();
        }

        return totalRoundsPlayed;
    }

    public double play(int banditIndex) {
        return this.bandits[banditIndex].play();
    }
}
