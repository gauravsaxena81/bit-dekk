package org.bitdekk.scenario.distributed.noscenario;

import java.io.IOException;

import org.bitdekk.distributed.cluster.ClusterConfig;
import org.bitdekk.distributed.scenario.DistributedScenarioDataLayer;
import org.bitdekk.distributed.server.impl.BitDekkInstance;
import org.bitdekk.model.DimensionValue;
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
@ContextConfiguration(locations = {"classpath:applicationContext-reducer-noscenario-test.xml"})
@Test(singleThreaded=true)
public class DistributedNoScenarioTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private DistributedScenarioDataLayer distributedScenarioDataLayer;
	private Process processA;
	private Process processAA;
	private Process processAB;
	private BitDekkInstance root;
	
	public DistributedNoScenarioTest() throws IOException, InterruptedException {
		root = new BitDekkInstance();
		root.setIp("127.0.0.1");
		root.setPort(54565);
	}
	private void startCluster() {
		ClusterConfig.getInstance().setRoot(root);
		ClusterConfig.getInstance().setClusterSize(3);
	}
	@BeforeTest
	public void launchRoot() {
		String separator = System.getProperty("file.separator");
		String classpath = System.getProperty("java.class.path");
		String path = System.getProperty("java.home")
	                + separator + "bin" + separator + "java";
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
			processA = processBuilderA.start();
			processAA = processBuilderAA.start();
			processAB = processBuilderAB.start();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	@BeforeClass
	public void initialize() throws TypeMismatchException, IOException, InterruptedException {
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
		System.out.println("DistributedNoScenarioTest " + distributedScenarioDataLayer.hashCode());
		distributedScenarioDataLayer.initializeTable("VolumeTable", dataTable);
	}
	@Test
	public void distributedTest() {
		startCluster();
		Assert.assertEquals(42 * 3, distributedScenarioDataLayer.aggregate("VolumeTable",  new DimensionValue[]{new DimensionValue("Supplier","S1")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Supplier","S2"),new DimensionValue("Year","2011")}, "SUM(2 * Volume)"), 0.00000001);
		Assert.assertEquals(23.0, (distributedScenarioDataLayer.aggregate("VolumeTable",  new DimensionValue[]{new DimensionValue("Supplier","S1")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Supplier","S2"),new DimensionValue("Year","2011")}, "(SUM(2 * Volume) / COUNT(Volume)) + 2"))
				, 0.00000001);
	}
	@Test(dependsOnMethods="distributedTest", expectedExceptions=RuntimeException.class)
	public void distributedTestNodeFailure() {
		startCluster();
		processAB.destroy();
		Assert.assertEquals(42 * 3, distributedScenarioDataLayer.aggregate("VolumeTable",  new DimensionValue[]{new DimensionValue("Supplier","S1")}, new DimensionValue[]{new DimensionValue("Supplier","S1"),new DimensionValue("Product","P1"),new DimensionValue("Product","P2"),new DimensionValue("Supplier","S2"),new DimensionValue("Year","2011")}, "SUM(2 * Volume)"), 0.00000001);
	}
	@AfterClass
	public void destroyServers() {
		System.out.println("---------------------------------Distributed No Scenario Test Done------------------------------------------");
		processA.destroy();
		processAA.destroy();
		processAB.destroy();
		System.out.println("---------------------------------Distributed No Scenario Test Done------------------------------------------");
	}
}