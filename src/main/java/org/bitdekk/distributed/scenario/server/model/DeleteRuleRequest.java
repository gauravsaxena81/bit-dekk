package org.bitdekk.distributed.scenario.server.model;

import org.bitdekk.api.IBitSet;

public class DeleteRuleRequest {
	private int id;
	private IBitSet key;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public IBitSet getKey() {
		return key;
	}
	public void setKey(IBitSet key) {
		this.key = key;
	}
}
