import static org.junit.Assert.*;

import org.junit.Test;

import javafx.geometry.Point2D;

public class shipTest {

	@Test
	public void test() {
		Ship test = new Ship();
		Point2D point = new Point2D(-25,5);
		assertNotSame(point, test.currentLocation);
		point = test.currentLocation;
		assertTrue(test.getsSunk(point));
		assertTrue(test.foundTreasure(point));
	}

}
