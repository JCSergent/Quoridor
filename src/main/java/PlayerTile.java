
public class PlayerTile extends TileModel{
	
	Player isOccupied;
	
	public PlayerTile(int x, int y) {
		super(Tile.PLAYER, x, y);
		
		isOccupied = null;
	}
	
	public void setOccupied(Player player) {
		isOccupied = player;
	}
	
	public Player getOccupied() {
		return isOccupied;
	}
	
	
	
	
	
	
}
