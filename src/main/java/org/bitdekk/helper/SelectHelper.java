package org.bitdekk.helper;

import org.bitdekk.api.IBitSet;
import org.bitdekk.api.IEvaluation;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.DataTable;

public class SelectHelper {
	private IEvaluation evaluator;

	public IEvaluation getEvaluator() {
		return evaluator;
	}
	public void setEvaluator(IEvaluation evaluator) {
		this.evaluator = evaluator;
	}
	public DataTable select(String tableName, IBitSet filterbitSet, String... columnNames) {
		try {
			return evaluator.select(tableName, filterbitSet, columnNames);
		} catch (TypeMismatchException e) {
			e.printStackTrace();
			return null;
		}
	}
}
