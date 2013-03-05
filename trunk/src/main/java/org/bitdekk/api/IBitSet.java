package org.bitdekk.api;

public interface IBitSet {
	/**
	 * @param bitSet
	 * @return true if parameter bitset object and this bitset object are the same type and 
	 * this bitSet Object (A) has all the bits set as the parameter bitset object (B) i.e. A & B == A, false otherwise
	 */
	boolean contains(IBitSet bitSet);
	/**
	 * @param position bit at this position is set
	 */
	void set(int position);
	/**
	 * Returns the position of the next set bit start from the given position, inclusive
	 * @param position look up begins from this position in bitset
	 * @return the position of the next set bit
	 */
	int nextSetBit(int position);
}
