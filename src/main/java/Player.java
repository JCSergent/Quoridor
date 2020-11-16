
public class Player {
	
	int playerNum;
	int wallCount;
	int xPos;
	int yPos;
	
	
	public Player(int playerNum) {
		this.playerNum = playerNum;
		wallCount = 10;
		
		if(playerNum==1) {
			xPos = 0;
			yPos = 8;
		}
		else if(playerNum==2) {
			xPos = 16;
			yPos = 8;
		}
	}
	
	public int getPlayerNum() {
		return playerNum;
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public void setX(int xPos) {
		this.xPos = xPos;
	}
	
	public void setY(int yPos) {
		this.yPos = yPos;
	}
	
	public int getWallCount() {
		return wallCount;
	}
	
	public void setWallCount(int walls) {
		this.wallCount = walls;
	}
	
}
