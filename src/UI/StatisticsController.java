package UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {
    @FXML private Label maxDepthL;
    @FXML private Label solutionDepthL;
    @FXML private Label costL;
    @FXML private Label nodesExpL;
    @FXML private Label timeL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setData(int maxDepth, int solutionDepth, int cost,
                               int nodesExpanded, long runningTime) {
        maxDepthL.setText("Maximum Depth : " + maxDepth);
        solutionDepthL.setText("Solution Depth : " + solutionDepth);
        costL.setText("Cost : " + cost);
        nodesExpL.setText("Nodes Expanded : " + nodesExpanded);
        timeL.setText("Running time : " + runningTime + " ms");
    }
}
