import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class PirateHunt implements Observer, Pirate {
	Point2D pirateLocation;
	Point2D shipLoc;
	List<Point2D> islands;
	Image pirateImage;

	public PirateHunt(Point2D shipCoords, List<Point2D> islandLocations) {
		this.shipLoc = new Point2D(shipCoords.getX(), shipCoords.getY());
		this.pirateLocation = new Point2D(0, 0);
		this.islands = islandLocations;
	}

	@Override
	public void setLocation(List<Point2D> islandLocations) {
		Random randx = new Random();
		Random randy = new Random();
		Point2D temp = new Point2D(randx.nextInt(24), randy.nextInt(24));

		if ((temp != shipLoc) && !(islandLocations.contains(temp))) {
			pirateLocation = temp;

		}
	}
	
	@Override
	public Image getImage(){
		pirateImage = new Image("file:images//pirate2.jpg", 25, 25, false, true);
		return pirateImage;
	}
	
	@Override
	public void move() {
		Point2D north = new Point2D(pirateLocation.getX(), pirateLocation.getY() - 1);
		Point2D south = new Point2D(pirateLocation.getX(), pirateLocation.getY() + 1);
		Point2D west = new Point2D(pirateLocation.getX() - 1, pirateLocation.getY());
		Point2D east = new Point2D(pirateLocation.getX() + 1, pirateLocation.getY());

		if (north.distance(shipLoc) < pirateLocation.distance(shipLoc) && !(islands.contains(north))) {
			pirateLocation = north;
		}
		if (south.distance(shipLoc) < pirateLocation.distance(shipLoc) && !(islands.contains(south))) {
			pirateLocation = south;
		}
		if (east.distance(shipLoc) < pirateLocation.distance(shipLoc) && !(islands.contains(east))) {
			pirateLocation = east;
		}
		if (west.distance(shipLoc) < pirateLocation.distance(shipLoc) && !(islands.contains(west))) {
			pirateLocation = west;
		}

	}

	@Override
	public Point2D getPirateLocation() {
		return pirateLocation;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Ship) {
			shipLoc = (Point2D) arg;
		}
		move();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o == null)
			return false;

		if (o.getClass() != getClass())
			return false;

		PirateHunt that = (PirateHunt) o;
		return (this.pirateLocation == that.pirateLocation);
	}

	@Override
	public int hashCode() {
		int hash = 13;
		hash = (hash + (int) this.pirateLocation.getX()) * hashCode();
		hash = (hash + (int) this.pirateLocation.getY()) * hashCode();
		return hash;

	}

}