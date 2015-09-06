
package org.spheros.bitunion.lib;

import org.junit.Test;
import org.spheros.bitunion.lib.Point;
import org.spheros.bitunion.lib.Rectangle;

public class RectangleBoundsValidationTest {

    @Test(expected = Exception.class)
    public  void testBoundsAsPointValidation() throws Exception {
        new Rectangle(new Point(10, 10), new Point(10, 10));
    }

    @Test(expected = Exception.class)
    public void testBoundsAsBottomXLess() throws Exception {
        new Rectangle(new Point(10, 10), new Point(9, 20));
    }

    @Test(expected = Exception.class)
    public void testBoundsAsBottomYLess() throws Exception {
        new Rectangle(new Point(10, 10), new Point(10, 9));
    }

    @Test(expected = Exception.class)
    public void testBoundsAsTopLeftXNegative() throws Exception {
        new Rectangle(new Point(-1, 0), new Point(0, 0));
    }

    @Test(expected = Exception.class)
    public void testBoundsAsTopLeftYNegative() throws Exception {
        new Rectangle(new Point(0, -1), new Point(0, 0));
    }

    @Test(expected = Exception.class)
    public void testBoundsAsBottomRightXNegative() throws Exception {
        new Rectangle(new Point(0, 0), new Point(-1, 0));
    }

    @Test(expected = Exception.class)
    public void testBoundsAsBottomRightYNegative() throws Exception {
        new Rectangle(new Point(0, 0), new Point(0, -1));
    }
}
