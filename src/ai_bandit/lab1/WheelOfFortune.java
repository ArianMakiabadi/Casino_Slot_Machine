package ai_bandit.lab1;

public class WheelOfFortune extends Gambling {
    private int numberFields;
    private double[] categoryWins;
    private double[] categoryChances;

    public WheelOfFortune(double pricePerRound, int[] categoryNumberFields, double[] categoryWins) {
        super("wheelOfFortune", pricePerRound);
        this.categoryWins = categoryWins;

        // 1. Calculate total number of fields
        numberFields = 0;
        for (int count : categoryNumberFields) {
            numberFields += count;
        }

        // 2. Calculate probabilities for each category
        categoryChances = new double[categoryNumberFields.length];
        for (int i = 0; i < categoryNumberFields.length; i++) {
            categoryChances[i] = (double) categoryNumberFields[i] / numberFields;
        }
    }

    public int getNumberFields() {
        return numberFields;
    }

    @Override
    protected double determineWin() {
        double r = random.nextDouble(); // inherited protected Random from Gambling
        double cumulative = 0.0;
        for (int i = 0; i < categoryChances.length; i++) {
            cumulative += categoryChances[i];
            if (r <= cumulative) {
                return categoryWins[i];
            }
        }
        // Fallback: return last prize
        return categoryWins[categoryWins.length - 1];
    }
}
