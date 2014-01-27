package org.bitdekk.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bitdekk.api.IBitSet;

public class BitDekkSparseBitSet64 implements IBitSet {
	private long[] cols = new long[0];
	private long[] bits = new long[0];
	private int LENGTH = 64;
	//private static final int NO_OF_PROCESSORS = Runtime.getRuntime().availableProcessors();
	private static ExecutorService threadPool = Executors.newCachedThreadPool();
	
	@Override
	public void and(IBitSet other) {
		if(other instanceof BitDekkSparseBitSet64)
			and((BitDekkSparseBitSet64) other);
		else
			throw new RuntimeException("BitDekkSparseBitSet cannot act on other bitset implementations");
	}
	@Override
	public void or(IBitSet other) {
		if(other instanceof BitDekkSparseBitSet64)
			or((BitDekkSparseBitSet64) other);
		else
			throw new RuntimeException("BitDekkSparseBitSet cannot act on other bitset implementations");
	}
	@Override
	public void andNot(IBitSet other) {
		if(other instanceof BitDekkSparseBitSet64)
			andNot((BitDekkSparseBitSet64)other);
		else
			throw new RuntimeException("BitDekkSparseBitSet cannot act on other bitset implementations");
	}
	@Override
	public boolean intersects(IBitSet other) {
		if(other instanceof BitDekkSparseBitSet64)
			return intersects((BitDekkSparseBitSet64)other);
		else
			throw new RuntimeException("BitDekkSparseBitSet cannot act on other bitset implementations");
	}
	@Override
	public boolean contains(IBitSet other) {
		if(other instanceof BitDekkSparseBitSet64)
			return contains((BitDekkSparseBitSet64)other);
		else
			throw new RuntimeException("BitDekkSparseBitSet cannot act on other bitset implementations");
	}
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			int currentIndex = BitDekkSparseBitSet64.this.nextSetBit(0);
			@Override
			public boolean hasNext() {
				return currentIndex > -1;
			}

			@Override
			public Integer next() {
				int temp = currentIndex;
				currentIndex = BitDekkSparseBitSet64.this.nextSetBit(++currentIndex);
				return temp;
			}

