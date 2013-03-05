package org.bitdekk.helper.sql.grammar;

public class HavingExpression {

	private String lhs;
	private String rhs;
	private String logicOperator;

	public HavingExpression(String lhs, String logicOperator, String rhs) {
		this.lhs = lhs;
		this.logicOperator = logicOperator; 
		this.rhs = rhs;
	}
	public String getLhs() {
		return lhs;
	}
	public String getRhs() {
		return rhs;
	}
	public String getLogicOperator() {
		return logicOperator;
	}
}