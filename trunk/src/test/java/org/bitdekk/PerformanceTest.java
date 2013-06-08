package org.bitdekk;

import org.bitdekk.api.IBitSet;
import org.bitdekk.util.BitDekkUtil;
import org.testng.annotations.Test;

@Test
public class PerformanceTest {
	@Test
	public void test() {
		IBitSet filterBitSet = BitDekkUtil.newBitSet();
		int MAX = 1000;
		for(int i = 0; i < MAX; i++)
			filterBitSet.set(i);
		IBitSet rowBitSet = BitDekkUtil.newBitSet();
		for(int i = 0; i < 5; i++)
			rowBitSet.set((long) (Math.random() * MAX));
		long currentTimeMillis = System.currentTimeMillis();
		for(int k = 0; k < 3000; k++)
			for(int i = 0, j = 0; i < 5000; i++)
				if(filterBitSet.contains(rowBitSet))
					j++;
		System.out.println(System.currentTimeMillis() - currentTimeMillis);
		/*for(int k = 0; k < 3000; k++)
			for(int i = 0, j = 0; i < 5000; i++)
				j = 65 & 6;*/
	}
}
