import java.util.ArrayList;

public class GameBoard {
	
	TileModel[][] gameBoard = new TileModel[17][17];
	
	
	public GameBoard() {
		for(int i=0;i<17;i++) {
			for(int j=0;j<17;j++) {
				TileModel tile;
				if(i%2==0 && j%2==0) {
					tile = new PlayerTile(j,i);
					gameBoard[i][j] = tile;
				}
				else if(i%2!=0 && j%2!=0) {
					tile = new IntersectTile(j,i);
					gameBoard[i][j] = tile;
				}
				else {
					tile = new WallTile(j,i);
					gameBoard[i][j] = tile;
				}
			}
		}
	}
	
	public void addPlayer(Player player) {
		gameBoard[player.getY()][player.getX()].setOccupied(player);
	}
	
	public void movePlayer(Player player, int newX, int newY) {
		
		gameBoard[player.getY()][player.getX()].setOccupied(null);
		gameBoard[newY][newX].setOccupied(player);
	}
	
	//TODO: Implement checks to see if a wall is blocking path
	public ArrayList<TileModel> getPossiblePlayerMoves(Player player) {
		ArrayList<TileModel> possibleTiles = new ArrayList<TileModel>();
		int currX = player.getX();
		int currY = player.getY();
		if(!(currY+2>16)) { //Check the TIle Below the Current Player
			if(gameBoard[currY+2][currX].getOccupied()==null) { //If tile isn't Occupied
				possibleTiles.add(gameBoard[currY+2][currX]); //Add to possibleTiles
			}
			else { //If it is, check the tile Beyond it
				if(!(currY+4>16)) {
					possibleTiles.add(gameBoard[currY+4][currX]);
				}
			}
		}
		if(!(currY-2<0)) { //Check the Tile Above the Current Player
			if(gameBoard[currY-2][currX].getOccupied()==null) {
				possibleTiles.add(gameBoard[currY-2][currX]);
			}
			else {
				if(!(currY-4<0)) {
					possibleTiles.add(gameBoard[currY-4][currX]);
				}
			}
		}
		if(!(currX+2>16)) { //Check the Tile to the Right of Current Player
			if(gameBoard[currY][currX+2].getOccupied()==null) {
				possibleTiles.add(gameBoard[currY][currX+2]);
			}
			else {
				if(!(currX+4>16)) {
					possibleTiles.add(gameBoard[currY][currX+4]);
				}
			}
		}
		if(!(currX-2<0)) { //Check the Tile to the Left of Current Player
			if(gameBoard[currY][currX-2].getOccupied()==null) {
				possibleTiles.add(gameBoard[currY][currX-2]);
			}
			else {
				if(!(currX-4<0)) {
					possibleTiles.add(gameBoard[currY][currX-4]);
				}
			}
		}
		return possibleTiles;
	}
	
}
