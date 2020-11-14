public class Model {
	
	GameBoard gameBoard;
	
	public Model() {
		gameBoard = new GameBoard();
	}
	
	public GameBoard getGameBoard() {
		return gameBoard;
	}
	
	public GameBoard resetGameBoard() {
		gameBoard = new GameBoard();
		return gameBoard;
	}
	
	
}