package org.bitdekk.test.store;

import au.com.bytecode.opencsv.*;
import java.io.FileReader;


//import org.bitdekk.store.Dimension;
import org.bitdekk.store.DimensionDictionary;

public class TestDimension {
	
	private static final String FILE_ADDRESS = "bin/org/bitdekk/test/store/Test.csv";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		CSVReader reader = new CSVReader(new FileReader(FILE_ADDRESS));
		String[] nextLine;
		
		DimensionDictionary dimensionDictionary = new DimensionDictionary();
		/* Language Considerations:
		 * -> Need to specify to read dimension names from where, first row, first column etc
		 * -> Need to specify which columns to read from the CSV file for dimensions
		 * Example:
		 * Read CSV file ([File Path]);
		 * DimensionName from ROW 1 (or COL 1)
		 * Read Columns (1,3,4,5) as dimensions
		 * Column 1 as 'DimA'
		 * Column 3 as 'DimC'
		 * Column 4 as 'DimD'
		 * Column 5 as 'DimE'
		 * 
		 * Define the language through XML?
		 */
		
		nextLine = reader.readNext(); //got the first line
		dimensionDictionary.addDimension(nextLine[0]);
		dimensionDictionary.addDimension(nextLine[1]);
		dimensionDictionary.addDimension(nextLine[2]);
		dimensionDictionary.addDimension(nextLine[3]);
		System.out.println(dimensionDictionary.toString());
		
		reader.close();
	}
}
