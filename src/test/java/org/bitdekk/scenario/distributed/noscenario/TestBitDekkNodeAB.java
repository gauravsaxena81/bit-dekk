package org.bitdekk.scenario.distributed.noscenario;

import java.io.IOException;

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
