package org.bitdekk.scenario;

import java.util.Arrays;

import org.bitdekk.api.IBitSet;
import org.bitdekk.model.DimensionValue;
import org.bitdekk.util.BitDekkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;

@ContextConfiguration(locations = {"classpath:applicationContext-scenario-test.xml"})
public class ScenarioTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private ScenarioDataLayer scenarioDataLayer;
	@BeforeClass
	public void initialize() throws TypeMismatchException {
		DataTable dataTable = new DataTable();
		dataTable.addColumn(new ColumnDescription("0", ValueType.TEXT, "Year"));
		dataTable.addColumn(new ColumnDescription("1", ValueType.TEXT, "Supplier"));
		dataTable.addColumn(new ColumnDescription("2", ValueType.TEXT, "Product"));
		dataTable.addColumn(new ColumnDescription("3", ValueType.NUMBER, "Volume"));
		dataTable.addColumn(new ColumnDescription("4", ValueType.NUMBER, "Cost"));
		TableRow row = new TableRow();
		row.addCell("2011");
		row.addCell("S1");
		row.addCell("P1");
		row.addCell(10);
		row.addCell(1.0);
		dataTable.addRow(row);
		row = new TableRow();
		row.addCell("2011");
		row.addCell("S1");
		row.addCell("P2");
		row.addCell(11);
		row.addCell(1.5);
		dataTable.addRow(row);
		row = new TableRow();
		row.addCell("2011");
		row.addCell("S2");
		row.addCell("P1");
		row.addCell(12);
		row.addCell(1.1);
		dataTable.addRow(row);
		row = new TableRow();
		row.addCell("2011");
		row.addCell("S2");
		row.addCell("P2");
		row.addCell(13);
		row.addCell(1.4);
		dataTable.addRow(row);
		System.out.println("ScenarioTest");
		scenarioDataLayer.initializeTable("VolumeTable", dataTable);
	}
	@Test
	public void dimensionTest() {
		Assert.assertEquals(scenarioDataLayer.getDimensionValueIds("Supplier"), Arrays.asList(new Integer[]{scenarioDataLayer.getDimensionId("Supplier", "S1")
				,scenarioDataLayer.getDimensionId("Supplier", "S2")}));
		Assert.assertEquals(scenarioDataLayer.getDimensionValueIds("Product"), Arrays.asList(new Integer[]{scenarioDataLayer.getDimensionId("Product", "P1")
				,scenarioDataLayer.getDimensionId("Product", "P2")}));
		Assert.assertEquals(scenarioDataLayer.getDimensionValueIds("Year"), Arrays.asList(new Integer[]{scenarioDataLayer.getDimensionId("Year", "2011")}));
	}
	@Test(dependsOnMethods="dimensionTest")
	public void scenario1stLevelTest() {
		scenarioDataLayer.createDimensionValue("Year", "2012");
		IBitSet ruleBitSet1 = BitDekkUtil.newBitSet();
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Supplier", "S1"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Product", "P1"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Product", "P2"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Year", "2011"));
		scenarioDataLayer.associateRule("Year", "2012", ruleBitSet1, new double[]{2, 1});
		IBitSet ruleBitSet2 = BitDekkUtil.newBitSet();
		ruleBitSet2.set(scenarioDataLayer.getDimensionId("Supplier", "S2"));
		ruleBitSet2.set(scenarioDataLayer.getDimensionId("Product", "P1"));
		ruleBitSet2.set(scenarioDataLayer.getDimensionId("Product", "P2"));
		ruleBitSet2.set(scenarioDataLayer.getDimensionId("Year", "2011"));
		scenarioDataLayer.associateRule("Year", "2012", ruleBitSet2, new double[]{3, 1});
		Assert.assertEquals(117, scenarioDataLayer.aggregate("VolumeTable", new DimensionValue[]{new DimensionValue("Year","2012")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Supplier","S2"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Year","2011"),new DimensionValue("Year","2012")}, "SUM(Volume)"), 0.000001);
		//Assert.assertEquals(56, scenarioDataLayer.aggregate("VolumeTable", new DimensionValue[]{new DimensionValue("Year","2012")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Supplier","S2"),new DimensionValue("Product","P1"),new DimensionValue("Year","2011"),new DimensionValue("Year","2012")}, "SUM(Volume)"), 0.000001);
		Assert.assertEquals(56, scenarioDataLayer.aggregate("VolumeTable", new DimensionValue[]{new DimensionValue("Product","P1"), new DimensionValue("Year","2012")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Supplier","S2"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Year","2011"),new DimensionValue("Year","2012")}, "SUM(Volume)"), 0.00001);
		Assert.assertEquals(61, scenarioDataLayer.aggregate("VolumeTable", new DimensionValue[]{new DimensionValue("Product","P2"), new DimensionValue("Year","2012")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Supplier","S2"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Year","2011"),new DimensionValue("Year","2012")}, "SUM(Volume)"), 0.00001);
	}
	@Test(dependsOnMethods="scenario1stLevelTest")
	public void scenario2ndLevelTest() {
		scenarioDataLayer.createDimensionValue("Supplier", "S3");
		IBitSet ruleBitSet1 = BitDekkUtil.newBitSet();
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Supplier", "S2"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Product", "P1"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Product", "P2"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Year", "2011"));
		scenarioDataLayer.associateRule("Supplier", "S3", ruleBitSet1, new double[]{1, 0.9});
		double aggregate = scenarioDataLayer.aggregate("VolumeTable", new DimensionValue[]{new DimensionValue("Supplier","S3")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Supplier","S2"),new DimensionValue("Supplier","S3"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Year","2011"),new DimensionValue("Year","2012")}, "SUM(Volume * Cost)");
		Assert.assertEquals(28.26, aggregate, 0.000001);
	}
	@Test(dependsOnMethods="scenario2ndLevelTest")
	public void scenario3rdLevelTest() {
		IBitSet ruleBitSet1 = BitDekkUtil.newBitSet();
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Supplier", "S3"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Product", "P1"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Product", "P2"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Year", "2011"));
		ruleBitSet1.toString();
		scenarioDataLayer.associateRule("Year", "2012", ruleBitSet1, new double[]{3, 1});
		Assert.assertEquals(75, scenarioDataLayer.aggregate("VolumeTable", new DimensionValue[]{new DimensionValue("Supplier","S3"), new DimensionValue("Year","2012")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Supplier","S2"),new DimensionValue("Supplier","S3"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Year","2011"),new DimensionValue("Year","2012")}, "SUM(Volume)")
				, 0.000001);
		Assert.assertEquals(137.78, scenarioDataLayer.aggregate("VolumeTable", new DimensionValue[]{new DimensionValue("Year","2012")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Supplier","S3"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Year","2011"),new DimensionValue("Year","2012")}, "SUM(Volume * Cost)")
				, 0.000001);
	}
	@Test(dependsOnMethods="scenario3rdLevelTest")
	public void scenario4thLevelTest() {
		scenarioDataLayer.createDimensionValue("Supplier", "S4");
		IBitSet ruleBitSet1 = BitDekkUtil.newBitSet();
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Supplier", "S3"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Product", "P1"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Product", "P2"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Year", "2012"));
		scenarioDataLayer.associateRule("Supplier", "S4", ruleBitSet1, new double[]{2, 1.1});
		Assert.assertEquals(150, scenarioDataLayer.aggregate("VolumeTable", new DimensionValue[]{new DimensionValue("Supplier","S4")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Supplier","S2"),new DimensionValue("Supplier","S3"),new DimensionValue("Supplier","S4"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Year","2011"),new DimensionValue("Year","2012")}, "SUM(Volume)"), 0.000001);
	}
	@Test(dependsOnMethods="scenario4thLevelTest")
	public void selectTest() {
		System.out.println(scenarioDataLayer.select("VolumeTable", new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Supplier","S2"),new DimensionValue("Supplier","S3"),new DimensionValue("Supplier","S4"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Year","2011"),new DimensionValue("Year","2012")}, "Supplier", "Volume"));
	}
	@Test(dependsOnMethods="scenario4thLevelTest")
	public void updateRuleTest() {
		double s12012 = scenarioDataLayer.aggregate("VolumeTable", new DimensionValue[]{new DimensionValue("Supplier","S1"), new DimensionValue("Year","2012")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Supplier","S2"),new DimensionValue("Supplier","S3"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Year","2011"),new DimensionValue("Year","2012")}, "SUM(Volume)");
		IBitSet ruleBitSet1 = BitDekkUtil.newBitSet();
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Supplier", "S1"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Product", "P1"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Product", "P2"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Year", "2011"));
		scenarioDataLayer.associateRule("Year", "2012", ruleBitSet1, new double[] {3,1});
		Assert.assertEquals(s12012 * 3 / 2, scenarioDataLayer.aggregate("VolumeTable", new DimensionValue[]{new DimensionValue("Supplier","S1"), new DimensionValue("Year","2012")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Supplier","S2"),new DimensionValue("Supplier","S3"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Year","2011"),new DimensionValue("Year","2012")}
			, "SUM(Volume)"), 0.000001);
	}
	@Test(dependsOnMethods="updateRuleTest")
	public void deleteRuleTest() {
		double s12012 = scenarioDataLayer.aggregate("VolumeTable", new DimensionValue[]{new DimensionValue("Supplier","S1"), new DimensionValue("Year","2012")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Supplier","S2"),new DimensionValue("Supplier","S3"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Year","2011"),new DimensionValue("Year","2012")}, "SUM(Volume)");
		double only2012 = scenarioDataLayer.aggregate("VolumeTable", new DimensionValue[]{new DimensionValue("Year","2012")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Supplier","S2"),new DimensionValue("Supplier","S3"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Year","2011"),new DimensionValue("Year","2012")}, "SUM(Volume)");
		IBitSet ruleBitSet1 = BitDekkUtil.newBitSet();
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Supplier", "S1"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Product", "P1"));
		Assert.assertEquals(false, scenarioDataLayer.deleteRule("Year", "2012", ruleBitSet1));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Product", "P2"));
		ruleBitSet1.set(scenarioDataLayer.getDimensionId("Year", "2011"));
		Assert.assertEquals(true, scenarioDataLayer.deleteRule("Year", "2012", ruleBitSet1));
		Assert.assertEquals(only2012 - s12012, scenarioDataLayer.aggregate("VolumeTable", new DimensionValue[]{new DimensionValue("Year","2012")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Supplier","S2"),new DimensionValue("Supplier","S3"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Year","2011"),new DimensionValue("Year","2012")}
			, "SUM(Volume)"), 0.000001);
	}
	@Test(dependsOnMethods="deleteRuleTest")
	public void deleteScenarioTest1() {
		Assert.assertEquals(true, scenarioDataLayer.deleteDimensionValue("Supplier", "S3"));
	}
	@Test(dependsOnMethods="deleteRuleTest", expectedExceptions=IllegalArgumentException.class)
	public void deleteScenarioTest2() {
		Assert.assertEquals(false, scenarioDataLayer.deleteDimensionValue("Supplier", "S5"));
	}
	@Test(dependsOnMethods="deleteScenarioTest1", expectedExceptions=IllegalArgumentException.class)
	public void deleteScenarioTest3() {
		Assert.assertEquals(false, scenarioDataLayer.deleteDimensionValue("Supplier", "S3"));
	}
}
