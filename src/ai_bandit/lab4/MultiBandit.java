package ai_bandit.lab4;

/**
 * Multi-bandit environment using Bandit class.
 */
public class MultiBandit {
    private final Bandit[] bandits;

    /**
     * Construct N bandits, one with higher mean.
     */
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

    /** Number of bandits */
    public int getNumberBandits() {
        return bandits.length;
    }

    /** Play bandit at index, returns profit */
    public double play(int index) {
        return bandits[index].play();
    }

    /** Reset all bandits to initial state */
    public void reset() {
        for (Bandit b : bandits) b.reset();
    }
}
