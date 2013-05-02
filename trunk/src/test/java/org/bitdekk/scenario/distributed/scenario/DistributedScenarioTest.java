package org.bitdekk.scenario.distributed.scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bitdekk.distributed.cluster.ClusterConfig;
import org.bitdekk.distributed.scenario.DistributedScenarioDataLayer;
import org.bitdekk.distributed.server.impl.BitDekkInstance;
import org.bitdekk.util.OpenBitSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;
@ContextConfiguration(locations = {"classpath:applicationContext-reducer-scenario-test.xml"})
@Test(singleThreaded=true)
public class DistributedScenarioTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private DistributedScenarioDataLayer distributedScenarioDataLayer;
	private Process processA;
	private Process processAA;
	private Process processAB;
	private BitDekkInstance root;
	
	public DistributedScenarioTest() throws IOException, InterruptedException {
		root = new BitDekkInstance();
		root.setIp("127.0.0.1");
		root.setPort(54575);
	}
	private void startCluster() {
		ClusterConfig.getInstance().setRoot(root);
		ClusterConfig.getInstance().setClusterSize(3);
	}
	@BeforeTest
	public void launchRoot() {
		String separator = System.getProperty("file.separator");
		String classpath = System.getProperty("java.class.path");
		String path = System.getProperty("java.home") + separator + "bin" + separator + "java";
		ProcessBuilder processBuilderA = 
	                new ProcessBuilder(path, "-cp", 
	                classpath, 
	                TestBitDekkNodeA.class.getName());
		processBuilderA.inheritIO();
		ProcessBuilder processBuilderAA = 
                new ProcessBuilder(path, "-cp", 
                classpath, 
                TestBitDekkNodeAA.class.getName());
		processBuilderAA.inheritIO();
		ProcessBuilder processBuilderAB = 
                new ProcessBuilder(path, "-cp", 
                classpath, 
                TestBitDekkNodeAB.class.getName());
		processBuilderAB.inheritIO();
		try {
			processAA = processBuilderAA.start();
			processAB = processBuilderAB.start();
			processA = processBuilderA.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@BeforeClass
	public void initialize() throws TypeMismatchException, IOException, InterruptedException {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("S1",0);
		hashMap.put("S2",1);
		hashMap.put("P1",2);
		hashMap.put("P2",3);
		hashMap.put("2011",4);
		distributedScenarioDataLayer.initializeDimensionValues(hashMap);
		
		HashMap<String, List<Integer>> dimensionToDimensionValueIdMap = new HashMap<String, List<Integer>>();
		dimensionToDimensionValueIdMap.put("Supplier", new ArrayList<Integer>(Arrays.asList(new Integer[]{0,1})));
		dimensionToDimensionValueIdMap.put("Product", new ArrayList<Integer>(Arrays.asList(new Integer[]{2,3})));
		dimensionToDimensionValueIdMap.put("Year", new ArrayList<Integer>(Arrays.asList(new Integer[]{4})));
		distributedScenarioDataLayer.initializeDimensions(dimensionToDimensionValueIdMap);
		
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
		distributedScenarioDataLayer.initializeTable("VolumeTable", dataTable);
	}
	@Test
	public void dimensionTest() {
		startCluster();
		Assert.assertEquals(distributedScenarioDataLayer.getDimensionValueIds("Supplier"), Arrays.asList(new Integer[]{0,1}));
		Assert.assertEquals(distributedScenarioDataLayer.getDimensionValueIds("Product"), Arrays.asList(new Integer[]{2,3}));
		Assert.assertEquals(distributedScenarioDataLayer.getDimensionValueIds("Year"), Arrays.asList(new Integer[]{4}));
		distributedScenario1stLevelTest();
		distributedScenario2ndLevelTest();
		distributedScenario3rdLevelTest();
		distributedScenario4thLevelTest();
		distributedUpdateRuleTest();
		distributedDeleteRuleTest();
		distributedDeleteScenarioTest();
	}
	private void distributedScenario1stLevelTest() {
		distributedScenarioDataLayer.createDimensionValue("Year", "2012", 5);
		OpenBitSet ruleBitSet1 = new OpenBitSet();
		ruleBitSet1.set(0);
		ruleBitSet1.set(2);
		ruleBitSet1.set(3);
		ruleBitSet1.set(4);
		distributedScenarioDataLayer.associateRule(5, ruleBitSet1, new double[]{2, 1});
		OpenBitSet ruleBitSet2 = new OpenBitSet();
		ruleBitSet2.set(1);
		ruleBitSet2.set(2);
		ruleBitSet2.set(3);
		ruleBitSet2.set(4);
		distributedScenarioDataLayer.associateRule(5, ruleBitSet2, new double[]{3, 1});
		Assert.assertEquals(117 * 3, distributedScenarioDataLayer.aggregate("VolumeTable", new String[]{"2012"}, new String[]{"S1","S2","P1","P2","2011","2012"}, "SUM(Volume)"), 0.000001);
		Assert.assertEquals(56 * 3, distributedScenarioDataLayer.aggregate("VolumeTable", new String[]{"P1", "2012"}, new String[]{"S1","S2","P1","P2","2011","2012"}, "SUM(Volume)"), 0.00001);
		Assert.assertEquals(61 * 3, distributedScenarioDataLayer.aggregate("VolumeTable", new String[]{"P2", "2012"}, new String[]{"S1","S2","P1","P2","2011","2012"}, "SUM(Volume)"), 0.00001);
	}
	private void distributedScenario2ndLevelTest() {
		distributedScenarioDataLayer.createDimensionValue("Supplier", "S3", 6);
		OpenBitSet ruleBitSet1 = new OpenBitSet();
		ruleBitSet1.set(1);
		ruleBitSet1.set(2);
		ruleBitSet1.set(3);
		ruleBitSet1.set(4);
		distributedScenarioDataLayer.associateRule(6, ruleBitSet1, new double[]{1, 0.9});
		Assert.assertEquals(28.26 * 3, distributedScenarioDataLayer.aggregate("VolumeTable", new String[]{"S3"}, new String[]{"S1","S2","S3","P1","P2","2011","2012"}, "SUM(Volume * Cost)")
				, 0.000001);
	}
	private void distributedScenario3rdLevelTest() {
		OpenBitSet ruleBitSet1 = new OpenBitSet();
		ruleBitSet1.set(6);
		ruleBitSet1.set(2);
		ruleBitSet1.set(3);
		ruleBitSet1.set(4);
		distributedScenarioDataLayer.associateRule(5, ruleBitSet1, new double[]{3, 1});
		Assert.assertEquals(75 * 3, distributedScenarioDataLayer.aggregate("VolumeTable", new String[]{"S3", "2012"}, new String[]{"S1","S2","S3","P1","P2","2011","2012"}, "SUM(Volume)"), 0.000001);
		Assert.assertEquals(137.78 * 3, distributedScenarioDataLayer.aggregate("VolumeTable", new String[]{"2012"}, new String[]{"S1","S3","P1","P2","2011","2012"}, "SUM(Volume * Cost)"), 0.000001);
	}
	private void distributedScenario4thLevelTest() {
		distributedScenarioDataLayer.createDimensionValue("Supplier", "S4", 7);
		OpenBitSet ruleBitSet1 = new OpenBitSet();
		ruleBitSet1.set(6);
		ruleBitSet1.set(2);
		ruleBitSet1.set(3);
		ruleBitSet1.set(5);
		distributedScenarioDataLayer.associateRule(7, ruleBitSet1, new double[]{2, 1.1});
		Assert.assertEquals(150 * 3, distributedScenarioDataLayer.aggregate("VolumeTable", new String[]{"S4"}, new String[]{"S1","S2","S3","S4","P1","P2","2011","2012"}, "SUM(Volume)"), 0.000001);
	}
	private void distributedUpdateRuleTest() {
		double s12012 = distributedScenarioDataLayer.aggregate("VolumeTable", new String[]{"S1", "2012"}, new String[]{"S1","S2","S3","P1","P2","2011","2012"}, "SUM(Volume)");
		OpenBitSet ruleBitSet1 = new OpenBitSet();
		ruleBitSet1.set(0);
		ruleBitSet1.set(2);
		ruleBitSet1.set(3);
		ruleBitSet1.set(4);
		distributedScenarioDataLayer.associateRule(5, ruleBitSet1, new double[] {3,1});
		Assert.assertEquals(s12012 * 3 / 2, distributedScenarioDataLayer.aggregate("VolumeTable", new String[]{"S1", "2012"}, new String[]{"S1","S2","S3","P1","P2","2011","2012"}
			, "SUM(Volume)"), 0.000001);
	}
	private void distributedDeleteRuleTest() {
		double s12012 = distributedScenarioDataLayer.aggregate("VolumeTable", new String[]{"S1", "2012"}, new String[]{"S1","S2","S3","P1","P2","2011","2012"}, "SUM(Volume)");
		double only2012 = distributedScenarioDataLayer.aggregate("VolumeTable", new String[]{"2012"}, new String[]{"S1","S2","S3","P1","P2","2011","2012"}, "SUM(Volume)");
		OpenBitSet ruleBitSet1 = new OpenBitSet();
		ruleBitSet1.set(0);
		ruleBitSet1.set(2);
		Assert.assertEquals(false, distributedScenarioDataLayer.deleteRule(5, ruleBitSet1));
		ruleBitSet1.set(3);
		ruleBitSet1.set(4);
		Assert.assertEquals(true, distributedScenarioDataLayer.deleteRule(5, ruleBitSet1));
		Assert.assertEquals(only2012 - s12012, distributedScenarioDataLayer.aggregate("VolumeTable", new String[]{"2012"}, new String[]{"S1","S2","S3","P1","P2","2011","2012"}
			, "SUM(Volume)"), 0.000001);
	}
	private void distributedDeleteScenarioTest() {
		Assert.assertEquals(false, distributedScenarioDataLayer.deleteDimensionValue("Supplier", "S5", 6));
		Assert.assertEquals(true, distributedScenarioDataLayer.deleteDimensionValue("Supplier", "S3", 6));
		Assert.assertEquals(false, distributedScenarioDataLayer.deleteDimensionValue("Supplier", "S3", 6));
	}
	@AfterClass
	public void destroyServers() {
		System.out.println("---------------------------------Distributed Scenario Test Done------------------------------------------");
		processAB.destroy();
		processA.destroy();
		processAA.destroy();
		System.out.println("---------------------------------Distributed Scenario Test Done------------------------------------------");
	}
}