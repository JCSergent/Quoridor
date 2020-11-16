import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View {
	
	final private int CANVASWIDTH = 825;
	final private int CANVASHEIGHT = 635;
	
	TileView[][] tiles;
	
	Group root;
	HBox hb;
	
	
	public View(Stage theStage, Controller controller) {
		
		theStage.setTitle("Coridor but cool");
		root = new Group();
		Scene theScene = new Scene(root,CANVASWIDTH, CANVASHEIGHT);
		
		theStage.setScene(theScene);
		
		HBox hb = new HBox();
		VBox gameLog = new VBox();
		gameLog.setPadding(new Insets(20, 0, 0, 10));
		
		Button startBtn = new Button("New Game");
		controller.setHandlerForNewGameBtn(startBtn);
		Label statusTxt = new Label("Player 1's Turn");
		statusTxt.setPadding(new Insets(10,0,0,5));
		
		gameLog.getChildren().add(startBtn);
		gameLog.getChildren().add(statusTxt);
		
		drawBoard(17, 17);
		
		root.getChildren().add(gameLog);
		theStage.show();
	}
	
	public void drawBoard(int x, int y) {
		FlowPane fp = new FlowPane();
		tiles = new TileView[x][y];
		int posX = 200;
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
			posX = 200;
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
	
	public void updateGameBoard(TileModel[][] gameBoard) {
		for(int i=0;i<17;i++) {
			for(int j=0;j<17;j++) {
				if(gameBoard[i][j].getOccupied()!=null) {
					tiles[i][j].highlight();
				}
			}
		}
	}	
	
	//TODO: Method that lightly highlights possible locations the player can jump too
	public void displayPossiblePlayerMoves(Player player) {
		
	}
}
	
