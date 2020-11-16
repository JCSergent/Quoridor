import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

enum Tile {
	PLAYER,
	WALL,
	INTERSECT
}

public class TileView {
	
	Tile tileType;
	Rectangle rect;
	boolean isOccupied = false;
	
	
	TileView(Tile tileType, int xPos, int yPos, int height, int width ){
		this.tileType = tileType;
		rect = new Rectangle();
		rect.setX(xPos);
		rect.setY(yPos);
		rect.setHeight(height);
		rect.setWidth(width);
		
		unhighlight();
	}
	
	public Rectangle getRectangle() {
		return rect;
	}
	
	public Tile getTileType() {
		return tileType;
	}
	
	public void unhighlight() {
		isOccupied = false;
		switch( tileType ) {
		case PLAYER: 
			rect.setFill(Color.GREY);
			break;
		case WALL:
			rect.setFill(Color.BEIGE);
			break;
		case INTERSECT:
			rect.setFill(Color.BEIGE);
			break;
		}
	}
	
	public void setPlayerOccupied(int playerNum) {
		isOccupied = true;
		switch(  playerNum ) {
		case 1:
			rect.setFill(Color.BLUE);
			break;
		case 2:
			rect.setFill(Color.GREEN);
			break;
		}
	}
	
	public void setPossibleMove(int playerNum) {
		switch(  playerNum ) {
		case 1:
			rect.setFill(Color.LIGHTBLUE);
			break;
		case 2:
			rect.setFill(Color.LIGHTGREEN);
			break;
		}
	}
	
	public void setWallBuilt() {
		isOccupied = true;
		rect.setFill(Color.HOTPINK);
	}
	
	public void setWallBuildable() {
		rect.setFill(Color.LIGHTPINK);
	}
	
	
	public void setPlayerMoveEventHandler(Controller controller, int x, int y) {
		rect.setOnMouseClicked( event -> {
			controller.movePlayer(x, y);
		});
	}
	
	public void unSetPlayerMoveEventHandler() {
		rect.setOnMouseClicked(event -> {
			
		});
	}
	
	public void setWallPrepEventHandler(Controller controller, int x, int y) {
		rect.setOnMouseClicked( event -> {
			setWallBuilt();
			controller.prepWall(x, y);
		});
	}
	
	public void unSetWallPrepEventHandler() {
		rect.setOnMouseClicked( event -> {
		});
	}
	
	public void setWallUnprepEventHandler(Controller controller, int wallX, int wallY) {
		rect.setOnMouseClicked( event -> {
			controller.unPrepWall(wallX, wallY);
		});
	}
	
	public void setWallBuildEventHandler(Controller controller, int wall1X, int wall1Y, int wall2X, int wall2Y) {
		rect.setOnMouseClicked(event -> {
			controller.buildWall(wall1X, wall1Y, wall2X, wall2Y);
		});
	}
	
	public boolean getOccupiedSatus() {
		return isOccupied;
	}
	
	
}
