package ai_bandit.lab1;

import java.util.Scanner;

public class MultiBanditApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberBandits = 7;
        MultiBandit bandits = new MultiBandit(numberBandits);
        System.out.printf("%-8s: Multi-armed bandit (%d bandits)\n", "Gambling", Integer.valueOf(numberBandits));
        System.out.printf("%-8s: 1,00 EUR \n", "Price");
        System.out.print("\nHow many rounds would you like to play ? ");
        int rounds = scanner.nextInt();
        System.out.printf("%-8s | %-8s | %-8s | %-8s \n", "Round", "Bandit", "Win [EUR]", "Net [EUR]");
        System.out.println("---------+-----------+-----------+----------");

        for(int i = 0; i < rounds; ++i) {
            int whichBandit = (int)(Math.random() * (double)numberBandits);
            System.out.printf("%8d | %8d | %8.2f | %8.2f\n", i + 1, whichBandit, bandits.play(whichBandit), -bandits.getOverallProfit());
        }

        System.out.printf("\nMulti-armed bandit's statistics:\n");
        System.out.printf("Rounds: %d\n", rounds);
        System.out.printf("Profit: %.2f (%.2f EUR/round)\n\n\n", bandits.getOverallProfit(), bandits.getMeanProfitPerRound());
    }
}
