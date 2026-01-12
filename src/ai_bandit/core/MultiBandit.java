package ai_bandit.core;

public class MultiBandit {
    private final Bandit[] bandits;

    public MultiBandit(int numberBandits) {
        bandits = new Bandit[numberBandits];
        int winner = (int) (Math.random() * numberBandits);
        for (int i = 0; i < numberBandits; i++) {
            double avg = (i == winner)
                    ? Math.random() * 0.2 + 1.1
                    : Math.random() * 0.3 + 0.5;
            bandits[i] = new Bandit(1.0, avg, 1.0);
        }
    }

    public int getNumberBandits() {
        return bandits.length;
    }

    public double play(int index) {
        double win = bandits[index].play();
        double price = bandits[index].getPricePerRound();
        return price - win;
    }

    public void reset() {
        for (Bandit b : bandits) b.reset();
    }
}
