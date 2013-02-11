package org.bitdekk.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableCell;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.NumberValue;
import com.google.visualization.datasource.datatable.value.TextValue;
import com.google.visualization.datasource.datatable.value.ValueType;
import org.bitdekk.model.DataRow;
import org.bitdekk.model.Table;

@Component
public class MeasureHelper {
	@Autowired
	private DimensionHelper dimensionHelper;
	@Autowired
	private DataHelper dataHelper;
	
	public void intializeTable(String dataTableName, DataTable dataTable) {
		Table table = new Table();
		int measureIndex = 0;
		int dimensionIndex = 0;
		for(ColumnDescription i : dataTable.getColumnDescriptions()) {
			if(i.getType().equals(ValueType.NUMBER))
				table.getMeasureIndexMap().put(i.getLabel(), measureIndex++);
			else
				table.getDimensionIndexMap().put(i.getLabel(), dimensionIndex++);
		}
		for(TableRow i : dataTable.getRows()) {
			DataRow dataRow = new DataRow(table.getMeasureIndexMap().size());
			table.getRows().add(dataRow);
			int index = 0;
			for(TableCell j : i.getCells()) {
				if(j.getType().equals(ValueType.TEXT))
					dataRow.getMeasureQuery().set(dimensionHelper.getId(((TextValue)j.getValue()).getValue()));
				else if(j.getType().equals(ValueType.NUMBER))
					dataRow.getMeasureValues()[index++] = ((NumberValue)j.getValue()).getValue();
			}
		}
		dataHelper.getTableMap().put(dataTableName, table);
	}

	public Table getTable(String tableName) {
		return dataHelper.getTableMap().get(tableName);
	}
}