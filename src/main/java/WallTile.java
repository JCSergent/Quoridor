
public class WallTile extends TileModel{
	
	boolean isBuilt;
	
	public WallTile(int x, int y) {
		super(Tile.WALL, x, y);
		isBuilt = false;
	}
	
	public void buildWall() {
		isBuilt = true;
	}
	
	public void breakWall() {
		isBuilt = false;
	}
	
	public boolean isBuilt() {
		return isBuilt;
	}
}
