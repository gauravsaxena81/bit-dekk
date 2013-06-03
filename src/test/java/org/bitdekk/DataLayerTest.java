package org.bitdekk;

import org.bitdekk.aggregation.impl.AvgAggregation;
import org.bitdekk.aggregation.impl.SumAggregation;
import org.bitdekk.api.IBitSet;
import org.bitdekk.exception.InvalidBitDekkExpressionException;
import org.bitdekk.model.DimensionValue;
import org.bitdekk.util.BitDekkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
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

	@Test
	public void testAggregations() throws TypeMismatchException, InvalidBitDekkExpressionException {
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
		Assert.assertEquals(300.0, (dataLayer.aggregate(new SumAggregation(), "VolumeTable",  new DimensionValue[]{new DimensionValue("Supplier","S1")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Supplier","S2")}, new String[]{"Volume"}))
			, 0.00000001);
		Assert.assertEquals(100.0, (dataLayer.aggregate(new SumAggregation(), "VolumeTable",  new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P1")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Supplier","S2")}, new String[]{"Volume"}))
			, 0.00000001);
		Assert.assertEquals(150.0, (dataLayer.aggregate(new AvgAggregation(), "VolumeTable",  new DimensionValue[]{new DimensionValue("Supplier","S1")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Supplier","S2")}, new String[]{"Volume"}))
			, 0.00000001);
		Assert.assertEquals(600, (dataLayer.aggregate("VolumeTable",  new DimensionValue[]{new DimensionValue("Supplier","S1")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Supplier","S2")}, "SUM(2 * Volume)"))
			, 0.00000001);
		Assert.assertEquals(302.0, (dataLayer.aggregate("VolumeTable",  new DimensionValue[]{new DimensionValue("Supplier","S1")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Supplier","S2")}, "(SUM(2 * Volume) / COUNT(Volume)) + 2"))
			, 0.00000001);
		Assert.assertEquals(602.0, (dataLayer.aggregate("VolumeTable",  new DimensionValue[]{new DimensionValue("Supplier","S1")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Supplier","S2")}, "2 * SUM(Volume) + 2"))
			, 0.00000001);
		Assert.assertEquals(Double.isNaN((dataLayer.aggregate(new SumAggregation(), "VolumeTable", new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P1")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P2")}
			, new String[]{"Volume"}))), true);
		Assert.assertEquals(Double.isNaN((dataLayer.aggregate(new AvgAggregation(), "VolumeTable", new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P1")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P2")}
			, new String[]{"Volume"}))), true);
	}
	@Test(dependsOnMethods="testAggregations", expectedExceptions={InvalidBitDekkExpressionException.class})
	public void testIllegalGrammar() throws InvalidBitDekkExpressionException {
		Assert.assertEquals(302.0, (dataLayer.aggregate("VolumeTable",  new DimensionValue[]{new DimensionValue("Supplier","S1")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Supplier","S2")}, "AVG((2 * Volume) + 2")), 0.00000001);
	}
	@Test(dependsOnMethods="testAggregations", expectedExceptions={IllegalArgumentException.class})
	public void testAbsentDimension() throws TypeMismatchException, InvalidBitDekkExpressionException {
		DataTable dataTable = new DataTable();
		dataLayer.initializeTable("VolumeTable", dataTable);
		Assert.assertEquals(Double.NaN, (dataLayer.aggregate(new SumAggregation(), "VolumeTable", new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P3")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P2")}, new String[]{"Volume"})));
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
		Assert.assertEquals(600.0, (dataLayer.aggregate(new SumAggregation(), "VolumeTableNullDimensions",  new DimensionValue[]{new DimensionValue("Supplier","S1")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Supplier","S2")}
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
		Assert.assertEquals(100.0, (dataLayer.aggregate(new SumAggregation(), "VolumeTableNullMeasure",  new DimensionValue[]{new DimensionValue("Supplier","S1")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Supplier","S2")}
			, new String[]{"Volume"})), 0.00000001);
	}
	@Test(dependsOnMethods="testAggregations")
	public void getBitSetTest() {
		IBitSet openBitSet = BitDekkUtil.newBitSet();
		openBitSet.set(dataLayer.getDimensionId("Supplier","S1"));
		openBitSet.set(dataLayer.getDimensionId("Product","P1"));
		Assert.assertEquals(dataLayer.getBitSet(new DimensionValue[] {new DimensionValue("Supplier","S1"), new DimensionValue("Product","P1")}), openBitSet);
		Assert.assertEquals(dataLayer.getBitSet(new DimensionValue[] {}), BitDekkUtil.newBitSet());
	}
}