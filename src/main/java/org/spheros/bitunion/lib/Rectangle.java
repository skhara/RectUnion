
package org.spheros.bitunion.lib;

import java.util.BitSet;
import java.util.Iterator;

/**
 * Rectangle BitShape implementation
 */
public class Rectangle implements BitShape {

    private Point topLeft;
    private Point bottomRight;

    /**
     * Constructor of Rectangle instance with specific square bounds
     *
     * @param topLeft top left bound
     * @param bottomRight bottom right bound
     * @throws IllegalArgumentException thrown if the passed bounds not valid
     */
    public Rectangle(Point topLeft, Point bottomRight) throws IllegalArgumentException {
        if (!isValidBounds(topLeft, bottomRight)) {
            throw new IllegalArgumentException("Bounds check error! Rectangle coordinates are out of this union's bounds!");
        }

        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Iterator<BitStripe> iterator() {
        return new RectIterator();
    }

    private boolean isValidBounds(Point topLeft, Point bottomRight) {

        return !(topLeft.x < 0 || bottomRight.x < 0 || topLeft.y < 0 || bottomRight.y < 0) &&
                !(topLeft.x >= bottomRight.x || topLeft.y >= bottomRight.y);

    }

    /**
     * Iterates over sequentially generated BitStripes
     */
    private class RectIterator implements Iterator<BitStripe> {
        private int stripesHeight = bottomRight.y - topLeft.y;
        private int stripeWidth = bottomRight.x - topLeft.x;
        private int stripesRemained = stripesHeight;
        private BitSet b = new BitSet(stripeWidth);

        public boolean hasNext() {
            return stripesRemained-- > 0;
        }

        public BitStripe next() {

            //just set each bit to 'true'
            b.set(0, stripeWidth);

            int startX = topLeft.x;
            int startY = topLeft.y + stripesHeight - stripesRemained;

            return new BitStripe(b, new Point(startX, startY));
        }
    }

    /**
     * Get current square of this rectangle
     *
     * @return the square value
     */
    public int getSquare() {
        return (bottomRight.x - topLeft.x) * (bottomRight.y - topLeft.y);
    }
}
