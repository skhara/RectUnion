
package org.spheros.bitunion.lib;

/**
 * 'BitStriped' representation of a geometric shape
 *
 *  <p>BitShape shapes can be processed stripe by stripe to reduce memory consumption
 */
public interface BitShape extends Iterable<BitStripe>{}
