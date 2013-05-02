package org.bitdekk.scenario.distributed.noscenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bitdekk.distributed.scenario.server.impl.BitDekkScenarioInstance;
import org.bitdekk.distributed.scenario.server.impl.BitDekkScenarioServer;
import org.bitdekk.scenario.ScenarioDataLayer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;

public class TestBitDekkNodeAB {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws TypeMismatchException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, TypeMismatchException {
		BitDekkScenarioServer bitDekkScenarioServer = new BitDekkScenarioServer();
		bitDekkScenarioServer.setBitDekkScenarioInstance(getBitDekkScenarioInstance());
		bitDekkScenarioServer.launch();
	}

	private static BitDekkScenarioInstance getBitDekkScenarioInstance() throws TypeMismatchException {
		BitDekkScenarioInstance bitDekkInstance = new BitDekkScenarioInstance();
		bitDekkInstance.setIp("127.0.0.1");
		bitDekkInstance.setPort(54567);
		bitDekkInstance.setScenarioDataLayer(getScenarioDataLayer());
		return bitDekkInstance;
	}
	private static ScenarioDataLayer getScenarioDataLayer() throws TypeMismatchException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext-node-scenario-test.xml");
		ScenarioDataLayer scenarioDataLayer = ctx.getBean(ScenarioDataLayer.class);
		
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("S1",0);
		hashMap.put("S2",1);
		hashMap.put("P1",2);
		hashMap.put("P2",3);
		hashMap.put("2011",4);
		scenarioDataLayer.initializeDimensionValues(hashMap);
		
		HashMap<String, List<Integer>> dimensionToDimensionValueIdMap = new HashMap<String, List<Integer>>();
		dimensionToDimensionValueIdMap.put("Supplier", new ArrayList<Integer>(Arrays.asList(new Integer[]{0,1})));
		dimensionToDimensionValueIdMap.put("Product", new ArrayList<Integer>(Arrays.asList(new Integer[]{2,3})));
		dimensionToDimensionValueIdMap.put("Year", new ArrayList<Integer>(Arrays.asList(new Integer[]{4})));
		scenarioDataLayer.initializeDimensions(dimensionToDimensionValueIdMap);
		
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
		scenarioDataLayer.initializeTable("VolumeTable", dataTable);
		return scenarioDataLayer;
	}
}
