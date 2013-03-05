package org.bitdekk.api;

import java.util.ArrayList;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.bitdekk.helper.expression.model.GroupedMeasureExpression;
import org.bitdekk.model.DataRow;

public interface IEvaluation {
	double getMeasureExpressionValue(ArrayList<Object> measureExpressionTokens,	int pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet);

	Lexer getLexer(String measureExpression);

	void parse(CommonTokenStream tokens, GroupedMeasureExpression gme) throws RecognitionException;
}
