package org.bitdekk.server.api;

import java.util.List;

import org.bitdekk.DataLayer;

public interface IBitDekkInstance {
	String getIp();
	int getPort();
	DataLayer getDataLayer();
	List<IBitDekkInstance> getChildren();
}
