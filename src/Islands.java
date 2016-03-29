import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Islands {
	List<Point2D> islandCoords;
	Point2D treasureCoord;
	Point2D shipCoord;
	Image treasureImage;

	public Islands(int count, Point2D shipLocation2) {
		this.islandCoords = new ArrayList<Point2D>();
		this.shipCoord = new Point2D(shipLocation2.getX(), shipLocation2.getY());
		Random treasure = new Random();
		for (int i = 0; i < count; i++) {
			Random randx = new Random();
			Random randy = new Random();

			Point2D temp = new Point2D(randx.nextInt(24), randy.nextInt(24));
			while ((temp != shipCoord) && !(islandCoords.contains(temp))) {
				islandCoords.add(temp);
			}
		}
		int treasureIsland = treasure.nextInt(count-2);
		this.treasureCoord = islandCoords.get(treasureIsland);

	}
	
	public Image getTreasureImage(){
		treasureImage = new Image("file:images//treasurchest.jpeg", 15, 15, false, true);
		return treasureImage;
	}
	
	public List<Point2D> getIslandLocations() {
		return islandCoords;
	}

}
