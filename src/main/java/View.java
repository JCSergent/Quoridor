import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View {
	
	final private int CANVASWIDTH = 625;
	final private int CANVASHEIGHT = 625;
	
	TileView[][] tiles;
	
	Group root;
	
	
	public View(Stage theStage) {
		
		theStage.setTitle("Coridor but cool");
		root = new Group();
		Scene theScene = new Scene(root,CANVASWIDTH, CANVASHEIGHT);
		
		theStage.setScene(theScene);
		
		drawBoard(17, 17);
		
		theStage.show();
	}
	
	public void drawBoard(int x, int y) {
		tiles = new TileView[x][y];
		int posX = 10;
		int posY = 10;
		boolean alternatingFlag = false;
		boolean wallFlag = false;
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				TileView tile;
				
				if(!wallFlag) {
					if(!alternatingFlag) {
						tile = new TileView(Tile.PLAYER, posX, posY, 50, 50);
						posX += 50;
						alternatingFlag = true;
					}
					else {
						tile = new TileView(Tile.WALL, posX, posY, 50, 20);
						posX += 20;
						alternatingFlag = false;
					}
				}
				else {
					if(!alternatingFlag) {
						tile = new TileView(Tile.WALL, posX, posY, 20, 50);
						posX += 50;
						alternatingFlag = true;
					}
					else {
						tile = new TileView(Tile.INTERSECT, posX, posY, 20, 20);
						posX += 20;
						alternatingFlag = false;
					}	
				}
				
				tiles[i][j] = tile;
				root.getChildren().add(tile.getRectangle());
				
			}
			alternatingFlag = false;
			posX = 10;
			if(!wallFlag) { 
				wallFlag = true;
				posY += 50;
			}
			else {
				wallFlag = false;
				posY += 20;
			}
		}
	}
	
}