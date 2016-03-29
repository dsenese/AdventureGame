import java.util.List;

import javafx.geometry.Point2D;

public class PirateFactory {
	Point2D shipCoords;
	List<Point2D> islandLocations;
	
	public Pirate createPirate(Point2D shipCoords, List<Point2D> islandLocations, String pirateType){
		this.shipCoords = shipCoords;
		this.islandLocations = islandLocations;
		
		if (pirateType == null){
			return null;
		}
		if (pirateType.equalsIgnoreCase("PirateHunt")){
			return new PirateHunt(shipCoords, islandLocations);
		}
		else if (pirateType.equalsIgnoreCase("PirateRand")){
			return new PirateRand(shipCoords, islandLocations);
		}
		return null;
	}
}
