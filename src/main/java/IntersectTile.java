
public class IntersectTile extends TileModel{

	boolean isBuilt;
	
	public IntersectTile(int x, int y) {
		super(Tile.INTERSECT, x, y);
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
