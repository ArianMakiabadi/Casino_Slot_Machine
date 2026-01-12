package ai_bandit.apps;

import java.util.Scanner;
import ai_bandit.core.WheelOfFortune;

public class WheelApp {
    public static void main(String[] args) {
        double pricePerRound = 1.0;
        int[] categoryNumberFields = {15, 10, 4, 1};   // total = 30
        double[] categoryWins = {0.0, 1.0, 2.0, 5.0};   // win values

        WheelOfFortune wheel = new WheelOfFortune(pricePerRound, categoryNumberFields, categoryWins);

        Scanner scanner = new Scanner(System.in);

        // Header
        System.out.println("Gambling: Wheel of Fortune");
        System.out.printf("Price: %.2f EUR%n", pricePerRound);
        System.out.printf("Fields: %d%n", wheel.getNumberFields());

        // Ask for number of rounds
        System.out.print("How many rounds would you like to play? ");
        int rounds = scanner.nextInt();

        // Header of result table
        System.out.println("Round | Win [EUR] | Net [EUR]");
        System.out.println("======+===========+==========");

        double totalProfit = 0;

        for (int i = 1; i <= rounds; i++) {
            double win = wheel.play();
            double profit = win - pricePerRound;
            totalProfit += profit;

            // Format with comma as decimal separator (Euro-style)
            String winStr = String.format("%.2f", win).replace('.', ',');
            String profitStr = String.format("%.2f", totalProfit).replace('.', ',');

            System.out.printf("%-6d| %-10s| %-9s%n", i, winStr, profitStr);
        }

        // Summary block
        String meanProfitStr = String.format("%.2f", totalProfit / rounds).replace('.', ',');
        String totalProfitStr = String.format("%.2f", totalProfit).replace('.', ',');

        System.out.println("Wheel of Fortune Statistics:");
        System.out.printf("Rounds: %d%n", rounds);
        System.out.printf("Profit: %s (%s EUR/round)%n", totalProfitStr, meanProfitStr);
    }
}