			@Override
			public void remove() {
				clear(currentIndex);
			}
		};
	}
	public boolean contains1(final BitDekkSparseBitSet64 bitSet) {
		long[] bitSetCols = bitSet.cols;
		long[] bitSetBits = bitSet.bits;
		int i = 0, j = 0;
		for(int len = bitSetCols.length, len1 =  cols.length; i < len && j < len1; j++) {
			if(bitSetCols[i] > cols[j])
				;
			else if((bits[j] & bitSetBits[i]) != bitSetBits[i] || bitSetCols[i] < cols[j])
				return false;
			else
				i++;
		}
		if(j == cols.length && i < bitSetCols.length)
			return false;
		return true;
	}
	public boolean contains(final BitDekkSparseBitSet64 bitSet) {
		long[] bitSetCols = bitSet.cols;
		long[] bitSetBits = bitSet.bits;
		int len = bitSetCols.length;
		int len1 = cols.length;
		//boolean retVal = true;
		/*if(len > 0 && len1 > 1 && (len > len1 || bitSetCols[len - 1] > cols[len1 - 1]))
			return false;*/
		if(len > len1 || (len > 0 && len1 > 0 && bitSetCols[len - 1] > cols[len1 - 1]))
			return false;
		for(int i = 0, j = 0; i < len; i++, j++) {
			for(; (cols[j] < bitSetCols[i]); j++);
			if((cols[j] > bitSetCols[i]) || (bits[j] & bitSetBits[i]) != bitSetBits[i])
				return false;
		}
		return true;
		
		/*
		 for(int i = 0, index = 0; i < len && retVal; index++, i++) {
			index = binarySearch(cols, index, (int)bitSetCols[i] + 1, bitSetCols[i]);
			retVal &= ((bits[index] & bitSetBits[i]) == bitSetBits[i]);
				//return false;
		}
		return retVal;*/
	}
    private int binarySearch(long[] a, int fromIndex, int toIndex, long key) {
        int low = fromIndex;
        int high = toIndex;

        while (low <= high) {
            int mid = (low + high) >> 1;

            if (a[mid] < key)
                low = mid + 1;
            else if (a[mid] > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low);  // key not found.
    }

	public boolean intersects(BitDekkSparseBitSet64 bitSet) {
		if(bitSet.cols.length == 0 && cols.length == 0)
			return true;
		else {
			for(int i = 0, j = 0; i < bitSet.cols.length && j < cols.length;) {
				if(bitSet.cols[i] > cols[j])
					j++;
				else if(bitSet.cols[i] < cols[j])
					i++;
				else if((bits[j++] & bitSet.bits[i++]) != 0)
					return true;
			}
		}
		return false;
	}

	@Override
	public void set(long position) {
		long colPosition = position / LENGTH;
		for(int i = 0; i < cols.length; i++) {
			if(cols[i] == colPosition) {
				bits[i] = bits[i] | ((1 << (position % LENGTH)));
				return;
			}
		}
		int newColIndex = -Arrays.binarySearch(cols, colPosition) - 1;
		insertColumn(newColIndex, colPosition);
		bits[newColIndex] = bits[newColIndex] | (1 << position % LENGTH);
	}

	private void insertColumn(int column, long colPosition) {
		long[] newCols = new long[cols.length + 1];
		long[] newBits = new long[cols.length + 1];
		for(int i = 0, j = 0; i < newCols.length; i++) {
			if(i != column) {
				newCols[i] = cols[j];
				newBits[i] = bits[j];
				j++;
			} else {
				newCols[i] = colPosition;
			}
		}
		this.cols = newCols;
		this.bits = newBits;
	}
	@Override
	public void clear(long position) {
		long colPosition = position / LENGTH;
		int index = Arrays.binarySearch(cols, colPosition);
		if(index >= 0) {
			bits[index] = bits[index] & (~(1 << (position % LENGTH)));
			if(bits[index] == 0)
				removeEmptyBits(index);
		}
	}

	private void removeEmptyBits(int index) {
		long[] newColsTemp = new long[cols.length - 1];
		long[] newBitsTemp = new long[newColsTemp.length];
		for(int i = 0, k = 0; i < cols.length; i++) {
			if(i != index) {
				newColsTemp[k] = cols[i];
				newBitsTemp[k++] = bits[i];
			}
		}
		cols = newColsTemp;
		bits = newBitsTemp;
	}
	@Override
	public boolean get(int position) {
		int colPosition = position / LENGTH;
		int index = Arrays.binarySearch(cols, colPosition);
		if(index >= 0)
			return ((bits[index] >> (position % LENGTH)) & 1) == 1;
		return false;
	}

	public void and(BitDekkSparseBitSet64 bitSet) {
		if(this.cols.length == 0)
			return;
		long[] newColsTemp = new long[Math.min(bitSet.cols.length, this.cols.length)];
		long[] newBitsTemp = new long[newColsTemp.length];
		if(newColsTemp.length == 0) {
			this.cols = newColsTemp;
			this.bits = newBitsTemp;
			return;
		} else {
			int k = 0;
			for(int i = 0, j = 0; i < bitSet.cols.length && j < this.cols.length; ) {
				if(bitSet.cols[i] == this.cols[j]) {
					newBitsTemp[k] = bitSet.bits[i] & this.bits[j];
					if(newBitsTemp[k] != 0) {
						newColsTemp[k] = bitSet.cols[i];
						k++;
					}
					i++;
					j++;
				} else if(bitSet.cols[i] > this.cols[j])
					j++;
				else
					i++;
			}
			long[] newCols = new long[k];
			long[] newBits = new long[k];
			System.arraycopy(newColsTemp, 0, newCols, 0, k);
			System.arraycopy(newBitsTemp, 0, newBits, 0, k);
			this.cols = newCols;
			this.bits = newBits;
		}
		//this.wlen = (int) (this.cols[this.cols.length - 1] * LENGTH + Long.lowestOneBit(this.bits[this.bits.length - 1]));
	}

	public void or(BitDekkSparseBitSet64 bitSet) {
		long[] newColsTemp = new long[bitSet.cols.length + this.cols.length];
		long[] newBitsTemp = new long[newColsTemp.length];
		int i = 0;
		int j = 0;
		int k = 0;
		for(; i < bitSet.cols.length && j < this.cols.length; k++) {
			if(bitSet.cols[i] < this.cols[j]) {
				newColsTemp[k] = bitSet.cols[i];
				newBitsTemp[k] = bitSet.bits[i];
				i++;
			} else if (this.cols[j] < bitSet.cols[i]) {
				newColsTemp[k] = this.cols[j];
				newBitsTemp[k] = this.bits[j];
				j++;
			} else {
				newColsTemp[k] = this.cols[j];
				newBitsTemp[k] = this.bits[j] | bitSet.bits[i];
				i++;
				j++;
			}
		}
		for(; i < bitSet.cols.length; i++, k++) {
			newColsTemp[k] = bitSet.cols[i];
			newBitsTemp[k] = bitSet.bits[i];
		}
		for(; j < this.cols.length; j++, k++) {
			newColsTemp[k] = this.cols[j];
			newBitsTemp[k] = this.bits[j];
		}
		long[] newCols = new long[k];
		long[] newBits = new long[k];
		System.arraycopy(newColsTemp, 0, newCols, 0, k);
		System.arraycopy(newBitsTemp, 0, newBits, 0, k);
		this.cols = newCols;
		this.bits = newBits;
		//this.wlen = (int) (this.cols[this.cols.length - 1] * LENGTH + Long.lowestOneBit(this.bits[this.bits.length - 1]));
	}

	public void andNot(BitDekkSparseBitSet64 bitSet) {
		long[] newColsTemp = new long[this.cols.length];
		long[] newBitsTemp = new long[newColsTemp.length];
		int j = 0;
		int k = 0;
		for(int i = 0; i < bitSet.cols.length && j < this.cols.length; ) {
			if(bitSet.cols[i] == this.cols[j]) {
				newBitsTemp[k] = this.bits[j] & (~bitSet.bits[i]);
				if(newBitsTemp[k] != 0) {
					newColsTemp[k] = bitSet.cols[i];
					k++;
				}
				i++;
				j++;
			} else {
				newColsTemp[k] = this.cols[j];
				newBitsTemp[k] = this.bits[j];
				k++;
				if(bitSet.cols[i] > this.cols[j])
					j++;
				else
					i++;
			}
		}
		for(; j < this.cols.length; j++, k++) {
			newColsTemp[k] = this.cols[j];
			newBitsTemp[k] = this.bits[j];
		}
		long[] newCols = new long[k];
		long[] newBits = new long[k];
		System.arraycopy(newColsTemp, 0, newCols, 0, k);
		System.arraycopy(newBitsTemp, 0, newBits, 0, k);
		this.cols = newCols;
		this.bits = newBits;
		//this.wlen = (int) (this.cols[this.cols.length - 1] * LENGTH + Long.lowestOneBit(this.bits[this.bits.length - 1]));
	}

	@Override
	public int nextSetBit(int position) {
		int index = Arrays.binarySearch(cols, position / LENGTH);
		if(index < 0) {
			index = -index;
		} else {
			for(long x = bits[index] >> (position % LENGTH), i = (position % LENGTH); x != 0; x = x >> 1, i++)
				if((x & 1) == 1)
					return (int) (cols[index] * LENGTH + i);
			index++;
		}
		for(; index < cols.length; index++)
			for(long x = bits[index], i = 0; x != 0; x = x >> 1, i++)
				if((x & 1) == 1)
					return (int) (cols[index] * LENGTH + i);
		return -1;
	}

	@Override
	public BitDekkSparseBitSet64 clone() {
		BitDekkSparseBitSet64 bitDekkSparseBitSet = new BitDekkSparseBitSet64();
		bitDekkSparseBitSet.cols = new long[this.cols.length];
		bitDekkSparseBitSet.bits = new long[this.bits.length];
		System.arraycopy(this.cols, 0, bitDekkSparseBitSet.cols, 0, this.cols.length);
		System.arraycopy(this.bits, 0, bitDekkSparseBitSet.bits, 0, this.bits.length);
		return bitDekkSparseBitSet;
	}
	public String toString() {
		StringBuffer buffer = new StringBuffer("<");
		for(Integer i : this)
			buffer.append(i + ",");
		return buffer.deleteCharAt(buffer.length() - 1).toString();
	}
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof BitDekkSparseBitSet64) || ((BitDekkSparseBitSet64)o).cols.length != this.cols.length)
			return false;
		else {
			BitDekkSparseBitSet64 obj = (BitDekkSparseBitSet64)o;
			for(int i = 0; i < this.cols.length; i++)
				if(this.bits[i] != obj.bits[i])
					return false;
			return true;
		}
	}
	@Override
	public int compareTo(IBitSet o) {
		if(o instanceof BitDekkSparseBitSet64) {
			BitDekkSparseBitSet64 other = (BitDekkSparseBitSet64)o;
			int min = Math.min(other.cols.length, cols.length);
			long[] otherCols = other.cols;
			long[] otherBits = other.bits;
			if(min != 0) {
				for(int i = 0; i < min; i++) {
					if(otherCols[i] < cols[i])
						return -1;
					else if(cols[i] < otherCols[i])
						return 1;
					else if (otherBits[i] != bits[i])
						/*for(int j = 0 ; j > - 1; j++) {
							int temp = other.nextSetBit(j);
							int x = nextSetBit(j) - temp;
							if(x != 0)
								return -x;
							else
								j = temp + 1;
						}*/
						return (int) (bits[i] - otherBits[i]);
				}
			}
			return cols.length - otherCols.length;
		} else
			  throw new RuntimeException("BitDekkSparseBitSet cannot act on other bitset implementations");
	}
}