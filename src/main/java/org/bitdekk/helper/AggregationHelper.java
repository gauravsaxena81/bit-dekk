package org.bitdekk.helper;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.helper.expression.BitdekkErrorHandlingLexer;
import org.bitdekk.helper.expression.BitdekkErrorHandlingParser;
import org.bitdekk.helper.expression.EvaluationUtil;
import org.bitdekk.helper.expression.GroupedMeasureExpression;
import org.bitdekk.model.DataRow;
import org.bitdekk.model.Table;
import org.bitdekk.util.OpenBitSet;
import org.springframework.stereotype.Component;

@Component
public class AggregationHelper {
	public double aggregate(IAggregation aggregation, Table table, OpenBitSet viewBitSet, OpenBitSet filterBitSet, String[] measureNames)  {
		double[] measureArray = new double[measureNames.length];
		for(DataRow i : table.getRows()) {
			if(filterBitSet.contains(i.getMeasureQuery()) && i.getMeasureQuery().contains(viewBitSet)) {
				for(int j = 0; j < measureNames.length; j++)
					measureArray[j] = i.getMeasureValues()[table.getMeasureIndexMap().get(measureNames[j])];
				aggregation.aggregate(measureArray);
			}
		}
		return aggregation.getValue();
	}

	public double aggregate(Table table, OpenBitSet viewBitSet, OpenBitSet filterBitSet, String measureExpression) throws InvalidBitDekkExpressionException{
		GroupedMeasureExpression gme = new GroupedMeasureExpression();
		BitdekkErrorHandlingLexer lexer = new BitdekkErrorHandlingLexer(new ANTLRStringStream(measureExpression));
		CommonTokenStream tokens = new CommonTokenStream();
		tokens.setTokenSource(lexer);
		BitdekkErrorHandlingParser parser = new BitdekkErrorHandlingParser(tokens, gme);
		try {
			parser.stat();
			return EvaluationUtil.getMeasureExpressionValue(gme.getGroupedTokens(), 0, null, table, viewBitSet, filterBitSet);
		} catch (RecognitionException e) {
			e.printStackTrace();
			throw new InvalidBitDekkExpressionException("Invalid token " + e.token + " found at " + e.line + "," + e.charPositionInLine);
		}
	}
}