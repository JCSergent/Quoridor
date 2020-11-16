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
	
	public void buildWall(int wall1X, int wall1Y, int wall2X, int wall2Y) {
		int intersectX, intersectY;
		if(wall1X==wall2X) {
			intersectX = wall1X;
			intersectY = Math.min(wall1Y, wall2Y)+1;
		}
		else {
			intersectX = Math.min(wall1X, wall2X)+1;
			intersectY = wall1Y;
		}
		gameBoard[wall1Y][wall1X].buildWall();
		gameBoard[wall2Y][wall2X].buildWall();
		gameBoard[intersectY][intersectX].buildWall();
		
		checkIntersects();
	}
	
	public void checkIntersects() {
		
		for(int i=0;i<17;i++) {
			for(int j=0;j<17;j++) {
				if(i%2!=0 && j%2!=0) { //Parse through all the Intersects
					int wallCount = 0;
					if(gameBoard[i][j-1].isBuilt()) {wallCount++;}
					if(gameBoard[i][j+1].isBuilt()) {wallCount++;}
					if(gameBoard[i-1][j].isBuilt()) {wallCount++;}
					if(gameBoard[i+1][j].isBuilt()) {wallCount++;}
					if(wallCount>=2) { gameBoard[i][j].buildWall(); }
				}
			}
		}
	}
	
	//TODO: Implement checks to see if a wall is blocking path
	public ArrayList<TileModel> getPossiblePlayerMoves(Player player) {
		ArrayList<TileModel> possibleTiles = new ArrayList<TileModel>();
		int currX = player.getX();
		int currY = player.getY();
		if(!(currY+2>16)) { //Check the TIle Below the Current Player
			if(!gameBoard[currY+1][currX].isBuilt()) { //If the is not a wall in front of it
				if(gameBoard[currY+2][currX].getOccupied()==null) { //If tile isn't Occupied
					possibleTiles.add(gameBoard[currY+2][currX]); //Add to possibleTiles
				}
				else { //If tile is Occupied, check the tile behind it
					if(!(currY+4>16)) {
						if(!gameBoard[currY+3][currX].isBuilt()) {
							possibleTiles.add(gameBoard[currY+4][currX]);
						}
					}
				}
			}
		}
		if(!(currY-2<0)) { //Check the Tile Above the Current Player
			if(!gameBoard[currY-1][currX].isBuilt()) {
				if(gameBoard[currY-2][currX].getOccupied()==null) {
					possibleTiles.add(gameBoard[currY-2][currX]);
				}
				else {
					if(!(currY-4<0)) {
						if(!gameBoard[currY-3][currX].isBuilt()) {
							possibleTiles.add(gameBoard[currY-4][currX]);
						}
					}
				}
			}
		}
		if(!(currX+2>16)) { //Check the Tile to the Right of Current Player
			if(!gameBoard[currY][currX+1].isBuilt()) {
				if(gameBoard[currY][currX+2].getOccupied()==null) {
					possibleTiles.add(gameBoard[currY][currX+2]);
				}
				else {
					if(!(currX+4>16)) {
						if(!gameBoard[currY][currX+3].isBuilt()) {
							possibleTiles.add(gameBoard[currY][currX+4]);
						}
					}
				}
			}
		}
		if(!(currX-2<0)) { //Check the Tile to the Left of Current Player
			if(!gameBoard[currY][currX-1].isBuilt()) {
				if(gameBoard[currY][currX-2].getOccupied()==null) {
					possibleTiles.add(gameBoard[currY][currX-2]);
				}
				else {
					if(!(currX-4<0)) {
						if(!gameBoard[currY][currX-3].isBuilt()) {
							possibleTiles.add(gameBoard[currY][currX-4]);
						}
					}
				}
			}
		}
		return possibleTiles;
	}
	
	public ArrayList<TileModel> getPossibleWallBuilds(int wallX, int wallY) {
		ArrayList<TileModel> possibleWallBuilds = new ArrayList<TileModel>();
		if(wallY%2==0) { //If the wall is Vertical check the nearby vertical walls
			if(!(wallY+2>16)) {
				if(!gameBoard[wallY+1][wallX].isBuilt()) {
					if(!gameBoard[wallY+2][wallX].isBuilt()){
						possibleWallBuilds.add(gameBoard[wallY+2][wallX]);
					}
				}
			}
			if(!(wallY-2<0)) {
				if(!gameBoard[wallY-1][wallX].isBuilt()) {
					if(!gameBoard[wallY-2][wallX].isBuilt()){
						possibleWallBuilds.add(gameBoard[wallY-2][wallX]);
					}
				}
			}
		}
		else {
			if(!(wallX+2>16)) {
				if(!gameBoard[wallY][wallX+1].isBuilt()) {
					if(!gameBoard[wallY][wallX+2].isBuilt()){
						possibleWallBuilds.add(gameBoard[wallY][wallX+2]);
					}
				}
			}
			if(!(wallX-2<0)) {
				if(!gameBoard[wallY][wallX-1].isBuilt()) {
					if(!gameBoard[wallY][wallX-2].isBuilt()){
						possibleWallBuilds.add(gameBoard[wallY][wallX-2]);
					}
				}
			}
		}
		
		return possibleWallBuilds;
	}
	
}
