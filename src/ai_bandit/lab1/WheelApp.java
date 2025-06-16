package ai_bandit.lab1;

public class WheelApp {
    public static void main(String[] args) {
        // Setup game parameters
        double pricePerRound = 1.0;
        int[] categoryNumberFields = {15, 10, 4, 1};   // 30 fields total
        double[] categoryWins = {0.0, 1.0, 2.0, 5.0};   // matching categories

        // Create instance of WheelOfFortune
        WheelOfFortune wheel = new WheelOfFortune(pricePerRound, categoryNumberFields, categoryWins);

        System.out.println("Welcome to the Wheel of Fortune!");
        System.out.println("Number of Fields: " + wheel.getNumberFields());
        System.out.println("Price per round: €" + wheel.getPricePerRound());
        System.out.println();

        int rounds = 10;
        for (int i = 1; i <= rounds; i++) {
            double win = wheel.play(); // inherited from Gambling
            System.out.printf("Round %2d: You won €%.2f%n", i, win);
        }

        // Summary
        System.out.println("\nSummary:");
        System.out.printf("Rounds played: %d%n", wheel.getRoundsPlayed());
        System.out.printf("Total profit: €%.2f%n", wheel.getOverallProfit());
        System.out.printf("Mean profit per round: €%.2f%n", wheel.getMeanProfitPerRound());
    }
}
