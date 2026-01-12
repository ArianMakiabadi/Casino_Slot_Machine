package ai_bandit.core;

public class WheelOfFortune extends Gambling {
    private int numberFields;
    private double[] categoryWins;
    private double[] categoryChances;

    public WheelOfFortune(double pricePerRound, int[] categoryNumberFields, double[] categoryWins) {
        super("wheelOfFortune", pricePerRound);
        this.categoryWins = categoryWins;

        numberFields = 0;
        for (int count : categoryNumberFields) {
            numberFields += count;
        }

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
        double r = random.nextDouble();
        double cumulative = 0.0;
        for (int i = 0; i < categoryChances.length; i++) {
            cumulative += categoryChances[i];
            if (r <= cumulative) {
                return categoryWins[i];
            }
        }
        return categoryWins[categoryWins.length - 1];
    }
}
