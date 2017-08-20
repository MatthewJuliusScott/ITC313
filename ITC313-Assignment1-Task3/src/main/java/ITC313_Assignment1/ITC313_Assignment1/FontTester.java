
package ITC313_Assignment1.ITC313_Assignment1;

import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * The Class FontTester. Draws a 2D archery target.
 */
public class FontTester extends Application {

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
	 * Sets the display text weight, posture, font and size
	 *
	 * @param displayText
	 *            the display text
	 * @param isBold
	 *            the is bold
	 * @param isItalic
	 *            the is italic
	 * @param font
	 *            the font
	 * @param size
	 *            the size
	 */
	public void setDisplayText(final Label displayText, boolean isBold,
	        boolean isItalic, String font, double size) {
		FontWeight fontWeight = isBold ? FontWeight.BOLD : FontWeight.NORMAL;
		FontPosture fontPosture = isItalic
		        ? FontPosture.ITALIC
		        : FontPosture.REGULAR;

		displayText.setFont(Font.font(font, fontWeight, fontPosture, size));

		stage.sizeToScene();
	}

	private static Stage stage;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;
		stage.setResizable(false);

		primaryStage.setTitle("Font Tester");

		// get available font families
		final List<String> fonts = javafx.scene.text.Font.getFamilies();

		// add the available fonts, up to but not exceeding 10
		final ObservableList<String> fontOptions = FXCollections
		        .observableArrayList();
		for (int i = 0; i < (Math.min(fonts.size(), 10)); i++) {
			fontOptions.add(fonts.get(i));
		}

		// add the size options
		final ObservableList<String> sizeOptions = FXCollections
		        .observableArrayList("8", "12", "16", "24", "30", "36", "42",
		                "48", "56");

		// create the text for display / testing
		final Label displayText = new Label("Programming is fun");

		// create a label for the size selector
		final Label sizeComboBoxLabel = new Label("Font Size");

		// create a label for the combo box
		final Label comboBoxLabel = new Label("Font Name");

		// add checkboxes for bold and italic
		final CheckBox boldCheckBox = new CheckBox("Bold");
		final CheckBox italicCheckBox = new CheckBox("Italic");

		// create the size selector combo box
		final ComboBox<String> sizeComboBox = new ComboBox<String>(sizeOptions);
		sizeComboBox.setValue("36");

		// create a combo box for the user to select their font
		final ComboBox<String> fontComboBox = new ComboBox<String>(fontOptions);
		fontComboBox.setValue(fontOptions.get(0));

		// set how the size combo box behaves
		sizeComboBox.getSelectionModel().selectedItemProperty()
		        .addListener(new ChangeListener<String>() {

			        public void changed(ObservableValue<? extends String> ov,
			                String old_val, String new_val) {

				        setDisplayText(displayText, boldCheckBox.isSelected(),
				                italicCheckBox.isSelected(),
				                fontComboBox.getValue(),
				                Double.parseDouble(new_val));
			        }
		        });

		// set how the font combo box behaves
		fontComboBox.valueProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> ov,
			        String old_val, String new_val) {

				setDisplayText(displayText, boldCheckBox.isSelected(),
				        italicCheckBox.isSelected(), new_val,
				        Double.parseDouble(sizeComboBox.getValue()));
			}
		});

		// set how the bold checkbox behaves
		boldCheckBox.selectedProperty()
		        .addListener(new ChangeListener<Boolean>() {

			        public void changed(ObservableValue<? extends Boolean> ov,
			                Boolean old_val, Boolean new_val) {

				        setDisplayText(displayText, new_val,
				                italicCheckBox.isSelected(),
				                fontComboBox.getValue(),
				                Double.parseDouble(sizeComboBox.getValue()));
			        }
		        });

		// set how the italic checkbox behaves
		italicCheckBox.selectedProperty()
		        .addListener(new ChangeListener<Boolean>() {

			        public void changed(ObservableValue<? extends Boolean> ov,
			                Boolean old_val, Boolean new_val) {

				        setDisplayText(displayText, boldCheckBox.isSelected(),
				                new_val, fontComboBox.getValue(),
				                Double.parseDouble(sizeComboBox.getValue()));
			        }
		        });

		// set the initial value of the displayText
		displayText.setFont(Font.font(fontComboBox.getValue(),
		        Double.parseDouble(sizeComboBox.getValue())));

		// add them all to a layout pane
		final GridPane root = new GridPane();
		
		// use a white background
		root.setStyle("-fx-background-color: white");

		// align the root to the center
		root.setAlignment(Pos.BASELINE_CENTER);

		// place a vertical gap between nodes
		root.setVgap(15);

		// add the rows
		HBox fontHBox = new HBox();
		fontHBox.setAlignment(Pos.CENTER);
		fontHBox.getChildren().addAll(comboBoxLabel, fontComboBox, sizeComboBoxLabel, sizeComboBox);
		root.addRow(0, fontHBox);

		// add display text and center
		root.addRow(1, displayText);
		GridPane.setHalignment(displayText, HPos.CENTER);

		// make the checkbox horizontal box text fill all columns and center
		HBox checkBoxHBox = new HBox();
		checkBoxHBox.setAlignment(Pos.CENTER);
		checkBoxHBox.getChildren().addAll(boldCheckBox, italicCheckBox);
		root.addRow(2, checkBoxHBox);

		// and add them to the scene, also setting background colour
		final Scene scene = new Scene(root, Color.WHITE);

		// draw the scene to the stage
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
