package org.bitdekk.util;

import java.util.Iterator;

import org.bitdekk.api.IBitSet;

public class BItDekkBitSet implements IBitSet {
	private int[] zeroes;
	private int[] bits;
	private int length;
	private long wlen;
	@Override
	public Iterator<Integer> iterator() {
		return null;
	}

	@Override
	public boolean contains(IBitSet bitSet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean intersects(IBitSet bitSet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void set(long position) {
		long wPos = position << 5; //divide by 32
		int bitOffset = 1 << (position % 32);
		if(wPos < wlen) {
			for(int j = 0, i = -1; j < zeroes.length; j++) {
		    	i = i + zeroes[j] + 1;
			}
		} else {
			
		}
		//find insert point
		//insert point lies on a existing int
		//insert point lies on an existing zero
		//insert point lies outside current range
		//find new size
		//add a new zero, put newZero = newSize - oldSize - 1
		//add a new int, new int = offset;
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

	@Override
	public void and(IBitSet query) {
		// TODO Auto-generated method stub

	}

	@Override
	public void or(IBitSet query) {
		// TODO Auto-generated method stub

	}

	@Override
	public void andNot(IBitSet bitset) {
		// TODO Auto-generated method stub

	}

	@Override
	public int nextSetBit(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void compress() {
		// TODO Auto-generated method stub

	}
	@Override
	public IBitSet clone() {
		return null;
	}
}
