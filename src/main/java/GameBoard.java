
public class GameBoard {
	
	TileModel[][] gameBoard = new TileModel[16][16];
	
	
	public GameBoard() {
		for(int i=0;i<16;i++) {
			for(int j=0;j<16;j++) {
				Tile type;
				if(i%2!=0 && j%2!=0) {
					type = Tile.PLAYER;
				}
				else if(i%2==0 && j%2!=0) {
					type = Tile.INTERSECT;
				}
				else type = Tile.WALL;
				TileModel tile = new TileModel(type, i, j);
				gameBoard[i][j] = tile;
			}
		}
	}
	
	public void addPlayer(int x, int y) {
		gameBoard[y][x].isOccupied = true;
	}
	

}
