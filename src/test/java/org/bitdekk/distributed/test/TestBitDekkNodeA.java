package org.bitdekk.distributed.test;

import java.io.IOException;
import java.util.ArrayList;

import org.bitdekk.DataLayer;
import org.bitdekk.distributed.server.api.IBitDekkInstance;
import org.bitdekk.distributed.server.impl.BitDekkInstance;
import org.bitdekk.distributed.server.impl.BitDekkServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;

public class TestBitDekkNodeA {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws TypeMismatchException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, TypeMismatchException {
		BitDekkServer bitDekkServer = new BitDekkServer();
		bitDekkServer.setBitDekkInstance(getBitDekkInstance());
		bitDekkServer.launch();
	}

	private static IBitDekkInstance getBitDekkInstance() throws TypeMismatchException {
		BitDekkInstance bitDekkInstance = new BitDekkInstance();
		bitDekkInstance.setIp("127.0.0.1");
		bitDekkInstance.setPort(54555);
		bitDekkInstance.setChildren(getChildren());
		bitDekkInstance.setDataLayer(getDataLayer());
		return bitDekkInstance;
	}
	private static ArrayList<IBitDekkInstance> getChildren() {
		ArrayList<IBitDekkInstance> children = new ArrayList<IBitDekkInstance>();
		
		BitDekkInstance bitDekkInstance = new BitDekkInstance();
		children.add(bitDekkInstance);
		bitDekkInstance.setIp("127.0.0.1");
		bitDekkInstance.setPort(54556);
		
		bitDekkInstance = new BitDekkInstance();
		children.add(bitDekkInstance);
		bitDekkInstance.setIp("127.0.0.1");
		bitDekkInstance.setPort(54557);
		
		return children;
	}

	private static DataLayer getDataLayer() throws TypeMismatchException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext-node-test.xml");
		DataLayer dataLayer = ctx.getBean(DataLayer.class);
		
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
		return dataLayer;
	}
}
