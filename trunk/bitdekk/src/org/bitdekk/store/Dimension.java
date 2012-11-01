package org.bitdekk.store;

public class Dimension {
	private String name;
	private String displayName;//Let this go to the UI
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
