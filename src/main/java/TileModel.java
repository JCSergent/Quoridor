
public class TileModel {

	Tile tileType;
	int x;
	int y;
	
	//Player
	boolean isOccupied = false;
	
	//Wall
	boolean isBuilt = false;
	
	//Intersect
	
	public TileModel(Tile tileType, int x, int y) {
		this.tileType = tileType;
		this.x = x;
		this.y = y;
		
		switch( tileType ) {
		case PLAYER:
			break;
		case WALL:
			break;
		case INTERSECT:
			break;
		}
	}
	
}
