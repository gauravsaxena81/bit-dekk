package org.bitdekk.test;

import au.com.bytecode.opencsv.*;
import java.io.FileReader;

import org.bitdekk.store.Dimension;

public class TestDimension {
	
	private static final String FILE_ADDRESS = "E:/EclipseWorkspace/manish.mishra/workspace/BitDekk/test/org/bitdekk/test/Test.csv";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		CSVReader reader = new CSVReader(new FileReader(FILE_ADDRESS));
		String[] nextLine;
		
		//Read the column headers
		//Lang: Load Table Headers
		nextLine = reader.readNext(); //got the first line
		//Which columns are Dimensions
		//Lang: Dimension:C1 as 'Dimension A'
		Dimension dimC1 = new Dimension();
		dimC1.setName(nextLine[0]);
		dimC1.setDisplayName("Dimension A");
		//Lang: Dimension: C2 as 'Dimension B'
		Dimension dimC2 = new Dimension();
		dimC2.setName(nextLine[1]);
		dimC2.setDisplayName("Dimension B");
		//Lang: Dimension: C3 as 'Dimension C'
		Dimension dimC3 = new Dimension();
		dimC3.setName(nextLine[2]);
		dimC3.setDisplayName("Dimension C");
		//Lang: Dimension: C4 as 'Dimension D'
		Dimension dimC4 = new Dimension();
		dimC4.setName(nextLine[3]);
		dimC4.setDisplayName("Dimension D");
		
		System.out.println(dimC1.getName());
		System.out.println(dimC1.getDisplayName());
		
		System.out.println(dimC2.getName());
		System.out.println(dimC2.getDisplayName());
		
		System.out.println(dimC3.getName());
		System.out.println(dimC3.getDisplayName());
		
		System.out.println(dimC4.getName());
		System.out.println(dimC4.getDisplayName());
		
		/*while((nextLine = reader.readNext()) != null){
			System.out.println(nextLine[0] + "\t" + nextLine[1] + "\t" + nextLine[2] + "\t" + nextLine[3] + "\t" + nextLine[4] + "\t" + nextLine[5]);
		}*/
		
		reader.close();

	}

}
