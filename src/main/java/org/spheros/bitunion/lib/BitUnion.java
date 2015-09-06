
package org.spheros.bitunion.lib;

import java.util.BitSet;

/**
 * Joins arbitrary BitShapes.
 * <p>It knows nothing about the geometric shape being joined. It uses BitStripe startPoint infos instead.
 */
public class BitUnion {

    private static final Point DEFAULT_TOP_LEFT = new Point(0, 0);
    private static final Point DEFAULT_BOTTOM_RIGHT = new Point(10001, 10001);

    private int width;

    private BitSet bitSet;

    /**
     * Creates BitUnion instance with default square bounds
     */
    public static BitUnion getInstance() {
        try {
            return new BitUnion(DEFAULT_TOP_LEFT, DEFAULT_BOTTOM_RIGHT);
        } catch (Exception e) {
            //'Almost' impossible case, so we just keep silence :)
            return null;
        }
    }

    /**
     * Creates BitUnion instance with specific square bounds
     *
     * @param topLeft top left square bound
     * @param bottomRight bottom right square bound
     */
    public BitUnion(Point topLeft, Point bottomRight) throws Exception {
        if (!isValidBounds(topLeft, bottomRight)) {
            throw new Exception("BitUnion bounds are not valid!");
        }

        bitSet = new BitSet( (bottomRight.x - topLeft.x) * (bottomRight.y - topLeft.y) );
        width = bottomRight.x - topLeft.x;
        bitSet.clear();
    }

    /**
     * Utility method to join rectangles having their integer coordinates
     *
     * @param topX top left x coordinate
     * @param topY top left y coordinate
     * @param bottomX bottom right x coordinate
     * @param bottomY bottom right y coordinate
     * @throws IllegalArgumentException thrown if the passed bounds not valid
     */
    public void unionRect(int topX, int topY, int bottomX, int bottomY) throws IllegalArgumentException {
        union(new Rectangle(new Point(topX, topY), new Point(bottomX, bottomY)));
    }

    /**
     * Unions BitShape objects no matter which geometric shape they have.
     * <p>While doing this it relies on BitSripe information.
     *
     * @param shape BitShape going to be unioned with this BitUnion
     */
    public void union(BitShape shape) {
        for (BitStripe stripe : shape) {
            Point sp = stripe.startPoint;
            BitSet bs = stripe.bitSet;
            int index = sp.x + sp.y * width;
            Utils.getInstance().or(bitSet, bs, index, index + bs.size());
        }
    }

    /**
     * Get current square of this union
     *
     * @return the square of all currently unioned shapes
     */
    public int getSquare() {
        return bitSet.cardinality();
    }

    /**
     * Clears internal bitset structure
     */
    public void clear() {
        bitSet.clear();
    }

    private boolean isValidBounds(Point topLeft, Point bottomRight) {

        return !(topLeft.x < 0 || bottomRight.x < 0 || topLeft.y < 0 || bottomRight.y < 0) &&
                !(topLeft.x >= bottomRight.x || topLeft.y >= bottomRight.y);

    }
}
