package org.bitdekk.helper.sql.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.bitdekk.exception.InvalidBitDekkExpressionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.visualization.datasource.DataSourceHelper;
import com.google.visualization.datasource.base.DataSourceException;
import com.google.visualization.datasource.base.InvalidQueryException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.NumberValue;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.ibm.icu.util.ULocale;

@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class SqlHelperTest  extends AbstractTestNGSpringContextTests {
  @Autowired
  private SqlHelper sqlHelper;
  
  @Test
  public void result() throws InvalidQueryException, DataSourceException, InvalidBitDekkExpressionException {
	HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
	hashMap.put("S1",0);
	hashMap.put("S2",1);
	hashMap.put("P1",2);
	hashMap.put("P2",3);
	
	HashMap<String, ArrayList<String>> dimensionMap = new HashMap<String, ArrayList<String>>();
	dimensionMap.put("Supplier", new ArrayList<String>(Arrays.asList(new String[]{"S1","S2"})));
	dimensionMap.put("Product", new ArrayList<String>(Arrays.asList(new String[]{"P1","P2"})));
	sqlHelper.initialize(dimensionMap, hashMap);
	
	DataTable dataTable = new DataTable();
	dataTable.addColumn(new ColumnDescription("Supplier", ValueType.TEXT, "Supplier"));
	dataTable.addColumn(new ColumnDescription("Product", ValueType.TEXT, "Product"));
	dataTable.addColumn(new ColumnDescription("Volume", ValueType.NUMBER, "Volume"));
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
	sqlHelper.initializeTable("VolumeTable", dataTable);
	
	DataTable dataTableSelected = sqlHelper.result("SELECT SUM(Volume) FROM VolumeTable");
	Assert.assertEquals(1000.0, ((NumberValue)dataTableSelected.getCell(0, 0).getValue()).getValue(), 0.000000000001);
	
	dataTableSelected = sqlHelper.result("SELECT Supplier, Product, SUM(Volume) FROM VolumeTable");
	Assert.assertEquals(100.0, ((NumberValue)dataTableSelected.getCell(0, 2).getValue()).getValue(), 0.000000000001);
	Assert.assertEquals(200.0, ((NumberValue)dataTableSelected.getCell(1, 2).getValue()).getValue(), 0.000000000001);
	Assert.assertEquals(300.0, ((NumberValue)dataTableSelected.getCell(2, 2).getValue()).getValue(), 0.000000000001);
	Assert.assertEquals(400.0, ((NumberValue)dataTableSelected.getCell(3, 2).getValue()).getValue(), 0.000000000001);
	
	dataTableSelected = sqlHelper.result("SELECT Supplier, SUM(Volume), Product FROM VolumeTable");
	Assert.assertEquals(100.0, ((NumberValue)dataTableSelected.getCell(0, 1).getValue()).getValue(), 0.000000000001);
	Assert.assertEquals(200.0, ((NumberValue)dataTableSelected.getCell(1, 1).getValue()).getValue(), 0.000000000001);
	Assert.assertEquals(300.0, ((NumberValue)dataTableSelected.getCell(2, 1).getValue()).getValue(), 0.000000000001);
	Assert.assertEquals(400.0, ((NumberValue)dataTableSelected.getCell(3, 1).getValue()).getValue(), 0.000000000001);
	
	dataTableSelected = sqlHelper.result("SELECT Supplier, SUM(Volume) AS VOLUME, Product AS PP FROM VolumeTable");
	Assert.assertEquals(dataTableSelected.getColumnDescription(1).getLabel(), "VOLUME");
	Assert.assertEquals(dataTableSelected.getColumnDescription(2).getLabel(), "PP");
	
	dataTableSelected = sqlHelper.result("SELECT Supplier, SUM(Volume) FROM VolumeTable");
	Assert.assertEquals(300.0, ((NumberValue)dataTableSelected.getCell(0, 1).getValue()).getValue(), 0.000000000001);
	Assert.assertEquals(700.0, ((NumberValue)dataTableSelected.getCell(1, 1).getValue()).getValue(), 0.000000000001);
	
	dataTableSelected = sqlHelper.result("SELECT Supplier, SUM(Volume) FROM VolumeTable HAVING SUM(Volume) > 400");
	Assert.assertEquals(700.0, ((NumberValue)dataTableSelected.getCell(0, 1).getValue()).getValue(), 0.000000000001);
	
	dataTableSelected = sqlHelper.result("SELECT Supplier, SUM(Volume) FROM VolumeTable HAVING SUM(Volume) < 3 * MIN(Volume)");
	Assert.assertEquals(700.0, ((NumberValue)dataTableSelected.getCell(0, 1).getValue()).getValue(), 0.000000000001);
	
	dataTableSelected = sqlHelper.result("SELECT Supplier, SUM(Volume) FROM VolumeTable HAVING 100 < 100");
	Assert.assertEquals(0, dataTableSelected.getNumberOfRows());
	
	dataTableSelected = sqlHelper.result("SELECT SUM(Volume), Supplier FROM VolumeTable");
	Assert.assertEquals(300.0, ((NumberValue)dataTableSelected.getCell(0, 0).getValue()).getValue(), 0.000000000001);
	Assert.assertEquals(700.0, ((NumberValue)dataTableSelected.getCell(1, 0).getValue()).getValue(), 0.000000000001);
	
	dataTableSelected = sqlHelper.result("SELECT Supplier, 2 * SUM(Volume) FROM VolumeTable WHERE Product = \"P1\"");
	Assert.assertEquals(200.0, ((NumberValue)dataTableSelected.getCell(0, 1).getValue()).getValue(), 0.000000000001);
	Assert.assertEquals(600.0, ((NumberValue)dataTableSelected.getCell(1, 1).getValue()).getValue(), 0.000000000001);
	
	dataTableSelected = sqlHelper.result("SELECT Supplier, 2 * SUM(Volume) + 2 FROM VolumeTable WHERE Product = \"P1\"");
	//DataTable applyQuery = DataSourceHelper.applyQuery(DataSourceHelper.parseQuery("SELECT Supplier, (2 * SUM(Volume)) + 2 WHERE Product = \"P1\" group by Supplier"), dataTable, ULocale.US);
	Assert.assertEquals(202.0, ((NumberValue)dataTableSelected.getCell(0, 1).getValue()).getValue(), 0.000000000001);
	Assert.assertEquals(602.0, ((NumberValue)dataTableSelected.getCell(1, 1).getValue()).getValue(), 0.000000000001);
	
	dataTableSelected = sqlHelper.result("SELECT Supplier, Product, 2 * SUM(Volume) AS Volume FROM VolumeTable ORDER BY Volume DESC");
	Assert.assertEquals(800.0, ((NumberValue)dataTableSelected.getCell(0, 2).getValue()).getValue(), 0.000000000001);
	
	dataTableSelected = sqlHelper.result("SELECT Supplier, Product, 2 * SUM(Volume) AS Volume FROM VolumeTable ORDER BY Volume ASC");
	Assert.assertEquals(200.0, ((NumberValue)dataTableSelected.getCell(0, 2).getValue()).getValue(), 0.000000000001);
	
	dataTableSelected = sqlHelper.result("SELECT Supplier, Product, 2 * SUM(Volume) AS Volume FROM VolumeTable ORDER BY Volume");
	Assert.assertEquals(200.0, ((NumberValue)dataTableSelected.getCell(0, 2).getValue()).getValue(), 0.000000000001);
	
	dataTableSelected = sqlHelper.result("SELECT Supplier, Product, 2 * SUM(Volume) AS Volume FROM VolumeTable ORDER BY Volume DESC LIMIT 1, 2");
	Assert.assertEquals(600.0, ((NumberValue)dataTableSelected.getCell(0, 2).getValue()).getValue(), 0.000000000001);
	
	long currentTimeMillis = System.currentTimeMillis();
	DataSourceHelper.applyQuery(DataSourceHelper.parseQuery("SELECT Supplier, SUM(Volume) WHERE Product = \"P1\" group by Supplier"), dataTable, ULocale.US);
	//Performance
	long timeTakenGoogle = System.currentTimeMillis() - currentTimeMillis;
	currentTimeMillis = System.currentTimeMillis();
	dataTableSelected = sqlHelper.result("SELECT Supplier, SUM(Volume) FROM VolumeTable WHERE Product = \"P1\"");
	long timeTakenBitDekk = System.currentTimeMillis() - currentTimeMillis;
	Assert.assertEquals(100.0, ((NumberValue)dataTableSelected.getCell(0, 1).getValue()).getValue(), 0.000000000001);
	Assert.assertEquals(true, timeTakenBitDekk < timeTakenGoogle);
  }
  @Test(expectedExceptions={IllegalArgumentException.class})
  public void illegalQueries() throws DataSourceException, InvalidBitDekkExpressionException {	
	sqlHelper.result("SELECT Supplier, Product, 2 * SUM(Volume) AS Volume FROM VolumeTable ORDER BY Volume DESC LIMIT 3, 1");
  }
  @Test(expectedExceptions={InvalidGrammarException.class})
  public void invalidQueryExpression() throws InvalidBitDekkExpressionException {
	sqlHelper.result("SELECT Supplier, Product, 2 * SUM(Volume) AS Volume FROM VolumeTable ORDER BY Volume DESC LIMIT -1, 2");
  }
  @Test(expectedExceptions={InvalidGrammarException.class})
  public void invalidQueryExpression1() throws InvalidBitDekkExpressionException {
		sqlHelper.result("SELECT Supplier, Product, 2 * SUM(Volume) AS Volume FROM VolumeTable ORDER BY Volume desc");
  }
  @Test(expectedExceptions={InvalidGrammarException.class})
  public void invalidQueryExpression2() throws InvalidBitDekkExpressionException {
		sqlHelper.result("SELECT Supplier, Product, 2 * Volume AS Volume FROM VolumeTable ORDER BY Volume desc");
  }
  @Test(expectedExceptions={InvalidGrammarException.class})
  public void invalidQueryExpression3() throws InvalidBitDekkExpressionException {
		sqlHelper.result("SELECT Supplier, Product, 2 * SUM(Volume) AS Volume FROM VolumeTable HAVING Volume > 100 ORDER BY Volume desc");
  }
  @Test(expectedExceptions={InvalidGrammarException.class})
  public void invalidQueryExpression4() throws InvalidBitDekkExpressionException {
		sqlHelper.result("SELECT Supplier, Product, 2 * SUM(Volume) AS Volume FROM VolumeTable HAVING Supplier = 'S1' ORDER BY Volume desc");
  }
}
