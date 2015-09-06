
package org.spheros.bitunion.lib;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.spheros.bitunion.lib.BitUnion;
import org.spheros.bitunion.lib.Point;
import org.spheros.bitunion.lib.Rectangle;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BitUnionTest {

    private ArrayList<Rectangle> rectangles = new ArrayList<>();
    private BitUnion union;

    @Before
    public void setupTestData() throws Exception {
        union = BitUnion.getInstance();
        getNonIntersectedRects();
    }

    @Test
    public void testNonIntersectable() throws Exception {
        union.clear();

        for (int i = 0; i < 3; i++) {
            union.union(rectangles.get(i));
        }

        assertEquals(getNonIntersectedSquare(), union.getSquare());
    }

    @Test
    public void testIntersectable() throws Exception {
        union.clear();
        rectangles.clear();

        getIntersectedRects();

        for (Rectangle rect : rectangles) {
            union.union(rect);
        }

        assertEquals(700 + (500 - 300) * (50 - 30) + 20, union.getSquare());
    }

    @After
    public void tearDownTestData() {
        rectangles.clear();
    }

    private int getNonIntersectedSquare() {
        int square = 0;
        for (int i = 0; i < 3; i++) {
            square+= rectangles.get(i).getSquare();
        }

        return square;
    }

    public void getIntersectedRects() throws Exception {
        rectangles.add(new Rectangle(new Point(20, 20), new Point(40, 40)));
        rectangles.add(new Rectangle(new Point(30, 30), new Point(50, 50)));
        rectangles.add(new Rectangle(new Point(300, 30), new Point(500, 50)));
        rectangles.add(new Rectangle(new Point(300, 30), new Point(500, 50)));
        rectangles.add(new Rectangle(new Point(300, 30), new Point(500, 50)));
        rectangles.add(new Rectangle(new Point(300, 30), new Point(500, 50)));
        rectangles.add(new Rectangle(new Point(499, 30), new Point(501, 50)));

    }

    private void getNonIntersectedRects() throws Exception {
        rectangles.add(new Rectangle(new Point(20, 20), new Point(40, 40)));
        rectangles.add(new Rectangle(new Point(80, 200), new Point(140, 240)));
        rectangles.add(new Rectangle(new Point(1020, 2000), new Point(4000, 4050)));
    }
}
