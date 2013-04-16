/*
 * Copyright 2013 Contributors of bit-dekk
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
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
	void set(long position);
	/**
	 * @param position bit at this position is cleared
	 */
	void clear(long position);
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
	void and(IBitSet query);
	void or(IBitSet query);
	IBitSet clone();
}
