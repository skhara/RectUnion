
package org.spheros.bitunion.lib;

import org.junit.Test;
import org.spheros.bitunion.lib.Utils;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class UtilsBulkOrTest {

    private ArrayList<BitSet> pairs = new ArrayList<>();

    @Test
    public void SingleElementOrTest() {
        prepareSingleElementPairs();

        for (int i = 0; i < pairs.size(); i+=4) {
            BitSet bs1 = pairs.get(i);
            BitSet bs2 = pairs.get(i + 1);
            BitSet bs3 = pairs.get(i + 2);
            BitSet bs4 = pairs.get(i + 3);

            bs1.or(bs2);
            Utils.getInstance().or(bs3, bs4, 0, 1);

            assertEquals(bs1.cardinality(), bs3.cardinality());
        }
    }

    @Test
    public void RandomElementsOrTest() {
        prepareRandomElementPairs();

        for (int i = 0; i < pairs.size(); i+=4) {
            BitSet bs1 = pairs.get(i);
            BitSet bs2 = pairs.get(i + 1);
            BitSet bs3 = pairs.get(i + 2);
            BitSet bs4 = pairs.get(i + 3);

            bs1.or(bs2);
            Utils.getInstance().or(bs3, bs4, 0, bs3.size());

            assertEquals(bs1.cardinality(), bs3.cardinality());
        }
    }

    private void prepareRandomElementPairs() {
        pairs.clear();
        Random r = new Random();
        int number = r.nextInt(10) + 10;
        for (int i = 0; i < number; i++) {
            int size = r.nextInt(100) + 5;

            BitSet bs1 = new BitSet(size);
            BitSet bs2 = new BitSet(size);

            for (int j = 0; j < size; j++) {
                bs1.set(j, r.nextBoolean());
                bs2.set(j, r.nextBoolean());
            }

            BitSet bs3 = (BitSet) bs1.clone();
            BitSet bs4 = (BitSet) bs2.clone();

            pairs.add(bs1);
            pairs.add(bs2);
            pairs.add(bs3);
            pairs.add(bs4);
        }
    }

    private void prepareSingleElementPairs() {
        pairs.clear();
        prepare4False();
        prepare4True();
    }

    private void prepare4True() {
        for (int i = 0; i < 4; i++) {
            addSingleElementBitset(true);
        }
    }

    private void prepare4False() {
        for (int i = 0; i < 4; i++) {
            addSingleElementBitset(false);
        }
    }

    private void addSingleElementBitset(boolean value) {
        BitSet bs = new BitSet(1);
        bs.set(0, value);

        pairs.add(bs);
    }
}
