package org.bitdekk.util;

import java.util.Iterator;

import org.bitdekk.api.IBitSet;

public class BitDekkSuperSparseBitSet implements IBitSet {
	private long[] cols;
	private long[] bits;

	@Override
	public void and(IBitSet other) {
		if(other instanceof BitDekkSuperSparseBitSet)
			and((BitDekkSuperSparseBitSet) other);
		else
			throw new RuntimeException("BitDekkSuperSparseBitSet cannot act on other bitset implementations");
	}
	@Override
	public void or(IBitSet other) {
		if(other instanceof BitDekkSuperSparseBitSet)
			or((BitDekkSuperSparseBitSet) other);
		else
			throw new RuntimeException("BitDekkSuperSparseBitSet cannot act on other bitset implementations");
	}
	@Override
	public void andNot(IBitSet other) {
		if(other instanceof BitDekkSuperSparseBitSet)
			andNot((BitDekkSuperSparseBitSet)other);
		else
			throw new RuntimeException("BitDekkSuperSparseBitSet cannot act on other bitset implementations");
	}
	@Override
	public boolean intersects(IBitSet other) {
		if(other instanceof BitDekkSuperSparseBitSet)
			return intersects((BitDekkSuperSparseBitSet)other);
		else
			throw new RuntimeException("BitDekkSuperSparseBitSet cannot act on other bitset implementations");
	}
	@Override
	public boolean contains(IBitSet other) {
		if(other instanceof BitDekkSuperSparseBitSet)
			return contains((BitDekkSuperSparseBitSet)other);
		else
			throw new RuntimeException("BitDekkSuperSparseBitSet cannot act on other bitset implementations");
	}
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			int currentIndex = BitDekkSuperSparseBitSet.this.nextSetBit(0);
			@Override
			public boolean hasNext() {
				return currentIndex > -1;
			}

			@Override
			public Integer next() {
				int temp = currentIndex;
				currentIndex = BitDekkSuperSparseBitSet.this.nextSetBit(++currentIndex);
				return temp;
			}

			@Override
			public void remove() {
				clear(currentIndex);
			}
		};
	}

	@Override
	public int compareTo(IBitSet o) {
		if(o instanceof BitDekkSuperSparseBitSet) {
			BitDekkSuperSparseBitSet other = (BitDekkSuperSparseBitSet)o;
			int min = Math.min(other.cols.length, cols.length);
			long[] otherCols = other.cols;
			long[] otherBits = other.bits;
			if(min != 0) {
				for(int i = 0; i < min; i++) {
					if(otherCols[i] != cols[i])
						return (int) (otherCols[i] - cols[i]);
					else if (otherBits[i] != bits[i])
						return (int) (bits[i] - otherBits[i]);
				}
			}
			return cols.length - otherCols.length;
		} else
			  throw new RuntimeException("BitDekkSuperSparseBitSet cannot act on other bitset implementations");
	}

	public boolean contains(BitDekkSuperSparseBitSet bitSet) {
		for(int i = 0; i < bitSet.cols.length; i++)
			if((bitSet.cols[i] & cols[i]) != bitSet.cols[i])
				return false;
		for(int i = 0; i < bitSet.cols.length; i++) {
			if(bitSet.cols[i] != 0)
				
			if((bitSet.cols[i] & cols[i]) != bitSet.cols[i])
				return false;
		}
		return false;
	}

	public boolean intersects(BitDekkSuperSparseBitSet bitSet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void set(long position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear(long position) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean get(int position) {
		// TODO Auto-generated method stub
		return false;
	}

	public void and(BitDekkSuperSparseBitSet query) {
		// TODO Auto-generated method stub

	}

	public void or(BitDekkSuperSparseBitSet query) {
		// TODO Auto-generated method stub

	}

	public void andNot(BitDekkSuperSparseBitSet bitset) {
		// TODO Auto-generated method stub

	}

	@Override
	public int nextSetBit(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	public IBitSet clone() {
		return null;
		
	}
}
