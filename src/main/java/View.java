import java.util.ArrayList;

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
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class View {
	
	Controller controller;
	
	final private int CANVASWIDTH = 825;
	final private int CANVASHEIGHT = 635;
	
	TileView[][] tiles;
	
	Group root;
	HBox hb;
	Label statusTxt;
	Label p1WallLabel;
	Label p2WallLabel;
	VBox player1Walls;
	VBox player2Walls;
	
	
	public View(Stage theStage, Controller controller) {
		
		theStage.setTitle("Coridor but cool");
		root = new Group();
		Scene theScene = new Scene(root,CANVASWIDTH, CANVASHEIGHT);
		this.controller = controller;
		theStage.setScene(theScene);
		
		HBox hb = new HBox();
		VBox gameLog = new VBox();
		gameLog.setPadding(new Insets(20, 0, 0, 10));
		
		Button startBtn = new Button("New Game");
		controller.setHandlerForNewGameBtn(startBtn);
		statusTxt = new Label();
		statusTxt.setPadding(new Insets(10,0,0,5));
		statusTxt.setFont(new Font(15));
		
		HBox playerWalls = new HBox();
		player1Walls = new VBox();
		player1Walls.setPadding(new Insets(0,0,0,10));
		player2Walls = new VBox();
		player2Walls.setPadding(new Insets(0,0,0,10));
		
		playerWalls.getChildren().add(player1Walls);
		playerWalls.getChildren().add(player2Walls);
		
		
		p1WallLabel = new Label();
		p1WallLabel.setPadding(new Insets(20,0,10,4));
		p1WallLabel.setTextAlignment(TextAlignment.CENTER);
		p2WallLabel = new Label();
		p2WallLabel.setPadding(new Insets(20,0,10,4));
		p2WallLabel.setTextAlignment(TextAlignment.CENTER);
		player1Walls.getChildren().add(p1WallLabel);
		player2Walls.getChildren().add(p2WallLabel);
		
		gameLog.getChildren().add(startBtn);
		gameLog.getChildren().add(statusTxt);
		gameLog.getChildren().add(playerWalls);
		
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
	
	public void updateGameBoard(TileModel[][] gameBoard, int playerNum) {
		for(int i=0;i<17;i++) {
			for(int j=0;j<17;j++) {
				if(gameBoard[i][j].getOccupied()!=null) {
					tiles[i][j].setPlayerOccupied(gameBoard[i][j].getOccupied().getPlayerNum());
				}
				if(gameBoard[i][j].isBuilt()) {
					tiles[i][j].setWallBuilt(gameBoard[i][j].getOccupied());
				}
			}
		}
	}	
	
	public void setPossiblePlayerMoves(ArrayList<TileModel> possibleTileMoves, Player currPlayer) {
		int x, y;
		for(TileModel tile : possibleTileMoves) {
			x = tile.getX();
			y = tile.getY();
			tiles[y][x].setPossibleMove(currPlayer.getPlayerNum());
			tiles[y][x].setPlayerMoveEventHandler(controller, x , y);
		}
	}
	
	public void unSetPossiblePlayerMoves(ArrayList<TileModel> possibleTileMoves, Player player) {
		int currX = player.getX();
		int currY = player.getY();
		int x,y;
		for(TileModel tile : possibleTileMoves) {
			x = tile.getX();
			y = tile.getY();
			tiles[y][x].unhighlight();
			tiles[y][x].unSetPlayerMoveEventHandler();
		}
	}
	
	public void currentPlayerUnHighlight(Player player) {
		tiles[player.getY()][player.getX()].unhighlight();
	}
	
	public void setAllWallsClickable() {
		for(int i=0;i<17;i++) {
			for(int j=0;j<17;j++) {
				if(tiles[i][j].getTileType()==Tile.WALL) {
					if(tiles[i][j].getOccupiedSatus()==false) {
						tiles[i][j].setWallPrepEventHandler(controller, j, i);
					}
					else tiles[i][j].unSetWallPrepEventHandler();
				}
			}
		}
	}
	
	public void unSetWallsClickable() {
		for(int i=0;i<17;i++) {
			for(int j=0;j<17;j++) {
				if(tiles[i][j].getTileType()==Tile.WALL) {
					tiles[i][j].unSetWallPrepEventHandler();
				}
			}
		}
	}
	
	public void setUnPrepWalls(int wallX, int wallY) {
		tiles[wallY][wallX].setWallUnprepEventHandler(controller, wallX, wallY);
	}
	
	public void setPossibleWalls(ArrayList<TileModel> possibleWallBuilds, int wallX, int wallY, int playerNum) {
		int x,y;
		for(TileModel tile : possibleWallBuilds) {
			x = tile.getX();
			y = tile.getY();
			tiles[y][x].setWallBuildable(playerNum);
			tiles[y][x].setWallBuildEventHandler(controller, x, y, wallX, wallY);
		}
	}
	
	public void unSetPossibleWalls(ArrayList<TileModel> possibleWallBuilds, int wallX, int wallY) {
		tiles[wallY][wallX].unhighlight();
		int x,y;
		for(TileModel tile : possibleWallBuilds) {
			x = tile.getX();
			y = tile.getY();
			tiles[y][x].unhighlight();
			tiles[y][x].unSetWallPrepEventHandler();
		}
	}

	public void gameWon(Player winner, Player loser) {
		statusTxt.setText("Player "+winner.getPlayerNum()+" Won!");
		p1WallLabel.setText("Player "+loser.getPlayerNum()+" Sucks ass");
		p2WallLabel.setText("");
	}
	
	public void updateLabel(int playerNum, int p1Walls, int p2Walls) {
		statusTxt.setText("Player "+playerNum+"'s turn");
		
		player1Walls.getChildren().clear();
		player2Walls.getChildren().clear();
		p1WallLabel.setText("Player 1 walls");
		p2WallLabel.setText("Player 2 walls");
		player1Walls.getChildren().add(p1WallLabel);
		player2Walls.getChildren().add(p2WallLabel);
		
		
		for(int i=0;i<p1Walls;i++) {
			Rectangle rect = new Rectangle();
			rect.setHeight(15);
			rect.setWidth(80);
			rect.setFill(Color.DARKBLUE);
			rect.setStroke(Color.WHITE);
			player1Walls.getChildren().add(rect);
		}
		
		for(int i=0;i<p2Walls;i++) {
			Rectangle rect = new Rectangle();
			rect.setHeight(15);
			rect.setWidth(80);
			rect.setFill(Color.DARKGREEN);
			rect.setStroke(Color.WHITE);
			player2Walls.getChildren().add(rect);
		}
	}
	
	public void clearLabels() {
		statusTxt.setText("");
		p1WallLabel.setText("");
		p2WallLabel.setText("");
	}
}
	
