
package org.spheros.bitunion.lib;

import java.util.BitSet;

public class Utils {
    private static Utils instance;

    private Utils(){}

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }

        return instance;
    }

    /**
     * Utility method (absent in java.util.Bitset) that adds one Bitset to another, performing 'or'
     * <p> operation between overlapping bits
     *
     * @param bs1 current Bitset
     * @param bs2 Bitset being added to the current one
     * @param fromIndex starting index in the current Bitset (inclusive)
     * @param toIndex ending index in the current Bitset (exclusive)
     */
    public void or(BitSet bs1, BitSet bs2, int fromIndex, int toIndex) {
        int w = toIndex - fromIndex;
        int lastIndex = (w > bs2.size()) ? fromIndex + bs2.size() : toIndex;

        for (int i = fromIndex, j = 0; i < lastIndex; i++, j++) {
            boolean bit = bs1.get(i) | bs2.get(j);
            bs1.set(i, bit);
        }
    }

}
