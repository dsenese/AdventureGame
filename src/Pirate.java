import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public interface Pirate {
	public void move();
	public Image getImage();
	public void setLocation(List<Point2D> islandLocations);
	public Point2D getPirateLocation();
}
