package org.bitdekk;

import java.util.HashMap;

import org.bitdekk.aggregation.impl.AvgAggregation;
import org.bitdekk.aggregation.impl.SumAggregation;
import org.bitdekk.exception.InvalidBitDekkExpressionException;
import org.bitdekk.util.OpenBitSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.Value;
import com.google.visualization.datasource.datatable.value.ValueType;

@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class DataLayerTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private DataLayer dataLayer;

	@Test(dataProvider="initializeDimensionsDataProvider")
	public void initializeDimensionValues(HashMap<String, Integer> hashMap) {
		dataLayer.initializeDimensionValues(hashMap);
		for(String i : hashMap.keySet())
			Assert.assertEquals(dataLayer.getDimensionId(i), hashMap.get(i).intValue());
	}
	@DataProvider
	public Object[][] initializeDimensionsDataProvider() {
		HashMap<String, Integer> emptyHashMap = new HashMap<String, Integer>();
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("S1",0);
		hashMap.put("S2",1);
		hashMap.put("P1",2);
		hashMap.put("P2",3);
		
		dataLayer.initializeDimensionValues(hashMap);
		return new Object[][]{{emptyHashMap},{hashMap}};
	}
	@Test
	public void testAggregations() throws TypeMismatchException, InvalidBitDekkExpressionException {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("S1",0);
		hashMap.put("S2",1);
		hashMap.put("P1",2);
		hashMap.put("P2",3);
		
		dataLayer.initializeDimensionValues(hashMap);
		
		DataTable dataTable = new DataTable();
		dataTable.addColumn(new ColumnDescription("1", ValueType.TEXT, "Supplier"));
		dataTable.addColumn(new ColumnDescription("2", ValueType.TEXT, "Product"));
		dataTable.addColumn(new ColumnDescription("3", ValueType.NUMBER, "Volume"));
		TableRow row = new TableRow();
		row.addCell("S1");
		row.addCell("P1");
		row.addCell(100);
		dataTable.addRow(row);
		row = new TableRow();
		row.addCell("S1");
		row.addCell("P2");
		row.addCell(200);
		dataTable.addRow(row);
		row = new TableRow();
		row.addCell("S2");
		row.addCell("P1");
		row.addCell(300);
		dataTable.addRow(row);
		row = new TableRow();
		row.addCell("S2");
		row.addCell("P2");
		row.addCell(400);
		dataTable.addRow(row);
		dataLayer.initializeTable("VolumeTable", dataTable);
		Assert.assertEquals(300.0, (dataLayer.aggregate(new SumAggregation(), "VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, new String[]{"Volume"}))
			, 0.00000001);
		Assert.assertEquals(100.0, (dataLayer.aggregate(new SumAggregation(), "VolumeTable",  new String[]{"S1","P1"}, new String[]{"S1","P1","P2","S2"}, new String[]{"Volume"}))
			, 0.00000001);
		Assert.assertEquals(150.0, (dataLayer.aggregate(new AvgAggregation(), "VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, new String[]{"Volume"}))
			, 0.00000001);
		Assert.assertEquals(600, (dataLayer.aggregate("VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, "SUM(2 * Volume)"))
			, 0.00000001);
		Assert.assertEquals(302.0, (dataLayer.aggregate("VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, "(SUM(2 * Volume) / COUNT(Volume)) + 2"))
			, 0.00000001);
		Assert.assertEquals(602.0, (dataLayer.aggregate("VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, "2 * SUM(Volume) + 2"))
			, 0.00000001);
		Assert.assertEquals(Double.isNaN((dataLayer.aggregate(new SumAggregation(), "VolumeTable", new String[]{"S1","P1"}, new String[]{"S1","P2"}
			, new String[]{"Volume"}))), true);
		Assert.assertEquals(Double.isNaN((dataLayer.aggregate(new AvgAggregation(), "VolumeTable", new String[]{"S1","P1"}, new String[]{"S1","P2"}
			, new String[]{"Volume"}))), true);
	}
	@Test(dependsOnMethods="testAggregations", expectedExceptions={InvalidBitDekkExpressionException.class})
	public void testIllegalGrammar() throws InvalidBitDekkExpressionException {
		Assert.assertEquals(302.0, (dataLayer.aggregate("VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, "AVG((2 * Volume) + 2")), 0.00000001);
	}
	@Test(dependsOnMethods="testAggregations", expectedExceptions={IllegalArgumentException.class})
	public void testAbsentDimension() throws TypeMismatchException, InvalidBitDekkExpressionException {
		DataTable dataTable = new DataTable();
		dataLayer.initializeTable("VolumeTable", dataTable);
		Assert.assertEquals(Double.NaN, (dataLayer.aggregate(new SumAggregation(), "VolumeTable", new String[]{"S1","P3"}, new String[]{"S1","P2"}, new String[]{"Volume"})));
	}
	@Test
	public void testNullDimension() throws TypeMismatchException {
		DataTable dataTable = new DataTable();
		dataTable.addColumn(new ColumnDescription("1", ValueType.TEXT, "Supplier"));
		dataTable.addColumn(new ColumnDescription("2", ValueType.TEXT, "Product"));
		dataTable.addColumn(new ColumnDescription("3", ValueType.NUMBER, "Volume"));
		TableRow row = new TableRow();
		row.addCell("S1");
		row.addCell("P1");
		row.addCell(100);
		dataTable.addRow(row);
		row = new TableRow();
		row.addCell("S1");
		row.addCell("P2");
		row.addCell(200);
		dataTable.addRow(row);
		row = new TableRow();
		row.addCell("S1");
		row.addCell(Value.getNullValueFromValueType(ValueType.TEXT));
		row.addCell(300);
		dataTable.addRow(row);
		row = new TableRow();
		row.addCell("S2");
		row.addCell("P1");
		row.addCell(300);
		dataTable.addRow(row);
		row = new TableRow();
		row.addCell("S2");
		row.addCell("P2");
		row.addCell(400);
		dataTable.addRow(row);
		dataLayer.initializeTable("VolumeTableNullDimensions", dataTable);
		Assert.assertEquals(600.0, (dataLayer.aggregate(new SumAggregation(), "VolumeTableNullDimensions",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}
			, new String[]{"Volume"})), 0.00000001);
	}
	@Test
	public void testNullMeasure() throws TypeMismatchException {
		DataTable dataTable = new DataTable();
		dataTable.addColumn(new ColumnDescription("1", ValueType.TEXT, "Supplier"));
		dataTable.addColumn(new ColumnDescription("2", ValueType.TEXT, "Product"));
		dataTable.addColumn(new ColumnDescription("3", ValueType.NUMBER, "Volume"));
		TableRow row = new TableRow();
		row.addCell("S1");
		row.addCell("P1");
		row.addCell(100);
		dataTable.addRow(row);
		row = new TableRow();
		row.addCell("S1");
		row.addCell("P2");
		row.addCell(Value.getNullValueFromValueType(ValueType.NUMBER));
		dataTable.addRow(row);
		row = new TableRow();
		row.addCell("S2");
		row.addCell("P1");
		row.addCell(300);
		dataTable.addRow(row);
		row = new TableRow();
		row.addCell("S2");
		row.addCell("P2");
		row.addCell(400);
		dataTable.addRow(row);
		dataLayer.initializeTable("VolumeTableNullMeasure", dataTable);
		Assert.assertEquals(100.0, (dataLayer.aggregate(new SumAggregation(), "VolumeTableNullMeasure",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}
			, new String[]{"Volume"})), 0.00000001);
	}
	@Test(dependsOnMethods="testAggregations")
	public void getBitSetTest() {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("S1",0);
		hashMap.put("S2",1);
		hashMap.put("P1",2);
		hashMap.put("P2",3);
		
		dataLayer.initializeDimensionValues(hashMap);
		OpenBitSet openBitSet = new OpenBitSet();
		openBitSet.set(0);
		openBitSet.set(2);
		Assert.assertEquals(dataLayer.getBitSet(new String[] {"S1", "P1"}), openBitSet);
		Assert.assertEquals(dataLayer.getBitSet(new String[] {}), new OpenBitSet());
	}
}