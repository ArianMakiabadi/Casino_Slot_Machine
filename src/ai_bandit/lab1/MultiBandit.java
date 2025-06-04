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
    public int getNumberBandits(){

    }

    public double getPricePerRound(){

    }
}
