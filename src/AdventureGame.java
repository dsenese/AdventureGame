
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AdventureGame extends Application{
	boolean[][] map;
	Pane root;
	Button btnReset = new Button("Reset");
	Button close = new Button("Close");
	OceanMap oceanMap;
	int dimensions;
	final int scalingFactor = 25;
	boolean shipSank = false;
	boolean shipFoundTreasure = false;
	Point2D treasureLocation;
	Ship ship;
	ImageView treasureImageView;
	ImageView shipSunk;
	ImageView shipTreasure;
	ImageView shipImageView;
	ImageView pirateImageView;
	List<ImageView> pirateViews;
	List<Pirate> pirateLocations;
	Scene scene;
	Stage stage;

	
	
	@Override
	public void start(Stage mapStage) throws Exception {
		stage = mapStage;
		startGame(mapStage);
	}
	
	public void startGame(Stage mapStage){
		stage = mapStage;
		oceanMap = OceanMap.getInstance();
		dimensions = OceanMap.dimensions;
		root = new AnchorPane();
		ship = oceanMap.getShip();
		treasureLocation = oceanMap.islands.treasureCoord;
		map = oceanMap.oceanMap;
		pirateLocations = oceanMap.pirates;
		
		drawMap();
		loadShip();
		loadPirates();
		loadTreasure();
		
		close.setOnAction(myHandler);
		close.setLayoutX(50);
		close.setLayoutY(660);
		root.getChildren().add(close);
		
		btnReset.setOnAction(e -> {
		       restart(stage);
		    });
		btnReset.setLayoutX(150);
		btnReset.setLayoutY(660);
		root.getChildren().add(btnReset);
		
		scene = new Scene(root, 625, 700);
		mapStage.setTitle("Adventurer Challenge");
		mapStage.setScene(scene);
		mapStage.show();
		startSailing();
	}
	
	final EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>(){

        @Override
        public void handle(final ActionEvent event) {
        	System.exit(0);
        }
	};
	
	public void restart(Stage stage){
		endGame();
		startGame(stage);
	}
	
	private void loadPirates() {
		pirateViews = new ArrayList<ImageView>();

		for (Pirate pirate : pirateLocations) {
			Image piratePic = pirate.getImage();
			pirateImageView = new ImageView(piratePic);

			pirateImageView.setX(pirate.getPirateLocation().getX() * scalingFactor);
			pirateImageView.setX(pirate.getPirateLocation().getY() * scalingFactor);

			pirateViews.add(pirateImageView);
			root.getChildren().add(pirateImageView);
		}

		for (int i = 0; i < pirateViews.size(); i++) {
			pirateViews.get(i).setX(pirateLocations.get(i).getPirateLocation().getX() * scalingFactor);
			pirateViews.get(i).setY(pirateLocations.get(i).getPirateLocation().getY() * scalingFactor);
		}
	}

	private void loadShip(){
		Image shipImage =  new Image("File:images/ship.jpeg",25,25,false,true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(oceanMap.getShipLocation().getX()*scalingFactor);
		shipImageView.setY(oceanMap.getShipLocation().getY()*scalingFactor);
		root.getChildren().add(shipImageView);
	}
	
	private void loadTreasure(){
		Image treasureImage = oceanMap.islands.getTreasureImage();
		treasureImageView = new ImageView(treasureImage);
		treasureImageView.setX(treasureLocation.getX()*scalingFactor + 5);
		treasureImageView.setY(treasureLocation.getY()*scalingFactor + 5);
		root.getChildren().add(treasureImageView);
	}

	private void startSailing(){
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
	
			@Override
			public void handle(KeyEvent ke){
				switch(ke.getCode()){
				case RIGHT:
					ship.goEast();
					break;
				case LEFT:
					ship.goWest();
					break;
				case UP:
					ship.goNorth();
					break;
				case DOWN:
					ship.goSouth();
					break;
				default:
					break;
				}
				shipImageView.setX(ship.getShipLocation().getX()*scalingFactor);
				shipImageView.setY(ship.getShipLocation().getY()*scalingFactor);
				for (int i = 0; i < pirateViews.size(); i++) {
					Point2D temp = new Point2D(pirateLocations.get(i).getPirateLocation().getX(),
							pirateLocations.get(i).getPirateLocation().getY());

					pirateViews.get(i).setX(pirateLocations.get(i).getPirateLocation().getX() * scalingFactor);
					pirateViews.get(i).setY(pirateLocations.get(i).getPirateLocation().getY() * scalingFactor);

					if (ship.getsSunk(temp) == true) {
						shipSank = true;

					}
				}
				if (ship.foundTreasure(treasureLocation) == true){
					shipFoundTreasure = true;
					displayWin();
				}
				if (shipSank) {
					displayLoss();
				}
				
				
			}
		});
	}
	
	public void displayLoss(){
		Image sunk = new Image("file:images//loseImage.png", 650, 650, false, true);
		shipSunk = new ImageView(sunk);
		shipSunk.setX(0);
		shipSunk.setX(0);
		root.getChildren().add(shipSunk);
	}
	
	public void displayWin(){
		Image foundTreasure = new Image("file:images//treasurchest.jpeg", 650, 650, false, true);
		shipTreasure = new ImageView(foundTreasure);
		shipTreasure.setX(0);
		shipTreasure.setX(0);
		root.getChildren().add(shipTreasure);
	}
	
	public void endGame() {
		oceanMap = null;
		root = null;
		ship = null;
		map = null;
		scene = null;
		shipSank = false;
		shipFoundTreasure = false;
		treasureLocation = null;
		pirateLocations = null;
		OceanMap.setToNull();
	}

	public void drawMap(){
		for(int x = 0; x < dimensions; x++){
			for(int y = 0; y < dimensions; y++){
				Rectangle rect = new Rectangle(x*scalingFactor,y*scalingFactor,scalingFactor,scalingFactor);
				rect.setStroke(Color.BLACK);
				if(map[x][y])
					rect.setFill(Color.GREEN);
				else
					rect.setFill(Color.PALETURQUOISE);
				root.getChildren().add(rect);
			}
		}
		
	}
	public static void main(String[] args) throws Exception{
		Application.launch(args);
	}
}