package org.bitdekk.test.store;

import au.com.bytecode.opencsv.*;
import java.io.FileReader;

import org.bitdekk.store.DimensionDictionary;

public class TestDimensionValue {

	private static final String FILE_ADDRESS = "bin/org/bitdekk/test/store/Test.csv";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		CSVReader reader = new CSVReader(new FileReader(FILE_ADDRESS));
		String[] nextLine;

		DimensionDictionary dimensionDict = new DimensionDictionary();

		// Read the first row and create the dimensions
		nextLine = reader.readNext();
		dimensionDict.addDimension(nextLine[0]);
		dimensionDict.addDimension(nextLine[1]);
		dimensionDict.addDimension(nextLine[2]);
		dimensionDict.addDimension(nextLine[3]);

		String[] dimensionArr = nextLine.clone();

		while ((nextLine = reader.readNext()) != null) {
			for (int i = 0; i < 4; i++) {
				dimensionDict.addDimensionValue(dimensionArr[i], nextLine[i]);
			}
		}

		System.out.println(dimensionDict.toString());

		reader.close();
	}
}
