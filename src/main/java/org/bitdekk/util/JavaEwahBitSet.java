package org.bitdekk.util;

import java.util.Iterator;

import org.bitdekk.api.IBitSet;

import com.googlecode.javaewah.EWAHCompressedBitmap;

public class JavaEwahBitSet implements IBitSet {
	private EWAHCompressedBitmap bitMap = EWAHCompressedBitmap.bitmapOf();
	@Override
	public boolean contains(IBitSet bitSet) {
		JavaEwahBitSet bitSetCast = (JavaEwahBitSet)bitSet;
		return bitSetCast.bitMap.cardinality() == (this.bitMap.andCardinality(bitSetCast.bitMap));
	}

	@Override
	public boolean intersects(IBitSet bitSet) {
		JavaEwahBitSet bitSetCast = (JavaEwahBitSet)bitSet;
		return this.bitMap.intersects(bitSetCast.bitMap);
	}

	@Override
	public void set(long position) {
		bitMap = bitMap.or(EWAHCompressedBitmap.bitmapOf((int)position));
	}

	@Override
	public void clear(long position) {
		EWAHCompressedBitmap bitmap = EWAHCompressedBitmap.bitmapOf((int)position);
		bitmap.not();
		bitMap = bitMap.and(bitmap);
	}
	@Override
	public boolean get(int position) {
		return false;
	}

	@Override
	public void and(IBitSet query) {
		JavaEwahBitSet bitSetCast = (JavaEwahBitSet)query;
		this.bitMap = this.bitMap.and(bitSetCast.bitMap);
	}

	@Override
	public void or(IBitSet query) {
		JavaEwahBitSet bitSetCast = (JavaEwahBitSet)query;
		this.bitMap = this.bitMap.or(bitSetCast.bitMap);
	}

	@Override
	public void andNot(IBitSet bitset) {
		JavaEwahBitSet bitSetCast = (JavaEwahBitSet)bitset;
		this.bitMap = this.bitMap.andNot(bitSetCast.bitMap);
	}
	public IBitSet clone() {
		JavaEwahBitSet javaEwahBitSet = new JavaEwahBitSet();
		try {
			javaEwahBitSet.bitMap = (EWAHCompressedBitmap) this.bitMap.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return javaEwahBitSet;
	}

	@Override
	public Iterator<Integer> iterator() {
		return bitMap.iterator();
	}
	@Override
	public boolean equals(Object o) {
		if(o != null && o instanceof JavaEwahBitSet)
			return this.bitMap.equals(((JavaEwahBitSet)o).bitMap);
		else
			return false;
	}
	@Override
	public int hashCode() {
		return this.bitMap.hashCode();
	}

	@Override
	public int nextSetBit(int position) {
		return -1;//TODO implement this
	}

	@Override
	public void compress() {
		//already compressed
	}
}
