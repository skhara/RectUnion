
package org.spheros.bitunion.lib;

import java.util.BitSet;

/**
 * Wrapper over BitSet structure adding start point information to BitSet. This simplifies
 * <p>processing arbitrary-shaped figures by BitUnion
 */
public class BitStripe {
    BitSet bitSet;
    Point startPoint;

    /**
     * Constructor
     * @param bitSet java.util.BitSet containing bits representing a single horizontal row of points
     * @param startPoint x, y coordinates that correspond to the first bit point in the bitSet
     */
    BitStripe(BitSet bitSet, Point startPoint) {
        this.bitSet = bitSet;
        this.startPoint = startPoint;
    }
}
