package org.bitdekk.test;

import au.com.bytecode.opencsv.*;
import java.io.FileReader;
import java.util.ArrayList;

import org.bitdekk.store.Dimension;
import org.bitdekk.store.DimensionValue;

import org.bitdekk.util.DimensionValueIDGenerator;

public class TestDimensionValue {
	
	private static final String FILE_ADDRESS = "E:/EclipseWorkspace/manish.mishra/workspace/BitDekk/test/org/bitdekk/test/Test.csv";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		CSVReader reader = new CSVReader(new FileReader(FILE_ADDRESS));
		String[] nextLine;
		ArrayList<Dimension> dim = new ArrayList<Dimension>();
		ArrayList<DimensionValue> dimValues = new ArrayList<DimensionValue>();
		
		//Read the first row and create the dimensions
		nextLine = reader.readNext();
		Dimension dimC1 = new Dimension();
		dimC1.setName(nextLine[0]);
		dimC1.setDisplayName("Dimension A");
		dim.add(dimC1);
		//Lang: Dimension: C2 as 'Dimension B'
		Dimension dimC2 = new Dimension();
		dimC2.setName(nextLine[1]);
		dimC2.setDisplayName("Dimension B");
		dim.add(dimC2);
		//Lang: Dimension: C3 as 'Dimension C'
		Dimension dimC3 = new Dimension();
		dimC3.setName(nextLine[2]);
		dimC3.setDisplayName("Dimension C");
		dim.add(dimC3);
		//Lang: Dimension: C4 as 'Dimension D'
		Dimension dimC4 = new Dimension();
		dimC4.setName(nextLine[3]);
		dimC4.setDisplayName("Dimension D");
		dim.add(dimC4);
		
		DimensionValueIDGenerator dimValIDGen = new DimensionValueIDGenerator();
		
		//Read the rest of the lines
		
		while((nextLine = reader.readNext()) != null){
			for(int i = 0; i < 4; i++){
				DimensionValue dimVal = new DimensionValue();
				dimVal.setDimensionValue(nextLine[i]);
				int dimId = dimValIDGen.getID(dim.get(i), nextLine[i]);
				dimVal.setDimensionValueID(dimId);
				dimValues.add(dimVal);
			}
		}
		
		//all done;
		
		for(int i = 0; i < dim.size(); i++){
			System.out.println("Dimension Name: " + dim.get(i).getName());
			System.out.println("Dimension Display Name: " + dim.get(i).getDisplayName());
		}
		
		for(int i = 0; i < dimValues.size(); i++){
			System.out.println("----------------------");
			System.out.println("Dimension Value:" + dimValues.get(i).getDimensionValue());
			System.out.println("Dimension Display Value:" + dimValues.get(i).getDimensionDisplayValue());
			System.out.println("Dimension ID:" + dimValues.get(i).getDimensionValueID());
		}
		
		reader.close();

	}

}
