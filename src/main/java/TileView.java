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
	
	
	TileView(Tile tileType, int xPos, int yPos, int height, int width ){
		this.tileType = tileType;
		rect = new Rectangle();
		rect.setX(xPos);
		rect.setY(yPos);
		rect.setHeight(height);
		rect.setWidth(width);
		
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
	
	public Rectangle getRectangle() {
		return rect;
	}
	
	public Tile getTileType() {
		return tileType;
	}
	
	
}
