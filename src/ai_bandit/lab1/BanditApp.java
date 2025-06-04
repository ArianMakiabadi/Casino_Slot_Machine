package ai_bandit.lab1;

import java.util.Scanner;

public class BanditApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Create Bandit object
        Bandit bandit = new Bandit("One-armed bandit", 1.0, 0.9, 0.4);

        // Step 2: Output machine info
        System.out.println("Gambling : " + bandit.getName());
        System.out.printf("Price    : %.2f EUR%n", bandit.getPricePerRound());

        // Step 3: Ask user for number of rounds
        System.out.print("How many rounds would you like to play? ");
        int rounds = scanner.nextInt();

        // Step 4: Header for table
        System.out.println("\nRound | Win [EUR] | Net [EUR]");
        System.out.println("======+===========+==========");

        double netLoss = 0.0;

        // Step 5: Play each round
        for (int i = 1; i <= rounds; i++) {
            double win = bandit.play();
            double lossThisRound = win - bandit.getPricePerRound();
            netLoss += lossThisRound;

            System.out.printf("%2d    | %7.2f   | %7.2f%n", i, win, netLoss);
        }

        // Step 6: Final stats
        System.out.println("\n" + bandit.getName() + "'s statistics:");
        System.out.printf("Rounds : %d%n", bandit.getRoundsPlayed());
        System.out.printf("Profit : %.2f (%.2f EUR/round)%n",
                bandit.getOverallProfit(),
                bandit.getMeanProfitPerRound());
    }
}
