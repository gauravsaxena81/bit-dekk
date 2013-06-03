package org.bitdekk.distributed.server.model;

import org.bitdekk.api.IBitSet;

public class AssociateRuleRequest {
	private String dimension;
	private String scenarioDimensionValue;
	private IBitSet ruleBitSet;
	private double[] factor;
	
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getScenarioDimensionValue() {
		return scenarioDimensionValue;
	}
	public void setScenarioDimensionValue(String scenarioDimensionValue) {
		this.scenarioDimensionValue = scenarioDimensionValue;
	}
	public IBitSet getRuleBitSet() {
		return ruleBitSet;
	}
	public void setRuleBitSet(IBitSet ruleBitSet) {
		this.ruleBitSet = ruleBitSet;
	}
	public double[] getFactor() {
		return factor;
	}
	public void setFactor(double[] factor) {
		this.factor = factor;
	}
}
