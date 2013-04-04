/*
 * Copyright 2013 Contributors of bit-dekk
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.bitdekk.distributed.scenario.server.impl;

import java.util.ArrayList;

import org.bitdekk.distributed.scenario.server.api.IBitDekkScenarioInstance;
import org.bitdekk.scenario.ScenarioDataLayer;

public class BitDekkScenarioInstance  implements IBitDekkScenarioInstance {
	private ArrayList<IBitDekkScenarioInstance> children = new ArrayList<IBitDekkScenarioInstance>();
	private String ip;
	private int tcpPort;
	private ScenarioDataLayer scenarioDataLayer;
	
	public ScenarioDataLayer getScenarioDataLayer() {
		return scenarioDataLayer;
	}
	public void setScenarioDataLayer(ScenarioDataLayer scenarioDataLayer) {
		this.scenarioDataLayer = scenarioDataLayer;
	}
	public ArrayList<IBitDekkScenarioInstance> getChildren() {
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
	public void setChildren(ArrayList<IBitDekkScenarioInstance> children) {
		this.children = children;
	}
}
