package ITC313_Assignment1.ITC313_Assignment1;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * The Class TrafficLightSimulation. Draws a Traffic Light Simulation, which the
 * user can change the colour of using a radio button select.
 */
public class TrafficLightSimulation extends Application {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Traffic Light Simulation");

		// define the canvas size
		int width = 200;
		int height = 125;

		// define the draw height the circles start at, and the space between them
		int radius = 10;
		int drawHeight = 50;
		int gap = 5;
		int heightDiff = radius * 2 + gap;

		// define the border rectangle
		final Rectangle rect = new Rectangle(width / 2 - radius - gap, drawHeight - radius - gap, radius * 2 + gap * 2, drawHeight + (heightDiff) + gap);
		rect.setFill(Color.WHITE);
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(1);

		// create the circles
		final Circle topCircle = new Circle(width / 2, drawHeight, radius);
		topCircle.setFill(Color.WHITE);
		topCircle.setStrokeWidth(1);
		topCircle.setStroke(Color.BLACK);
		drawHeight += heightDiff;

		final Circle midCircle = new Circle(width / 2, drawHeight, radius);
		midCircle.setFill(Color.WHITE);
		midCircle.setStrokeWidth(1);
		midCircle.setStroke(Color.BLACK);
		drawHeight += heightDiff;

		final Circle botCircle = new Circle(width / 2, drawHeight, radius);
		botCircle.setFill(Color.WHITE);
		botCircle.setStrokeWidth(1);
		botCircle.setStroke(Color.BLACK);
		
		// group them all together
		final Group trafficLight = new Group(rect, topCircle, midCircle, botCircle);
				
		// create the radio buttons
		RadioButton topButton = new RadioButton("Red");
		topButton.setUserData("Red");
        RadioButton midButton = new RadioButton("Yellow");
        midButton.setUserData("Yellow");
        RadioButton botButton = new RadioButton("Green");
        botButton.setUserData("Green");
        
        // Group them so only one can be toggled at a time
        final ToggleGroup radioToggleGroup = new ToggleGroup();
        
        topButton.setToggleGroup(radioToggleGroup);
        midButton.setToggleGroup(radioToggleGroup);
        botButton.setToggleGroup(radioToggleGroup);
        
        // define what behaviour toggling buttons has
        radioToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

                 if (radioToggleGroup.getSelectedToggle() != null) {
                	 String selectedColour = radioToggleGroup.getSelectedToggle().getUserData().toString();
                	 if (selectedColour.equals("Red")) {
                		 topCircle.setFill(Color.RED);
                		 midCircle.setFill(Color.WHITE);
                		 botCircle.setFill(Color.WHITE);
                	 } else if (selectedColour.equals("Yellow")) {
                		 topCircle.setFill(Color.WHITE);
                		 midCircle.setFill(Color.YELLOW);
                		 botCircle.setFill(Color.WHITE);
                	 } else if (selectedColour.equals("Green")) {
                		 topCircle.setFill(Color.WHITE);
                		 midCircle.setFill(Color.WHITE);
                		 botCircle.setFill(Color.GREEN);
                	 }
                 }
             } 
        });
        
        // Place them in a horizontal box group
        HBox radioGroup = new HBox(topButton, midButton, botButton);
        radioGroup.setAlignment(Pos.BASELINE_CENTER);
        
        // add them all to a layout pane
        final GridPane root = new GridPane();
        
        // set a gap at the top of the grid pane
        root.setTranslateY(gap);
        
        // use a white background
        root.setStyle("-fx-background-color: white");
        
        // align the root to the center
        root.setAlignment(Pos.BASELINE_CENTER);
        
        // place a vertical gap between rows
        root.setVgap(15);
        
        // add the rows
        root.addRow(0, trafficLight);
        root.addRow(1, radioGroup);
        
        // align the node within the GridPane
        GridPane.setHalignment(trafficLight, HPos.CENTER);

		// and add them to the scene, also setting background colour
		final Scene scene = new Scene(root, width, height, Color.WHITE);

		// draw the scene to the stage
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
