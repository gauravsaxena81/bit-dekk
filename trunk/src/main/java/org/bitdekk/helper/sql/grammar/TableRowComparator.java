package org.bitdekk.helper.sql.grammar;

import java.util.ArrayList;
import java.util.Comparator;

import org.bitdekk.helper.sql.grammar.model.OrderColumn;

import com.google.visualization.datasource.datatable.TableRow;

public class TableRowComparator implements Comparator<TableRow> {
	private ArrayList<OrderColumn> columns;
	
	public TableRowComparator(ArrayList<OrderColumn> columns) {
		this.columns = columns;
	}

	@Override
	public int compare(TableRow arg0, TableRow arg1) {
		for(OrderColumn i : columns) {
			int compareTo = arg0.getCell(i.getColumnNumner()).getValue().compareTo(arg1.getCell(i.getColumnNumner()).getValue());
			if(compareTo != 0)
				return compareTo * (i.isAsc() ? 1 : -1);
		}
		return 0;
	}
}