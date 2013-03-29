package org.bitdekk.api;

public interface IBitSet {
	/**
	 * @param bitSet
	 * @return true if parameter and this object are the same type and 
	 * this bitSet Object (A) has all the bits set as the parameter (B) i.e. A & B == A, false otherwise
	 */
	boolean contains(IBitSet bitSet);
	/**
	 * @param bitSet
	 * @return true if any bit is set after intersection of this object (A) and parameter i.e. A & B != empty, false otherwise 
	 */
	boolean intersects(IBitSet bitSet);
	/**
	 * @param position bit at this position is set
	 */
	void set(int position);
	/**
	 * @param position bit at this position is cleared
	 */
	void clear(int position);
	/**
	 * Returns the position of the next set bit start from the given position, inclusive
	 * @param position look up begins from this position in bitset
	 * @return the position of the next set bit
	 */
	int nextSetBit(int position);
	/**
	 * @param position
	 * @return true if bit at this position is set, false otherwise
	 */
	boolean get(int position);
	IBitSet clone();
	void and(IBitSet query);
}
