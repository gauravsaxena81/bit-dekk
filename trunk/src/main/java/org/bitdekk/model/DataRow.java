package org.bitdekk.model;

import org.bitdekk.util.OpenBitSet;

public class DataRow {
	private OpenBitSet measureQuery = new OpenBitSet();
	private double[] measureValues;
	public DataRow(int measureNumber) {
		measureValues = new double[measureNumber];
	}
	public OpenBitSet getMeasureQuery() {
		return measureQuery;
	}
	public double[] getMeasureValues() {
		return measureValues;
	}
}