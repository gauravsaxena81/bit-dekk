package org.bitdekk.helper.sql.grammar.model;

public class OrderColumn {
	private int columnNumner;
	private boolean asc = true;
	public OrderColumn(int columnNumner, boolean asc) {
		this.columnNumner = columnNumner;
		this.asc = asc;
	}
	public int getColumnNumner() {
		return columnNumner;
	}
	public void setColumnNumner(int columnNumner) {
		this.columnNumner = columnNumner;
	}
	public boolean isAsc() {
		return asc;
	}
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
}
