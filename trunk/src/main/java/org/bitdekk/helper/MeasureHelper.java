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
package org.bitdekk.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Comparator;

import org.bitdekk.api.IBitSet;
import org.bitdekk.model.DataRow;
import org.bitdekk.model.Table;

import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableCell;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.NumberValue;
import com.google.visualization.datasource.datatable.value.ValueType;


/**
 * Contains methods for processing of measures
 * @author gaurav.saxena
 *
 */
public class MeasureHelper {
	
	private DimensionValueHelper dimensionValueHelper;
	private DataHelper dataHelper;
	
	public DimensionValueHelper getDimensionValueHelper() {
		return dimensionValueHelper;
	}
	public void setDimensionValueHelper(DimensionValueHelper dimensionValueHelper) {
		this.dimensionValueHelper = dimensionValueHelper;
	}
	public DataHelper getDataHelper() {
		return dataHelper;
	}
	public void setDataHelper(DataHelper dataHelper) {
		this.dataHelper = dataHelper;
	}
	
	public void intializeTable(String dataTableName, DataTable dataTable) {
		Table table = new Table(dataTable.getNumberOfRows());
		int measureIndex = 0;
		int dimensionIndex = 0;
		for(ColumnDescription i : dataTable.getColumnDescriptions()) {
			if(i.getType().equals(ValueType.NUMBER))
				table.getMeasureIndexMap().put(i.getLabel(), measureIndex++);
			else
				table.getDimensionIndexMap().put(i.getLabel(), dimensionIndex++);
		}
		int rowIndex = 0;
		for(TableRow i : dataTable.getRows()) {
			DataRow dataRow = new DataRow(table.getMeasureIndexMap().size());
			table.getRows()[rowIndex++] = dataRow;
			int index = 0;
			for(int k = 0; k < i.getCells().size(); k++) {
				TableCell j = i.getCells().get(k);
				if(j.getType().equals(ValueType.TEXT) || j.getType().equals(ValueType.DATE)) {
					if(!j.isNull())
						dataRow.getMeasureQuery().set(dimensionValueHelper.getId(dataTable.getColumnDescription(k).getLabel(), j.getValue().toString()));
				} else if(j.getType().equals(ValueType.NUMBER)) {
					if(j.isNull())
						dataRow.getMeasureValues()[index++] = Double.NaN;
					else
						dataRow.getMeasureValues()[index++] = ((NumberValue)j.getValue()).getValue();
				}
			}
		}
		Arrays.sort(table.getRows(), new Comparator<DataRow>() {
			@Override
			public int compare(DataRow o1, DataRow o2) {
				return ((Comparable<IBitSet>) o1.getMeasureQuery()).compareTo(o2.getMeasureQuery());
			}
		});
		dataHelper.getTableMap().put(dataTableName, table);
	}

	public Table getTable(String tableName) {
		return dataHelper.getTableMap().get(tableName);
	}

	/**
	 * @deprecated under construction, not recommended for use
	 * @param tableName
	 * @param resultSet
	 * @throws SQLException
	 */
	public void intializeTable(String tableName, ResultSet resultSet) throws SQLException {
		Table table = new Table(100);
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
		int rowIndex = 0;
		while(resultSet.next()) {
			DataRow dataRow = new DataRow(table.getMeasureIndexMap().size());
			table.getRows()[rowIndex] = (dataRow);
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
						dataRow.getMeasureQuery().set(dimensionValueHelper.getId(resultSet.getMetaData().getColumnLabel(i), resultSet.getString(i)));
				}
			}
		}
		dataHelper.getTableMap().put(tableName, table);
	}
}