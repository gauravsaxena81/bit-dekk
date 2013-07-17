package org.bitdekk.util;

import org.bitdekk.api.IBitSet;

public class BitDekkUtil {
	public static enum OPERATORS {
		PLUS,
		MINUS,
		DIVIDE,
		MULTIPLY,
		AGGREGATION,
		NUMBER,
		CELL_VALUE;
	}
	public static String generateDimensionValueString(String dimension, String dimensionValue) {
		return dimension + "-" + dimensionValue;
	}

	public static IBitSet newBitSet() {
		return new OpenBitSet();
	}
	/*public static IBitSet newBitSet() {
		return new JavaEwahBitSet();
	}*/
}
