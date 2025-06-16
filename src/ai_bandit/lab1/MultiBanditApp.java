package ai_bandit.lab1; // Assuming new package based on lab instructions

import java.util.Scanner;

public class MultiBanditApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberBandits = 7;
        MultiBandit bandits = new MultiBandit(numberBandits);
        MultiBanditSolver solver = new MultiBanditSolver(bandits); // Initialize the solver

        System.out.printf("%-8s: Multi-armed bandit (%d bandits)\n", "Gambling", Integer.valueOf(numberBandits));
        System.out.printf("%-8s: 1,00 EUR \n", "Price");

        System.out.print("\nHow many rounds would you like to play? ");
        int rounds = scanner.nextInt();

        System.out.print("Enter epsilon in [0,100] percent (typical value: 15) or any other number for random strategy: ");
        double epsilonInput = scanner.nextDouble();
        String strategyApplied;

        if (epsilonInput >= 0 && epsilonInput <= 100) {
            // Epsilon-greedy strategy
            solver.setGreedyEpsilon(epsilonInput / 100.0); // Convert percentage to [0, 1]
            strategyApplied = String.format("epsilon-greedy (epsilon = %.2f)", epsilonInput / 100.0);
        } else {
            // Random strategy
            // The solver's default epsilon (0.0) or setting a high value would effectively make it random
            // For explicit random choice, we can set epsilon to 1.0 or simply use chooseRandom()
            // As per problem description, any other number for random strategy.
            solver.setGreedyEpsilon(1.0); // Set epsilon to 1.0 for purely random choice if input is not in [0,100]
            strategyApplied = "random";
        }


        System.out.printf("%-8s | %-8s | %-8s | %-8s \n", "Round", "Bandit", "Win [EUR]", "Net [EUR]");
        System.out.println("---------+-----------+-----------+----------");

        for (int i = 0; i < rounds; ++i) {
            int whichBandit;
            if (strategyApplied.startsWith("epsilon-greedy")) {
                whichBandit = solver.chooseGreedy();
            } else {
                whichBandit = solver.chooseRandom();
            }

            double winAmount = bandits.play(whichBandit); // This is the amount returned by the bandit
            double profitThisRound = winAmount - bandits.getPricePerRound(); // Calculate profit for this round

            // Add response to solver's statistics
            solver.addBanditResponse(whichBandit, profitThisRound);

            // The overall profit from the MultiBandit class still calculates total profit across all played rounds by any bandit
            // If you want the net profit displayed to be solely based on the current run's profit, use profitThisRound
            // The example output shows the 'Net [EUR]' accumulating the total profit/loss, so we need to track it.
            // bandits.getOverallProfit() accumulates all plays, which is suitable for the 'Net [EUR]' column.

            System.out.printf("%8d | %8d | %8.2f | %8.2f\n", i + 1, whichBandit, winAmount, bandits.getOverallProfit());
        }

        System.out.printf("\nApplied strategy: %s\n\n", strategyApplied);
        System.out.printf("Multi-armed bandit's statistics:\n");
        System.out.printf("Rounds: %d\n", rounds);
        System.out.printf("Profit: %.2f (%.2f EUR/round)\n\n\n", bandits.getOverallProfit(), bandits.getMeanProfitPerRound());

        scanner.close(); // Close the scanner
    }
}