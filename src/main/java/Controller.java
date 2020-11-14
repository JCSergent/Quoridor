import javafx.application.Application;
import javafx.stage.Stage;

public class Controller extends Application {
	
	Model model;
	View view;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage theStage) {
		model = new Model();
		view = new View(theStage);
		
		GameBoard gameBoard = model.getGameBoard();
		gameBoard.addPlayer(0, 8);
		
	}
	
}