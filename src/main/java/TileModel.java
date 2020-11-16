
public class TileModel {

	Tile tileType;
	int x;
	int y;
	Player isOccupied;
	boolean isBuilt;
	
	
	public TileModel(Tile tileType, int x, int y) {
		this.tileType = tileType;
		this.x = x;
		this.y = y;
		
	}
	
	public Tile getTileType() {
		return tileType;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setOccupied(Player player) {}
	
	public Player getOccupied() {return null;}
	
	public void BuildWall() {}
	
	public void BreakWall() {}
	
	public boolean isBuilt() {return false;}
	
	
	
	
	
}
