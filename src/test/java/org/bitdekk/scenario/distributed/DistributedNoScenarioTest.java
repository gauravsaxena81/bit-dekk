package org.bitdekk.scenario.distributed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bitdekk.distributed.cluster.ClusterConfig;
import org.bitdekk.distributed.scenario.DistributedScenarioDataLayer;
import org.bitdekk.distributed.server.impl.BitDekkInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;
@ContextConfiguration(locations = {"classpath:applicationContext-reducer-scenario-test.xml"})
public class DistributedNoScenarioTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private DistributedScenarioDataLayer distributedScenarioDataLayer;
	private Process processA;
	private Process processAA;
	private Process processAB;
	@BeforeTest
	public void startCluster() throws IOException, InterruptedException {
		BitDekkInstance root = new BitDekkInstance();
		root.setIp("127.0.0.1");
		root.setPort(54555);
		ClusterConfig.getInstance().setRoot(root);
		ClusterConfig.getInstance().setClusterSize(3);
		launchRoot();
	}
	private void launchRoot() {
		String separator = System.getProperty("file.separator");
		String classpath = System.getProperty("java.class.path");
		String path = System.getProperty("java.home")
	                + separator + "bin" + separator + "java";
		ProcessBuilder processBuilderA = 
	                new ProcessBuilder(path, "-cp", 
	                classpath, 
	                TestBitDekkNodeA.class.getName());
		ProcessBuilder processBuilderAA = 
                new ProcessBuilder(path, "-cp", 
                classpath, 
                TestBitDekkNodeAA.class.getName());
		ProcessBuilder processBuilderAB = 
                new ProcessBuilder(path, "-cp", 
                classpath, 
                TestBitDekkNodeAB.class.getName());
		try {
			processA = processBuilderA.start();
			processAA = processBuilderAA.start();
			processAB = processBuilderAB.start();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	@BeforeClass
	public void initialize() throws TypeMismatchException {
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
		dataTable.addColumn(new ColumnDescription("1", ValueType.TEXT, "Supplier"));
		dataTable.addColumn(new ColumnDescription("2", ValueType.TEXT, "Product"));
		dataTable.addColumn(new ColumnDescription("3", ValueType.NUMBER, "Volume"));
		dataTable.addColumn(new ColumnDescription("4", ValueType.NUMBER, "Cost"));
		TableRow row = new TableRow();
		row.addCell("S1");
		row.addCell("P1");
		row.addCell(10);
		row.addCell(1.0);
		dataTable.addRow(row);
		row = new TableRow();
		row.addCell("S1");
		row.addCell("P2");
		row.addCell(11);
		row.addCell(1.5);
		dataTable.addRow(row);
		row = new TableRow();
		row.addCell("S2");
		row.addCell("P1");
		row.addCell(12);
		row.addCell(1.1);
		dataTable.addRow(row);
		row = new TableRow();
		row.addCell("S2");
		row.addCell("P2");
		row.addCell(13);
		row.addCell(1.4);
		dataTable.addRow(row);
		distributedScenarioDataLayer.initializeTable("VolumeTable", dataTable);
	}
	@Test
	public void distributedTest() {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("S1",0);
		hashMap.put("S2",1);
		hashMap.put("P1",2);
		hashMap.put("P2",3);
		
		distributedScenarioDataLayer.initializeDimensionValues(hashMap);
		Assert.assertEquals(1800, distributedScenarioDataLayer.aggregate("VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, "SUM(2 * Volume)"), 0.00000001);
		Assert.assertEquals(302.0, (distributedScenarioDataLayer.aggregate("VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, "(SUM(2 * Volume) / COUNT(Volume)) + 2"))
				, 0.00000001);
	}
	@Test(expectedExceptions=RuntimeException.class)
	public void distributedTestNodeFailure() {
		processAB.destroy();
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("S1",0);
		hashMap.put("S2",1);
		hashMap.put("P1",2);
		hashMap.put("P2",3);
		
		distributedScenarioDataLayer.initializeDimensionValues(hashMap);
		Assert.assertEquals(1800, distributedScenarioDataLayer.aggregate("VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, "SUM(2 * Volume)"), 0.00000001);
	}
	@AfterTest
	public void destroyServers() {
		processA.destroy();
		processAA.destroy();
	}
}