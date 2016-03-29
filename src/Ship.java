import java.util.Observable;
import java.util.Random;
import javafx.geometry.Point2D;

public class Ship extends Observable{
	
	Point2D currentLocation;
	Random intx;
	Random inty;

	public Ship() {
		this.intx = new Random();
		this.inty = new Random();
		this.currentLocation = new Point2D(intx.nextInt(24), inty.nextInt(24));

	}

	public Point2D getShipLocation() {
		return currentLocation;
	}
	
	public void goWest() {
		if (currentLocation.getX() > 0) {
			currentLocation = new Point2D(currentLocation.getX() - 1, currentLocation.getY());
			setChanged();
			notifyObservers(currentLocation);
		}

	}

	public void goEast() {
		if (currentLocation.getX() < 24) {
			currentLocation = new Point2D(currentLocation.getX() + 1, currentLocation.getY());
			setChanged();
			notifyObservers(currentLocation);
		}

	}
	public void goNorth() {
		if (currentLocation.getY() > 0) {
			currentLocation = new Point2D(currentLocation.getX(), currentLocation.getY() - 1);
			setChanged();
			notifyObservers(currentLocation);
			
		}
	}

	public void goSouth() {
		if (currentLocation.getY() < 24) {
			currentLocation = new Point2D(currentLocation.getX(), currentLocation.getY() + 1);
			setChanged();
			notifyObservers(currentLocation);
		}

	}
	
	public boolean getsSunk(Point2D pirate){
		if (currentLocation.equals(pirate)){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean foundTreasure(Point2D treasure){
		if (currentLocation.equals(treasure)){
			return true;
		}
		else {
			return false;
		}
	}
}