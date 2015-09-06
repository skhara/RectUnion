
package org.spheros.bitunion.lib;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.spheros.bitunion.lib.BitStripe;
import org.spheros.bitunion.lib.Point;
import org.spheros.bitunion.lib.Rectangle;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class RectangleTest {

    private ArrayList<Rectangle> rectangles = new ArrayList<>();
    private BitSet bitSet = new BitSet(1000000);

    @Before
    public void setupTestData() throws Exception {
        getPredefinedRectangles();
        getRandomRectangles();
    }

    @After
    public void tearDownTestData() {
        rectangles.clear();
    }

    @Test
    public void testSquare() {
        assertEquals(rectangles.get(0).getSquare(), 8100);
        assertEquals(rectangles.get(1).getSquare(), 8400);
        assertEquals(rectangles.get(2).getSquare(), 11200);
    }

    @Test
    public void testSquareFromBitstripes() {
        for (Rectangle rect : rectangles) {
            assertEquals(rect.getSquare(), getSquareFromBitstripes(rect));
        }
    }

    private int getSquareFromBitstripes(Rectangle rect) {
        int square = 0;

        for (BitStripe stripe : rect) {
            square += stripe.bitSet.cardinality();
        }

        return square;
    }

    private void getPredefinedRectangles() throws Exception {
        Rectangle r1 = new Rectangle(new Point(10, 10), new Point(100, 100));
        Rectangle r2 = new Rectangle(new Point(15, 10), new Point(120, 90));
        Rectangle r3 = new Rectangle(new Point(30, 40), new Point(100, 200));
        Rectangle r4 = new Rectangle(new Point(331, 41), new Point(756, 211));
        rectangles.add(r1);
        rectangles.add(r2);
        rectangles.add(r3);
        rectangles.add(r4);
    }

    private void getRandomRectangles() throws Exception {
        for (int i = 0; i < 10; i++) {
            rectangles.add(generateRectangle());
        }
    }

    private Rectangle generateRectangle() throws Exception {
        Random r = new Random();

        Point topLeft = new Point(r.nextInt(500), r.nextInt(500));
        Point bottomRight = new Point(r.nextInt(500) + 500, r.nextInt(500) + 500);

        return new Rectangle(topLeft, bottomRight);
    }
}
