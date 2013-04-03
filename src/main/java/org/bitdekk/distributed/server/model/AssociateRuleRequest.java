package org.bitdekk.distributed.server.model;

import org.bitdekk.api.IBitSet;

public class AssociateRuleRequest {
	private int id;
	private IBitSet ruleBitSet;
	private double[] factor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
