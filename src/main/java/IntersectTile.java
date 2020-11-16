
public class IntersectTile extends TileModel{

	boolean isBuilt;
	
	public IntersectTile(int x, int y) {
		super(Tile.INTERSECT, x, y);
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
