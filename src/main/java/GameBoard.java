
public class GameBoard {
	
	TileModel[][] gameBoard = new TileModel[17][17];
	
	
	public GameBoard() {
		for(int i=0;i<17;i++) {
			for(int j=0;j<17;j++) {
				TileModel tile;
				if(i%2==0 && j%2==0) {
					tile = new PlayerTile(i,j);
					gameBoard[i][j] = tile;
				}
				else if(i%2!=0 && j%2!=0) {
					tile = new IntersectTile(i,j);
					gameBoard[i][j] = tile;
				}
				else {
					tile = new WallTile(i,j);
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
	
}
