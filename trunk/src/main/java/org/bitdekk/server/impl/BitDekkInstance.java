package org.bitdekk.server.impl;

import java.util.ArrayList;

import org.bitdekk.DataLayer;
import org.bitdekk.server.api.IBitDekkInstance;

public class BitDekkInstance  implements IBitDekkInstance {
	private ArrayList<IBitDekkInstance> children = new ArrayList<IBitDekkInstance>();
	private String ip;
	private int tcpPort;
	private DataLayer dataLayer = new DataLayer();
	public DataLayer getDataLayer() {
		return dataLayer;
	}
	public void setDataLayer(DataLayer dataLayer) {
		this.dataLayer = dataLayer;
	}
	public ArrayList<IBitDekkInstance> getChildren() {
		return children;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return tcpPort;
	}
	public void setPort(int tcpPort) {
		this.tcpPort = tcpPort;
	}
	public void setChildren(ArrayList<IBitDekkInstance> children) {
		this.children = children;
	}
}
