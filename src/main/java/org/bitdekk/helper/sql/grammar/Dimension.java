package org.bitdekk.helper.sql.grammar;

public class Dimension implements IColumn {
	private String label;
	private String name;
	public Dimension(String name, String label) {
		this.label = label;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	@Override
	public String getLabel() {
		return label;
	}
}