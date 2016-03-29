import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import javafx.geometry.Point2D;

public class islandsTest {
	Point2D point = new Point2D(25, 25);
	@Test
	public void test() {
		Islands test = new Islands(5, point);
		List<Point2D> coords = test.getIslandLocations();
		assertEquals(coords.size(), test.islandCoords.size());
		assertTrue(test.islandCoords.contains(test.treasureCoord));
	}

}
