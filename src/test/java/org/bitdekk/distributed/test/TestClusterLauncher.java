package org.bitdekk.distributed.test;

import java.io.IOException;
import java.util.HashMap;

import org.bitdekk.DataLayer;
import org.bitdekk.distributed.cluster.ClusterConfig;
import org.bitdekk.server.impl.BitDekkInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
@ContextConfiguration(locations = {"classpath:applicationContext-reducer-test.xml"})
public class TestClusterLauncher extends AbstractTestNGSpringContextTests {
	@Autowired
	private DataLayer dataLayer;
	private Process processA;
	private Process processAA;
	private Process processAB;
	@BeforeTest
	public void startCluster() throws IOException, InterruptedException {
		BitDekkInstance root = new BitDekkInstance();
		root.setIp("127.0.0.1");
		root.setPort(54555);
		ClusterConfig.getInstance().setRoot(root);
		ClusterConfig.getInstance().setClusterSize(3);
		launchRoot();
	}
	private void launchRoot() {
		String separator = System.getProperty("file.separator");
		String classpath = System.getProperty("java.class.path");
		String path = System.getProperty("java.home")
	                + separator + "bin" + separator + "java";
		ProcessBuilder processBuilderA = 
	                new ProcessBuilder(path, "-cp", 
	                classpath, 
	                TestBitDekkNodeA.class.getName());
		ProcessBuilder processBuilderAA = 
                new ProcessBuilder(path, "-cp", 
                classpath, 
                TestBitDekkNodeAA.class.getName());
		ProcessBuilder processBuilderAB = 
                new ProcessBuilder(path, "-cp", 
                classpath, 
                TestBitDekkNodeAB.class.getName());
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
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("S1",0);
		hashMap.put("S2",1);
		hashMap.put("P1",2);
		hashMap.put("P2",3);
		
		dataLayer.initializeDimensions(hashMap);
		double x = (dataLayer.aggregate("VolumeTable",  new String[]{"S1"}, new String[]{"S1","P1","P2","S2"}, "SUM(2 * Volume)"));
		Assert.assertEquals(1800, x, 0.00000001);
	}
	@AfterTest
	public void destroyServers() {
		processA.destroy();
		processAA.destroy();
		processAB.destroy();
	}
}