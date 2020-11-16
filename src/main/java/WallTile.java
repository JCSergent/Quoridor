
public class WallTile extends TileModel{
	
	boolean isBuilt;
	
	public WallTile(int x, int y) {
		super(Tile.WALL, x, y);
		isBuilt = false;
	}
	
	public void BuildWall() {
		isBuilt = true;
	}
	
	public void BreakWall() {
		isBuilt = false;
	}
	
}
