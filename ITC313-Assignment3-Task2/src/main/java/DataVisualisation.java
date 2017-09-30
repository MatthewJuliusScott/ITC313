
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DataVisualisation extends Application {

	private static String tempDirectory = System.getProperty("java.io.tmpdir");
	private static Stage stage;

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Gets the staff count by state.
	 *
	 * @return the staff count by state
	 * @throws Exception
	 *             the exception
	 */
	public static Map<String, Integer> getStaffCountByState() throws Exception {

		Map<String, Integer> stateCountMap = new HashMap<String, Integer>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT state, COUNT(*) AS count FROM Staff GROUP BY state;";

		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:file:" + tempDirectory + "db ", "SA", "");
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				stateCountMap.put(rs.getString("state"), rs.getInt("count"));
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
		return stateCountMap;
	}

	/**
	 * Gets the percentage of a total as a nicely formatted String.
	 *
	 * @param total
	 *            the total
	 * @param value
	 *            the value
	 * @return the percentage
	 */
	private String getPercentage(int total, int value) {
		int percentage = (int) (Math
		        .round(((double) value / (double) total) * 100));
		return " " + String.valueOf(percentage) + "%";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Map<String, Integer> staffCountByState = getStaffCountByState();
		
		stage = primaryStage;
		stage.setResizable(false);

		primaryStage.setTitle("Staff Location By State");

		Label label = new Label("Staff Location By State");
		
		// Pie Chart
		PieChart pieChart = new PieChart();

		// get the total
		int total = 0;
		for (Integer current : staffCountByState.values()) {
			total += current;
		}
		
		// construct the slices
		PieChart.Data[] slices = new PieChart.Data[staffCountByState.values().size()];
		int i = 0;
		for (Entry<String, Integer> entry : staffCountByState.entrySet()) {
			slices[i++] = new PieChart.Data(entry.getKey() + getPercentage(total, entry.getValue()), entry.getValue());
		}

		pieChart.getData().addAll(slices);

		// add them all to a layout pane
		final BorderPane root = new BorderPane();
		
		root.setTop(label);
		BorderPane.setAlignment(label, Pos.CENTER);

		root.setCenter(pieChart);

		// use a white background
		root.setStyle("-fx-background-color: white");

		// and add them to the scene, also setting background colour
		final Scene scene = new Scene(root, Color.WHITE);

		// draw the scene to the stage
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
