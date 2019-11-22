package UI;

import AStar.AStarSolver;
import AStar.HeuristicFunctions;
import BFS.BFSSolver;
import DFS.DFSSolver;
import Utils.Node;
import Utils.PuzzleChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

public class HomePage implements Initializable {
    @FXML private GridPane puzzle;
    @FXML private TextField inputPuzzle;
    @FXML private Button step;
    @FXML private Button stats;

    private PuzzleChecker puzzleChecker;
    private Node result;

    public int maxDepth;
    public int solutionDepth;
    public int cost;
    public int nodesExpanded;
    public long runningTime;
    public Stack<Node> path;
    List<Label> labels;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labels = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Label label;
                if ((j * 3) + i == 0) {
                    label = new Label();
                } else {
                    label = new Label(String.valueOf((i * 3) + j));
                }
                label.setId(String.valueOf((j * 3) + i));
                label.setStyle("-fx-border-color: Black; -fx-font-size: 30");
                label.setPrefHeight(70);
                label.setPrefWidth(70);
                label.setAlignment(Pos.CENTER);
                labels.add(label);
                puzzle.add(label, j,i);
            }
        }
        puzzleChecker = new PuzzleChecker();
        step.setDisable(true);
        stats.setDisable(true);
    }

    @FXML
    private void bfsSolver (ActionEvent event) throws Exception{
        if (!puzzleChecker.isSolvable(getPuzzle())) {
            System.out.println("Not solvable");
            step.setDisable(true);
            stats.setDisable(true);
            return;
        }
        BFSSolver bfsSolver = new BFSSolver(getPuzzle());
        bfsSolver.solve();
        result = bfsSolver.getResult();
        maxDepth = bfsSolver.getMaxDepth();
        System.out.println(maxDepth);
        solutionDepth = result.getDepth();
        System.out.println(solutionDepth);
        cost = result.getCost();
        System.out.println(cost);
        nodesExpanded = bfsSolver.getNodesExpanded();
        System.out.println(nodesExpanded);
        runningTime = bfsSolver.getRunningTime();
        System.out.println(runningTime);

        path = new Stack<>();
        while(result != null) {
            path.push(result);
            result = result.getParent();
        }
        //remove the first puzzle that is already the input
        path.pop();
        step.setDisable(false);
        stats.setDisable(false);
    }
    @FXML
    private void dfsSolver (ActionEvent event) throws Exception{
        if (!puzzleChecker.isSolvable(getPuzzle())) {
            System.out.println("Not solvable");
            step.setDisable(true);
            stats.setDisable(true);
            return;
        }
        DFSSolver dfsSolver = new DFSSolver(getPuzzle());
        dfsSolver.solve();
        result = dfsSolver.getResult();
        maxDepth = dfsSolver.getMaxDepth();
        solutionDepth = result.getDepth();
        cost = result.getCost();
//        TODO nodesExpanded = bfsSolver.getNodesExpanded();
//        TODO runningTime = bfsSolver.getRunningTime();

        path = new Stack<>();
        while(result != null) {
            path.push(result);
            result = result.getParent();
        }
        //remove the first puzzle that is already the input
        path.pop();
        step.setDisable(false);
        stats.setDisable(false);
    }
    @FXML
    private void astarSolverEuc (ActionEvent event) throws Exception{
        if (!puzzleChecker.isSolvable(getPuzzle())) {
            System.out.println("Not solvable");
            step.setDisable(true);
            stats.setDisable(true);
            return;
        }
        AStarSolver solver = new AStarSolver(getPuzzle());
        solver.solve(HeuristicFunctions::heuristicEuclidean);
        result = solver.getResult();
        maxDepth = solver.getMaxDepth();
        cost = solver.getResult().getCost();
        solutionDepth = solver.getResult().getDepth();
        nodesExpanded = solver.getNodesExpanded();
        //TODO add time

        path = new Stack<>();
        while(result != null) {
            path.push(result);
            result = result.getParent();
        }
        //remove the first puzzle that is already the input
        path.pop();
        step.setDisable(false);
        stats.setDisable(false);
    }

    @FXML
    private void astarSolverManh (ActionEvent event) throws Exception{
        if (!puzzleChecker.isSolvable(getPuzzle())) {
            System.out.println("Not solvable");
            step.setDisable(true);
            stats.setDisable(true);
            return;
        }
        AStarSolver solver = new AStarSolver(getPuzzle());
        solver.solve(HeuristicFunctions::heuristicManhattan);
        result = solver.getResult();
        maxDepth = solver.getMaxDepth();
        cost = solver.getResult().getCost();
        solutionDepth = solver.getResult().getDepth();
        nodesExpanded = solver.getNodesExpanded();
        //TODO add time

        path = new Stack<>();
        while(result != null) {
            path.push(result);
            result = result.getParent();
        }
        //remove the first puzzle that is already the input
        path.pop();
        step.setDisable(false);
        stats.setDisable(false);
    }

    @FXML
    private void slowMotion (ActionEvent event) throws Exception{
        if (!path.empty()) {
            puzzle.getChildren().remove(0,9);
            Node n = path.pop();
            String currentPuzzleState = n.getState().getCurrentPuzzle();
            for (int i = 0; i < 9; i++) {
                labels.get(i).setText
                        (currentPuzzleState.charAt(i) == '0'? "" : String.valueOf(currentPuzzleState.charAt(i)));
                puzzle.add(labels.get(i), i%3, i/3);
            }
        }
    }
    @FXML
    private void showStats (ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistics.fxml"));
        Parent root = loader.load();

        //Get controller of scene2
        StatisticsController controller = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        controller.setData(maxDepth, solutionDepth, cost,
        nodesExpanded, runningTime);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Stats");
        stage.show();
    }

    private String getPuzzle () {
        String userPuzzle = inputPuzzle.getText();
        int expectedSum = 0;
        int actualSum = 0;
        for (int i = 0; i < userPuzzle.length(); i++) {
            expectedSum += i;
            actualSum += (int) userPuzzle.charAt(i) - 48;
        }
        if (expectedSum != actualSum || userPuzzle.length() > 9) {
            System.out.println("Wrong input");
            return "012345678";
        }
        if (userPuzzle.equals("")) {
            System.out.println("Wrong input");
            return "012345678";
        }
        for (int i = 0; i < 9; i++) {
            Label l = labels.get(i);
            if (userPuzzle.charAt(i) == '0') {
                l.setText("");
            } else {
                l.setText(String.valueOf(userPuzzle.charAt(i)));
            }
        }
        return userPuzzle;
    }
    public int getMaxDepth () {
        return maxDepth;
    }
}
