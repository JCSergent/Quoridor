import javafx.application.Application;
import javafx.scene.control.Button;
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
		view = new View(theStage, this);
		
		
	}
	
	public void setHandlerForNewGameBtn(Button startBtn) {
		startBtn.setOnAction(event -> {
			startGame();
		});
	}
	
	public void startGame() {
		System.out.println("New Game");
		Player player1 = new Player(1);
		Player player2 = new Player(2);
		
		model.resetGameBoard();
		GameBoard gameBoard = model.getGameBoard();
		gameBoard.addPlayer(player1);
		gameBoard.addPlayer(player2);
		
		view.updateGameBoard(gameBoard.gameBoard);
//		boolean gamePlayed = true;
//		boolean changeTurnFlag = false;
//		while(gamePlayed) {
//			
//			
//			
//			view.updateGameBoard(gameBoard.gameBoard);
//			
//			
//		}
		
		
	}
	
}