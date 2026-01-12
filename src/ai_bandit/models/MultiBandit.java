package ai_bandit.models;

import ai_bandit.models.Bandit;

public class MultiBandit {
    private Bandit[] bandits;
    public MultiBandit(int numberBandits) {
        bandits = new Bandit[numberBandits];
        int winner = (int)(Math.random() * (double)numberBandits);

        for (int i = 0; i < bandits.length; ++i) {
            double averageWin = (i == winner)
                    ? Math.random() * 0.2 + 1.1
                    : Math.random() * 0.3 + 0.5;

            bandits[i] = new Bandit(1.0, averageWin, 1.0);
        }

    }

    // Getters
    public int getNumberBandits() {
        return bandits.length;
    }

    public double getPricePerRound() {
        double totalPrice = 0.0D;

        for(int i = 0; i < this.getNumberBandits(); i++) {
            totalPrice += this.bandits[i].getPricePerRound();
        }

        return totalPrice / (double)this.getNumberBandits();
    }

    public double getOverallProfit() {
        double totalProfit = 0.0D;

        for(int i = 0; i < this.getNumberBandits(); ++i) {
            totalProfit += this.bandits[i].getOverallProfit();
        }

        return totalProfit;
    }

    public double getMeanProfitPerRound() {
        return this.getRoundsPlayed() == 0 ? 0.0D : this.getOverallProfit() / (double)this.getRoundsPlayed();
    }

    public int getRoundsPlayed() {
        int totalRoundsPlayed = 0;

        for(int i = 0; i < this.getNumberBandits(); ++i) {
            totalRoundsPlayed += this.bandits[i].getRoundsPlayed();
        }

        return totalRoundsPlayed;
    }

    public double play(int banditIndex) {
        return this.bandits[banditIndex].play();
    }
}
