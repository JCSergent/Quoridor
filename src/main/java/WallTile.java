
public class WallTile extends TileModel{
	
	boolean isBuilt;
	
	public WallTile(int x, int y) {
		super(Tile.WALL, x, y);
		isBuilt = false;
	}
	
	public void buildWall(Player player) {
		isBuilt = true;
		isOccupied = player;
	}
	
	public void breakWall() {
		isBuilt = false;
		isOccupied = null;
	}
	
	public boolean isBuilt() {
		return isBuilt;
	}
}
