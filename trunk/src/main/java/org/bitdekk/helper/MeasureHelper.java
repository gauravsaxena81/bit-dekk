package org.bitdekk.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.bitdekk.model.DataRow;
import org.bitdekk.model.Table;

import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableCell;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.NumberValue;
import com.google.visualization.datasource.datatable.value.TextValue;
import com.google.visualization.datasource.datatable.value.ValueType;


public class MeasureHelper {
	
	private DimensionHelper dimensionHelper;
	private DataHelper dataHelper;
	
	
	public DimensionHelper getDimensionHelper() {
		return dimensionHelper;
	}

	public void setDimensionHelper(DimensionHelper dimensionHelper) {
		this.dimensionHelper = dimensionHelper;
	}

	public DataHelper getDataHelper() {
		return dataHelper;
	}

	public void setDataHelper(DataHelper dataHelper) {
		this.dataHelper = dataHelper;
	}

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

	public void intializeTable(String tableName, ResultSet resultSet) throws SQLException {
		Table table = new Table();
		int measureIndex = 0;
		int dimensionIndex = 0;
		for(int i = 0, columns = resultSet.getMetaData().getColumnCount(); i < columns; i++) {
			switch(resultSet.getMetaData().getColumnType(i)) {
				case Types.BIGINT:
				case Types.DECIMAL:
				case Types.DOUBLE:
				case Types.FLOAT:
				case Types.INTEGER:
				case Types.REAL:
				case Types.SMALLINT:
				case Types.TINYINT:
					table.getMeasureIndexMap().put(resultSet.getMetaData().getColumnLabel(i), measureIndex++);
					break;
				default:
					table.getDimensionIndexMap().put(resultSet.getMetaData().getColumnLabel(i), dimensionIndex++);
			}
		}
		while(resultSet.next()) {
			DataRow dataRow = new DataRow(table.getMeasureIndexMap().size());
			table.getRows().add(dataRow);
			int index = 0;
			for(int i = 0, columns = resultSet.getMetaData().getColumnCount(); i < columns; i++) {	
				switch(resultSet.getMetaData().getColumnType(i)) {
					case Types.BIGINT:
					case Types.DECIMAL:
					case Types.DOUBLE:
					case Types.FLOAT:
					case Types.INTEGER:
					case Types.REAL:
					case Types.SMALLINT:
					case Types.TINYINT:
						dataRow.getMeasureValues()[index++] = resultSet.getDouble(i);
					default:
						dataRow.getMeasureQuery().set(dimensionHelper.getId(resultSet.getString(i)));
				}
			}
		}
		dataHelper.getTableMap().put(tableName, table);
	}
}