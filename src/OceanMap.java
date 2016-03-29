import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import javafx.geometry.Point2D;

public class OceanMap {
	static int dimensions = 25;
	static int islandCount = 25;
	static int pirateCount = 6;
	Ship ship;
	Islands islands;
	Point2D shipLocation;
	List<Pirate> pirates;
	List<Point2D> pirateLocations;
	boolean[][] oceanMap;
	
	private static OceanMap instance;

	public OceanMap(int dimensions, int islandCount, int pirateCount) {
		this.ship = new Ship();
		this.pirates = new ArrayList<Pirate>();
		this.pirateLocations = new ArrayList<Point2D>();
		this.shipLocation = ship.getShipLocation();
		this.oceanMap = new boolean[25][25];
		this.islands = new Islands(islandCount, shipLocation);
		String pirateType = "PirateHunt";
		PirateFactory pirateFactory = new PirateFactory();
		
		for (int i = 0; i < pirateCount; i++){
			if (i > 0){
				pirateType = "PirateRand";
			}
			Pirate temp = pirateFactory.createPirate(shipLocation, islands.islandCoords, pirateType);
			temp.setLocation(islands.islandCoords);
			if (!pirates.contains(temp)) {
				pirates.add(temp);
			}
			pirateLocations.add(temp.getPirateLocation());
		}
		
		for (int i = 0; i < islandCount - 1; i++) {
			oceanMap[(int) islands.islandCoords.get(i).getX()][(int) islands.islandCoords.get(i).getY()] = true;
		}

		for (Pirate pirate : pirates) {
			ship.addObserver((Observer) pirate);
		}
		
	}
	
	public static OceanMap getInstance(){
		if (instance == null){
			instance = new OceanMap(dimensions, islandCount, pirateCount);
		}
		return instance;
	}
	
	public static void setToNull() {
	    instance = null;
	}
	
	public boolean[][] getMap() {
		return oceanMap;
	}

	public Ship getShip() {
		return ship;
	}

	public Point2D getShipLocation() {
		return ship.getShipLocation();
	}

	public List<Point2D> getIslandLocations() {
		return islands.islandCoords;
	}

	public List<Point2D> getPirateLocations() {
		return pirateLocations;
	}

	public boolean isIsland(Point2D coord) {
		if (islands.islandCoords.contains(coord)) {
			return true;
		}
		return false;
	}

}
