# Getting Started #

1. Copy the jar in your lib folder or maven repository. So far we have not added the jar to maven central repo.

2. Dependencies [pom](http://code.google.com/p/bit-dekk/source/browse/trunk/pom.xml)
  * Spring 3(These will be removed in due course)
  * Google DataTable as an input format
  * Antlr 3.5

3. Example Code

```
//Create Dimensions and dimension values for the example
HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
hashMap.put("S1",0);
hashMap.put("S2",1);
hashMap.put("P1",2);
hashMap.put("P2",3);

//Create DataTable for the example
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

//Now the real action
//Create Datalayer
DataLayer dataLayer = new DataLayer();
//Initialize the dimensions
dataLayer.initializeDimensions(hashMap);
//Initialize the table
dataLayer.initializeTable("VolumeTable", dataTable);
//Now run queries on the table using datalayer
dataLayer.aggregate(new SumAggregation(), "VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, new String[]{"Volume"});//300
dataLayer.aggregate(new SumAggregation(), "VolumeTable",  new String[]{"S1","P1"}, new String[]{"S1","P1","P2","S2"}, new String[]{"Volume"});//100
dataLayer.aggregate(new AvgAggregation(), "VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, new String[]{"Volume"});//150
dataLayer.aggregate("VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, "SUM(2 * Volume)");//600
dataLayer.aggregate("VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, "AVG(2 * Volume) + 2");//302
dataLayer.aggregate("VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, "2 * SUM(Volume) + 2");//602
dataLayer.aggregate(new SumAggregation(), "VolumeTable", new String[]{"S1","P1"}, new String[]{"S1","P2"}, new String[]{"Volume"});//Double.NaN
dataLayer.aggregate(new AvgAggregation(), "VolumeTable", new String[]{"S1","P1"}, new String[]{"S1","P2"}, new String[]{"Volume"});//Double.NaN;
```