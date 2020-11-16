
public class TileModel {

	Tile tileType;
	int x;
	int y;
	
	
	public TileModel(Tile tileType, int x, int y) {
		this.tileType = tileType;
		this.x = x;
		this.y = y;
		
	}
	
	public Tile getTileType() {
		return tileType;
	}
	
	public void setOccupied(Player player) {}
	
	public Player getOccupied() {return null;}
	
	public void BuildWall() {}
	
	public void BreakWall() {}
	
	
	
	
	
}
