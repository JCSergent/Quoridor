import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller extends Application {
	
	Model model;
	View view;
	GameBoard gameBoard;
	
	Player player1;
	Player player2;
	Player currPlayer;
	
	ArrayList<TileModel> possiblePlayerMoves;
	
	
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
		player1 = new Player(1);
		player2 = new Player(2);
		
		model.resetGameBoard();
		gameBoard = model.getGameBoard();
		gameBoard.addPlayer(player1);
		gameBoard.addPlayer(player2);
		
		view.drawBoard(17, 17);
		
		newTurn(player1);	
	}
	
	public void newTurn(Player currPlayer) {
		if(!checkWinConditions()) {
		
			this.currPlayer = currPlayer;
			view.getStatusLabel().setText("Player "+currPlayer.getPlayerNum()+"'s turn");
			possiblePlayerMoves = gameBoard.getPossiblePlayerMoves(currPlayer);
			view.setPossiblePlayerMoves(possiblePlayerMoves, currPlayer);
		
			view.updateGameBoard(gameBoard.gameBoard);
		}
		else view.gameWon(this.currPlayer, currPlayer);
		
	}
	
	public void movePlayer(int newX, int newY) {
		view.unSetPossiblePlayerMoves(possiblePlayerMoves, currPlayer);
		gameBoard.movePlayer(currPlayer, newX, newY);
		currPlayer.setX(newX);
		currPlayer.setY(newY);
		
		if(currPlayer.equals(player1)) {
			newTurn(player2);
		}
		else newTurn(player1);
		
	}
	
	public boolean checkWinConditions() {
		if(player1.getX()==16) {
			return true;
		}
		if(player2.getX()==0) {
			return true;
		}
		else return false;
	}
	
	
	
	
}