package ai_bandit.lab4;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the MultiBandit and solver state,
 * and records cumulative profit history.
 */
public class Model {
    private final MultiBandit bandit;
    private final MultiBanditSolver solver;
    private final List<Double> profitHistory;
    private double totalProfit;

    public Model(int numberOfBandits) {
        this.bandit = new MultiBandit(numberOfBandits);
        this.solver = new MultiBanditSolver(bandit);
        this.profitHistory = new ArrayList<>();
        this.totalProfit = 0.0;
        profitHistory.add(totalProfit);
    }

    /** Plays one round given epsilon: 1 for random, <1 for ε-greedy */
    public void play(double epsilon) {
        solver.setGreedyEpsilon(epsilon);
        int choice = solver.chooseGreedy();
        double profit = bandit.play(choice);
        solver.addBanditResponse(choice, profit);
        totalProfit += profit;
        profitHistory.add(totalProfit);
    }

    /** Resets solver and bandit to initial state. */
    public void reset() {
        bandit.reset();
        solver.reset();
        profitHistory.clear();
        totalProfit = 0.0;
        profitHistory.add(totalProfit);
    }

    /** Returns number of wins per bandit (play counts). */
    public int[] getPlayCounts() {
        return solver.getCounts();
    }

    /** Returns cumulative profit history for plotting. */
    public List<Double> getProfitHistory() {
        return List.copyOf(profitHistory);
    }
}