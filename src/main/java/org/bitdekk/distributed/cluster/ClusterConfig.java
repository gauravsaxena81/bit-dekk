package org.bitdekk.distributed.cluster;

import org.bitdekk.server.api.IBitDekkInstance;

public class ClusterConfig {
	private int clusterSize;
	private static ClusterConfig clusterConfig = new ClusterConfig();
	private IBitDekkInstance root;

	public IBitDekkInstance getRoot() {
		return root;
	}
	public void setRoot(IBitDekkInstance root) {
		this.root = root;
	}
	public int getClusterSize() {
		return clusterSize;
	}
	public void setClusterSize(int clusterSize) {
		this.clusterSize = clusterSize;
	}
	private ClusterConfig() {}
	public static ClusterConfig getInstance() {
		return clusterConfig;
	}
}