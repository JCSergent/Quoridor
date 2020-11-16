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
	ArrayList<TileModel> possibleWallBuilds;	
	
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
		//System.out.println("New Game");
		player1 = new Player(1);
		player2 = new Player(2);
		
		model.resetGameBoard();
		gameBoard = model.getGameBoard();
		gameBoard.addPlayer(player1);
		gameBoard.addPlayer(player2);
		
		view.clearLabels();
		view.drawBoard(17, 17);
		
		newTurn(player1);	
	}
	
	public void newTurn(Player currPlayer) {
		if(!checkWinConditions()) {
			this.currPlayer = currPlayer;
			view.updateLabel(currPlayer.getPlayerNum(), player1.getWallCount(), player2.getWallCount());
			
			possiblePlayerMoves = gameBoard.getPossiblePlayerMoves(currPlayer);
			
			
			view.setPossiblePlayerMoves(possiblePlayerMoves, currPlayer);
			view.updateGameBoard(gameBoard.gameBoard, currPlayer.getPlayerNum());
			
			if(currPlayer.getWallCount()!=0) {
				view.setAllWallsClickable();
			}
			else view.unSetWallsClickable();
		}
		else view.gameWon(this.currPlayer, currPlayer);
		
	}
	
	public void movePlayer(int newX, int newY) {
		view.currentPlayerUnHighlight(currPlayer);
		view.unSetPossiblePlayerMoves(possiblePlayerMoves, currPlayer);
		gameBoard.movePlayer(currPlayer, newX, newY);
		currPlayer.setX(newX);
		currPlayer.setY(newY);
		
		if(currPlayer.equals(player1)) {
			newTurn(player2);
		}
		else newTurn(player1);
		
	}
	
	public void prepWall(int wallX, int wallY) {
		possibleWallBuilds = gameBoard.getPossibleWallBuilds(wallX, wallY);
		
		view.unSetPossiblePlayerMoves(possiblePlayerMoves, currPlayer);
		view.unSetWallsClickable();
		view.setUnPrepWalls(wallX, wallY);
		view.setPossibleWalls(possibleWallBuilds, wallX, wallY, currPlayer.getPlayerNum());
		
	}
	
	public void unPrepWall(int wallX, int wallY) {
		view.setPossiblePlayerMoves(possiblePlayerMoves, currPlayer);
		view.unSetPossibleWalls(possibleWallBuilds, wallX, wallY);
		view.setAllWallsClickable();
	}
	
	
	public void buildWall(int wall1X, int wall1Y, int wall2X, int wall2Y) {
		gameBoard.buildWall(wall1X, wall1Y, wall2X, wall2Y, currPlayer);
		view.unSetPossibleWalls(possibleWallBuilds, wall1X, wall1Y);
		currPlayer.setWallCount(currPlayer.getWallCount()-1);
		
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