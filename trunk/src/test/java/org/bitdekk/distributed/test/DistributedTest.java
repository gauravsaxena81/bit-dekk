package org.bitdekk.distributed.test;

import java.io.IOException;
import java.util.HashMap;

import org.bitdekk.DataLayer;
import org.bitdekk.distributed.cluster.ClusterConfig;
import org.bitdekk.distributed.server.impl.BitDekkInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
@ContextConfiguration(locations = {"classpath:applicationContext-reducer-test.xml"})
@Test(singleThreaded=true)
public class DistributedTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private DataLayer dataLayer;
	private Process processA;
	private Process processAA;
	private Process processAB;
	private BitDekkInstance root;
	
	public DistributedTest() throws IOException, InterruptedException {
		root = new BitDekkInstance();
		root.setIp("127.0.0.1");
		root.setPort(54555);
	}
	private void startCluster() {
		ClusterConfig.getInstance().setRoot(root);
		ClusterConfig.getInstance().setClusterSize(3);
	}
	@BeforeTest
	public void launchRoot() {
		String separator = System.getProperty("file.separator");
		String classpath = System.getProperty("java.class.path");
		String path = System.getProperty("java.home")
	                + separator + "bin" + separator + "java";
		ProcessBuilder processBuilderA = 
	                new ProcessBuilder(path, "-cp", 
	                classpath, 
	                TestBitDekkNodeA.class.getName());
		processBuilderA.inheritIO();
		ProcessBuilder processBuilderAA = 
                new ProcessBuilder(path, "-cp", 
                classpath, 
                TestBitDekkNodeAA.class.getName());
		processBuilderAA.inheritIO();
		ProcessBuilder processBuilderAB = 
                new ProcessBuilder(path, "-cp", 
                classpath, 
                TestBitDekkNodeAB.class.getName());
		processBuilderAB.inheritIO();
		try {
			processA = processBuilderA.start();
			processAA = processBuilderAA.start();
			processAB = processBuilderAB.start();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	@Test
	public void distributedTest() {
		startCluster();
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("S1",0);
		hashMap.put("S2",1);
		hashMap.put("P1",2);
		hashMap.put("P2",3);
		
		dataLayer.initializeDimensionValues(hashMap);
		Assert.assertEquals(1800, dataLayer.aggregate("VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, "SUM(2 * Volume)"), 0.00000001);
		Assert.assertEquals(302.0, (dataLayer.aggregate("VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, "(SUM(2 * Volume) / COUNT(Volume)) + 2"))
				, 0.00000001);
	}
	@Test(expectedExceptions=RuntimeException.class,dependsOnMethods="distributedTest")
	public void distributedTestFailure() {
		startCluster();
		processAB.destroy();
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("S1",0);
		hashMap.put("S2",1);
		hashMap.put("P1",2);
		hashMap.put("P2",3);
		
		dataLayer.initializeDimensionValues(hashMap);
		Assert.assertEquals(1800, dataLayer.aggregate("VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, "SUM(2 * Volume)"), 0.00000001);
	}
	@AfterClass
	public void destroyServers() {
		System.out.println("---------------------------------Distributed Test Done------------------------------------------");
		processA.destroy();
		processAA.destroy();
		//processAB.destroy();
		System.out.println("---------------------------------Distributed Test Done------------------------------------------");
	}
}