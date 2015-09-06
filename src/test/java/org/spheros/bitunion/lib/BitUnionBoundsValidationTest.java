
package org.spheros.bitunion.lib;

import org.junit.Test;
import org.spheros.bitunion.lib.BitUnion;
import org.spheros.bitunion.lib.Point;

public class BitUnionBoundsValidationTest {

    @Test(expected = Exception.class)
    public  void testBoundsAsPointValidation() throws Exception {
        new BitUnion(new Point(10, 10), new Point(10, 10));
    }

    @Test(expected = Exception.class)
    public void testBoundsAsBottomXLess() throws Exception {
        new BitUnion(new Point(10, 10), new Point(9, 20));
    }

    @Test(expected = Exception.class)
    public void testBoundsAsBottomYLess() throws Exception {
        new BitUnion(new Point(10, 10), new Point(10, 9));
    }

    @Test(expected = Exception.class)
    public void testBoundsAsTopLeftXNegative() throws Exception {
        new BitUnion(new Point(-1, 0), new Point(0, 0));
    }

    @Test(expected = Exception.class)
    public void testBoundsAsTopLeftYNegative() throws Exception {
        new BitUnion(new Point(0, -1), new Point(0, 0));
    }

    @Test(expected = Exception.class)
    public void testBoundsAsBottomRightXNegative() throws Exception {
        new BitUnion(new Point(0, 0), new Point(-1, 0));
    }

    @Test(expected = Exception.class)
    public void testBoundsAsBottomRightYNegative() throws Exception {
        new BitUnion(new Point(0, 0), new Point(0, -1));
    }

}
