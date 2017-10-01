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
 * The Class BouncingBallAnimation. Displays a bouncing ball using java fx.
 * Based on code from Student Resources - Course Content: Week 12 -
 * MultipleBounceBall.java as I do not have access to the textbook. Modified the
 * program so it has a label which displays the current speed of the ball, users
 * can increase and decrease the speed of the ball by using an up or down arrow
 * key, and it uses a thread to animate the bouncing ball movements
 * 
 * @see <a href=
 *      "https://interact2.csu.edu.au/bbcswebdav/courses/S-ITC313_201760_B_D/S-ITC313_201760_B_D_ImportedContent_20170709094511/Student%20Resources%20Folder/Example%20Code/Week%2012/MultipleBounceBall.java">Student Resources - Course Content: Week 12 - MultipleBounceBall.java</a>
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

		// Add a ball
		BallPane ballPane = new BallPane();
		ballPane.add();

		// Pause and resume animation
		ballPane.setOnMousePressed(e -> ballPane.pause());
		ballPane.setOnMouseReleased(e -> ballPane.play());

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

						// pause on/off
						if (ballPane.isPaused()) {
							Thread.sleep(500);
							continue;
						}

						// limit animation speed
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

		private boolean	paused			= false;

		/** The animation rate. */
		DoubleProperty	animationRate	= new SimpleDoubleProperty();

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
			animationRate.set(animationRate.get() < 50 ? rate + 0.1 : 50);
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

		public void play() {
			paused = false;
		}

		public void pause() {
			paused = true;
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

		/**
		 * Checks if the ball panes animation is paused.
		 *
		 * @return true, if is paused
		 */
		public boolean isPaused() {
			return paused;
		}
	}

	/**
	 * The Class Ball.
	 */
	class Ball extends Circle {

		/** The rate of change in x. */
		private double	dx	= 1;

		/** The rate of change in y. */
		private double	dy	= 1;

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