package org.bitdekk.test.store;

import java.io.FileReader;

import au.com.bytecode.opencsv.*;
import org.bitdekk.store.Measure;

public class TestMeasure {

	private static final String FILE_ADDRESS = "bin/org/bitdekk/test/store/Test.csv";
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		CSVReader reader = new CSVReader(new FileReader(FILE_ADDRESS));
		String[] nextLine;
		
		nextLine = reader.readNext();
		
		//Read the measure Columns
		Measure mesA = new Measure();
		mesA.setName(nextLine[4]);
		
		Measure mesB = new Measure();
		mesB.setName(nextLine[5]);
		
		System.out.println("Measure A:" + mesA.getName());
		System.out.println("Measure B:" + mesB.getName());
		
		reader.close();
	}

}
