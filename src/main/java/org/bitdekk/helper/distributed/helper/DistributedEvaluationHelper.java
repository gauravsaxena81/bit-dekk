/*
 * Copyright 2013 Contributors of bit-dekk
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.bitdekk.helper.distributed.helper;

import java.util.ArrayList;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.bitdekk.api.IBitSet;
import org.bitdekk.api.IEvaluation;
import org.bitdekk.api.Processor;
import org.bitdekk.distributed.scenario.server.model.ExpressionEvaluationRequest;
import org.bitdekk.distributed.util.BitDekkDistributedUtil;
import org.bitdekk.helper.MeasureHelper;
import org.bitdekk.helper.Position;
import org.bitdekk.helper.distributed.expression.DistributedBitdekkErrorHandlingLexer;
import org.bitdekk.helper.distributed.expression.DistributedBitdekkErrorHandlingParser;
import org.bitdekk.helper.distributed.expression.model.FunctionExpression;
import org.bitdekk.helper.expression.model.GroupedMeasureExpression;
import org.bitdekk.model.DataRow;

public class DistributedEvaluationHelper implements IEvaluation {
	private MeasureHelper measureHelper;
	private long timeout = Long.MAX_VALUE;
	public long getTimeout() {
		return timeout;
	}
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	public MeasureHelper getMeasureHelper() {
		return measureHelper;
	}
	public void setMeasureHelper(MeasureHelper measureHelper) {
		this.measureHelper = measureHelper;
	}
	@Override
	public double getMeasureExpressionValue(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet viewBitSet
			, IBitSet filterBitSet) {
		if(measureExpressionTokens.get(pos.pos).equals("+")) {
			++pos.pos;
			return add(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet);
		} else if(measureExpressionTokens.get(pos.pos).equals("-")) {
			++pos.pos;
			return subtract(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet);
		} else if(measureExpressionTokens.get(pos.pos).equals("*"))  {
			++pos.pos;
			return multiply(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet);
		} else if(measureExpressionTokens.get(pos.pos).equals("/")) {
			++pos.pos;
			return divide(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet);
		} else if(measureExpressionTokens.get(pos.pos) instanceof Double)
			return (Double)measureExpressionTokens.get(pos.pos);
		else if(measureExpressionTokens.get(pos.pos) instanceof FunctionExpression)
			return aggregate(((FunctionExpression)measureExpressionTokens.get(pos.pos)), tableName, viewBitSet, filterBitSet);
		else
			return row.getMeasureValues()[measureHelper.getTable(tableName).getMeasureIndexMap().get(measureExpressionTokens.get(pos.pos))];
	}
	private double aggregate(final FunctionExpression functionExpression, final String tableName, final IBitSet viewBitSet
			, final IBitSet filterBitSet) {
		/*final CountDownLatch doneSignal = new CountDownLatch(ClusterConfig.getInstance().getClusterSize());
		final Client client = new Client();
		BitDekkDistributedUtil.registerClasses(client.getKryo());
		client.addListener(new Listener() {
			public void received (final Connection connection, Object object) {
				if(object instanceof Double) {
					functionExpression.getAggregation().aggregate((Double)object);
					doneSignal.countDown();
				}
			}
		});
		client.start();
		try {
			client.connect(5000, ClusterConfig.getInstance().getRoot().getIp(), ClusterConfig.getInstance().getRoot().getPort());
			ExpressionEvaluationRequest request = new ExpressionEvaluationRequest();
			request.setFilterBitSet(filterBitSet);
			request.setMeasureExpression(functionExpression.getExpression());
			request.setTableName(tableName);
			request.setViewBitSet(viewBitSet);
			client.sendTCP(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean success = false;
		try {
			success = doneSignal.await(timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		ExpressionEvaluationRequest request = new ExpressionEvaluationRequest();
		request.setFilterBitSet(filterBitSet);
		request.setMeasureExpression(functionExpression.getExpression());
		request.setTableName(tableName);
		request.setViewBitSet(viewBitSet);
		if(BitDekkDistributedUtil.evaluate(timeout, request, new Processor<Double>() {
			@Override
			public void process(Double t) {
				functionExpression.getAggregation().aggregate(t);
			}
		}))
			return functionExpression.getAggregation().getValue();
		else
			throw new RuntimeException("Timeout occured while waiting for response. One or more nodes may be down.");
	}
	private double divide(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet) {
		double v1 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet);
		++pos.pos;
		double v2 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet);
		return v1 / v2;
	}
	private double multiply(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet) {
		double v1 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet);
		++pos.pos;
		double v2 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet);
		return v1 * v2;
	}
	private double subtract(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet) {
		double v1 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet);
		++pos.pos;
		double v2 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet);
		return v1 - v2;
	}
	private double add(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet) {
		double v1 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet);
		++pos.pos;
		double v2 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet);
		return v1 + v2;
	}
	/*public boolean measureCondition(ArrayList<ArrayList<Object>> measureConditionExpressions, DataRow row, HashMap<String, Integer> measureIndexMap) {
		boolean returnValue = true;
		for(ArrayList<Object> measureConditionExpression : measureConditionExpressions) {
			if(measureConditionExpression.get(0).equals("="))
				returnValue &= row.getMeasureValues()[measureIndexMap.get(measureConditionExpression.get(1))] 
						== getMeasureValue(measureConditionExpression, 2, row, measureIndexMap);
			else if(measureConditionExpression.get(0).equals("<>"))
				returnValue &= row.getMeasureValues()[measureIndexMap.get(measureConditionExpression.get(1))] 
						!= getMeasureValue(measureConditionExpression, 2, row, measureIndexMap);
			else if(measureConditionExpression.get(0).equals(">="))
				returnValue &= row.getMeasureValues()[measureIndexMap.get(measureConditionExpression.get(1))] 
						>= getMeasureValue(measureConditionExpression, 2, row, measureIndexMap);
			else if(measureConditionExpression.get(0).equals("<="))
				returnValue &= row.getMeasureValues()[measureIndexMap.get(measureConditionExpression.get(1))] 
						<= getMeasureValue(measureConditionExpression, 2, row, measureIndexMap);
			else if(measureConditionExpression.get(0).equals("<"))
				returnValue &= row.getMeasureValues()[measureIndexMap.get(measureConditionExpression.get(1))] 
						< getMeasureValue(measureConditionExpression, 2, row, measureIndexMap);
			else if(measureConditionExpression.get(0).equals(">"))
				returnValue &= row.getMeasureValues()[measureIndexMap.get(measureConditionExpression.get(1))] 
						> getMeasureValue(measureConditionExpression, 2, row, measureIndexMap);
			else if(measureConditionExpression.get(0).equals("<>"))
				returnValue &= row.getMeasureValues()[measureIndexMap.get(measureConditionExpression.get(1))] 
						> getMeasureValue(measureConditionExpression, 2, row, measureIndexMap);
			else
				throw new IllegalArgumentException("Unidentified operator " + measureConditionExpressions.get(0));
		}
		return returnValue;
	}*/
	@Override
	public Lexer getLexer(String measureExpression) {
		return new DistributedBitdekkErrorHandlingLexer(new ANTLRStringStream(measureExpression));
	}
	@Override
	public void parse(CommonTokenStream tokens, GroupedMeasureExpression gme) throws RecognitionException {
		new DistributedBitdekkErrorHandlingParser(tokens, gme).stat();
	}
}
