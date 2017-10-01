import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * The Class BouncingBallAnimation.
 */
public class BouncingBallAnimation extends Application {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {

		BorderPane pane = new BorderPane();

		// Create a scene and place the pane in the stage
		Scene scene = new Scene(pane, 250, 150);
		primaryStage.setTitle("Bouncing Ball Animation"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		BallPane ballPane = new BallPane();
		ballPane.add();

		// increase or decrease the ball speed with up and down arrow keys
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case UP :
						ballPane.increaseSpeed();
						break;
					case DOWN :
						ballPane.decreaseSpeed();
						break;
					default :
						break;
				}
			}
		});

		// Create speed label
		Label speedLabel = new Label("");
		speedLabel.textProperty().bind(Bindings.concat("Speed: ")
		        .concat(ballPane.rateProperty().asString()));
		pane.setCenter(ballPane);
		pane.setTop(speedLabel);

		// Run the animation in a background thread
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						// Wait for animation interval milliseconds.
						long interval = (long) (50
						        / ballPane.rateProperty().doubleValue());
						if (interval > 500) {
							interval = 500;
							Thread.sleep(500);
							continue;
						} else if (interval < 1) {
							interval = 1;
							Thread.sleep(1);
						}
						Thread.sleep(interval);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							ballPane.moveBall();
						}
					});
				}
			}
		}, "Animation Thread");

		// Terminate the running animation thread if the application exits
		t.setDaemon(true);
		t.start();
	}

	/**
	 * The Class BallPane.
	 */
	public class BallPane extends Pane {

		/** The animation rate. */
		DoubleProperty animationRate = new SimpleDoubleProperty();

		/**
		 * Adds a new ball to the ball pane
		 */
		public void add() {
			animationRate.set(1);
			Color color = new Color(Math.random(), Math.random(), Math.random(),
			        0.5);
			getChildren().add(new Ball(30, 30, 20, color));
		}

		/**
		 * Increases animation speed.
		 */
		public void increaseSpeed() {
			double rate = (double) Math.round(animationRate.get() * 10) / 10;
			animationRate.set(rate + 0.1);
		}

		/**
		 * Decreases animation speed.
		 */
		public void decreaseSpeed() {
			double rate = (double) Math.round(animationRate.get() * 10) / 10;
			animationRate.set(animationRate.get() > 0 ? rate - 0.1 : 0);
		}

		/**
		 * Animation Rate property.
		 *
		 * @return the double property
		 */
		public DoubleProperty rateProperty() {
			return animationRate;
		}

		/**
		 * Moves the ball, maintains current direction until hitting the edge of
		 * the pane. Speed it controller by how often this method is called.
		 */
		protected void moveBall() {
			for (Node node : this.getChildren()) {
				Ball ball = (Ball) node;
				// Check boundaries
				if (ball.getCenterX() < ball.getRadius()
				        || ball.getCenterX() > getWidth() - ball.getRadius()) {
					ball.dx *= -1; // Change ball move direction
				}
				if (ball.getCenterY() < ball.getRadius()
				        || ball.getCenterY() > getHeight() - ball.getRadius()) {
					ball.dy *= -1; // Change ball move direction
				}

				// Adjust ball position
				ball.setCenterX(ball.dx + ball.getCenterX());
				ball.setCenterY(ball.dy + ball.getCenterY());
			}
		}
	}

	/**
	 * The Class Ball.
	 */
	class Ball extends Circle {

		/** The rate of change in x. */
		private double dx = 1;
		
		/** The rate of change in y. */
		private double dy = 1;

		/**
		 * Instantiates a new ball.
		 *
		 * @param x
		 *            the x
		 * @param y
		 *            the y
		 * @param radius
		 *            the radius
		 * @param color
		 *            the color
		 */
		Ball(double x, double y, double radius, Color color) {
			super(x, y, radius);
			setFill(color); // Set ball color
		}
	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support.
	 * Not needed for running from the command line.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}