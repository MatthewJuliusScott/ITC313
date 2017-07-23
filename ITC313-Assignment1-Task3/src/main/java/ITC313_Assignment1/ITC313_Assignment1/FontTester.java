package ITC313_Assignment1.ITC313_Assignment1;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
 * The Class FontTester. Draws a 2D archery target.
 */
public class FontTester extends Application {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
    public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Font Tester");

		// define the canvas size
		int width = 350;
		int height = 120;
		
		// get available font families
		List<String> fonts = javafx.scene.text.Font.getFamilies();
		
		// add the available fonts, up to but not exceeding 10
		ObservableList<String> fontOptions = FXCollections.observableArrayList();
		for (int i = 0; i < (Math.min(fonts.size(), 10)); i++) {
			fontOptions.add(fonts.get(i));
		}
		
		// create a combo box for the user to select their font
		final ComboBox<String> fontComboBox = new ComboBox<String>(fontOptions);
		
		// create a label for the combo box
		final Label comboBoxLabel = new Label("Font Name");
		
		// add the size options
		ObservableList<String> sizeOptions = FXCollections.observableArrayList("8", "12", "16", "24", "30", "36", "42", "48", "56");
		
		// create the size selector combo box
		final ComboBox<String> sizeComboBox = new ComboBox<String>(sizeOptions);
		
		// create a label for the size selector
		final Label sizeComboBoxLabel = new Label("Font Size");
		
		// create the text for display / testing
		final Label displayText = new Label("Programming is fun");
		displayText.setStyle("-fx-font-size: " + "36");
		                
        // add them all to a layout pane
        final GridPane root = new GridPane();
        root.setGridLinesVisible(true);
                
        // use a white background
        root.setStyle("-fx-background-color: white");
        
        // align the root to the center
        root.setAlignment(Pos.BASELINE_CENTER);
        
        // place a vertical gap between nodes
        root.setVgap(15);
        
        // place a horizontal gap between nodes
        root.setHgap(5);
        
        // make the display text fill all columns
        GridPane.setColumnSpan(displayText, 4);
        
        // add the rows
        root.addRow(0, comboBoxLabel);
        root.addRow(0, fontComboBox);
        root.addRow(0, sizeComboBoxLabel);
        root.addRow(0, sizeComboBox);
        
        root.addRow(1, displayText);
		// and add them to the scene, also setting background colour
		final Scene scene = new Scene(root, width, height, Color.WHITE);

		// draw the scene to the stage
		primaryStage.setScene(scene);
		primaryStage.show();
    }
}
