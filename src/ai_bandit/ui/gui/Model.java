package ai_bandit.ui.gui;

import java.util.ArrayList;
import java.util.List;
import ai_bandit.core.MultiBandit;
import ai_bandit.core.MultiBanditSolver;

public class Model {
    private final MultiBandit bandit;
    private final MultiBanditSolver solver;
    private final List<Double> houseProfitHistory;
    private double totalHouseProfit;

    public Model(int numberOfBandits) {
        this.bandit = new MultiBandit(numberOfBandits);
        this.solver = new MultiBanditSolver(bandit);
        this.houseProfitHistory = new ArrayList<>();
        this.totalHouseProfit = 0.0;
        houseProfitHistory.add(totalHouseProfit);
    }

    public void play(double epsilon) {
        solver.setGreedyEpsilon(epsilon);
        int choice = solver.chooseGreedy();
        double houseProfit = bandit.play(choice);
        solver.addBanditResponse(choice, -houseProfit);
        totalHouseProfit += houseProfit;
        houseProfitHistory.add(totalHouseProfit);
    }

    public void reset() {
        bandit.reset();
        solver.reset();
        houseProfitHistory.clear();
        totalHouseProfit = 0.0;
        houseProfitHistory.add(totalHouseProfit);
    }

    public int[] getPlayCounts() {
        return solver.getCounts();
    }

    public List<Double> getProfitHistory() {
        List<Double> user = new ArrayList<>(houseProfitHistory.size());
        for (double hp : houseProfitHistory) user.add(-hp);
        return List.copyOf(user);
    }

}