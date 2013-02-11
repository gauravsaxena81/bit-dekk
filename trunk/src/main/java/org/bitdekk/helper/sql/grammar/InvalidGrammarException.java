package org.bitdekk.helper.sql.grammar;

@SuppressWarnings("serial")
public class InvalidGrammarException extends RuntimeException {

	public InvalidGrammarException(String message) {
		super(message);
	}
}