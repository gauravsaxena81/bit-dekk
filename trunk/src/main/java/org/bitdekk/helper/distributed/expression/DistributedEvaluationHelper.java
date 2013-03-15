package org.bitdekk.helper.distributed.expression;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.bitdekk.api.IBitSet;
import org.bitdekk.api.IEvaluation;
import org.bitdekk.distributed.cluster.ClusterConfig;
import org.bitdekk.distributed.util.BitDekkDistributedUtil;
import org.bitdekk.helper.MeasureHelper;
import org.bitdekk.helper.distributed.expression.model.FunctionExpression;
import org.bitdekk.helper.expression.model.GroupedMeasureExpression;
import org.bitdekk.model.DataRow;
import org.bitdekk.server.model.Request;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class DistributedEvaluationHelper implements IEvaluation {
	private MeasureHelper measureHelper;
	private Integer timeout = Integer.MAX_VALUE;
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	public MeasureHelper getMeasureHelper() {
		return measureHelper;
	}
	public void setMeasureHelper(MeasureHelper measureHelper) {
		this.measureHelper = measureHelper;
	}
	@Override
	public double getMeasureExpressionValue(ArrayList<Object> measureExpressionTokens, int pos, DataRow row, String tableName, IBitSet viewBitSet
			, IBitSet filterBitSet) {
		if(measureExpressionTokens.get(pos).equals("+"))
			return add(measureExpressionTokens, pos + 1, row, tableName, viewBitSet, filterBitSet);
		else if(measureExpressionTokens.get(pos).equals("-"))
			return subtract(measureExpressionTokens, ++pos, row, tableName, viewBitSet, filterBitSet);
		else if(measureExpressionTokens.get(pos).equals("*"))
			return multiply(measureExpressionTokens, ++pos, row, tableName, viewBitSet, filterBitSet);
		else if(measureExpressionTokens.get(pos).equals("/"))
			return divide(measureExpressionTokens, ++pos, row, tableName, viewBitSet, filterBitSet);
		else if(measureExpressionTokens.get(pos) instanceof Double)
			return (Double)measureExpressionTokens.get(pos);
		else if(measureExpressionTokens.get(pos) instanceof FunctionExpression)
			return aggregate(((FunctionExpression)measureExpressionTokens.get(pos)), tableName, viewBitSet, filterBitSet);
		else
			return row.getMeasureValues()[measureHelper.getTable(tableName).getMeasureIndexMap().get(measureExpressionTokens.get(pos))];
	}
	private double aggregate(final FunctionExpression functionExpression, final String tableName, final IBitSet viewBitSet
			, final IBitSet filterBitSet) {
		final CountDownLatch doneSignal = new CountDownLatch(ClusterConfig.getInstance().getClusterSize());
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
			Request request = new Request();
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
		}
		if(success)
			return functionExpression.getAggregation().getValue();
		else
			throw new RuntimeException("Timeout occured while waiting for response. One or more nodes may be down.");
	}
	private double divide(ArrayList<Object> measureExpressionTokens, int pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet) {
		return getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet) 
				/ getMeasureExpressionValue(measureExpressionTokens, pos + 1, row, tableName, viewBitSet, filterBitSet);
	}
	private double multiply(ArrayList<Object> measureExpressionTokens, int pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet) {
		return getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet) 
				* getMeasureExpressionValue(measureExpressionTokens, pos + 1, row, tableName, viewBitSet, filterBitSet);
	}
	private double subtract(ArrayList<Object> measureExpressionTokens, int pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet) {
		return getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet) 
				- getMeasureExpressionValue(measureExpressionTokens, pos + 1, row, tableName, viewBitSet, filterBitSet);
	}
	private double add(ArrayList<Object> measureExpressionTokens, int pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet) {
		return getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet) 
				+ getMeasureExpressionValue(measureExpressionTokens, pos + 1, row, tableName, viewBitSet, filterBitSet);
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
