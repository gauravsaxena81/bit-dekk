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

import java.io.IOException;
import java.util.ArrayList;

import org.bitdekk.distributed.scenario.server.api.IBitDekkScenarioInstance;
import org.bitdekk.distributed.scenario.server.model.CreateDimensionValueRequest;
import org.bitdekk.distributed.scenario.server.model.DeleteDimensionValueRequest;
import org.bitdekk.distributed.scenario.server.model.DeleteRuleRequest;
import org.bitdekk.distributed.server.model.AssociateRuleRequest;
import org.bitdekk.distributed.util.BitDekkDistributedUtil;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class BitDekkScenarioServer {
	private IBitDekkScenarioInstance bitDekkInstance;
	private ArrayList<BitDekkClient> bitDekkClients = new ArrayList<BitDekkClient>();
	private ScenarioWorker worker = new ScenarioWorker();

	public void launch() throws IOException {
		Server server = new Server();
		server.start();
		server.bind(bitDekkInstance.getPort());
		BitDekkDistributedUtil.registerClasses(server.getKryo());
		
		server.addListener(new Listener(){
			public void received (final Connection connection, final Object object) {
				if(object instanceof CreateDimensionValueRequest) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							createDimensionValue(connection, (CreateDimensionValueRequest) object);
						}
		    		  }).start();
				} else if(object instanceof DeleteDimensionValueRequest) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							deleteDimensionValue(connection, (DeleteDimensionValueRequest) object);
						}
		    		  }).start();
				} else if(object instanceof AssociateRuleRequest) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							associateRule(connection, (AssociateRuleRequest) object);
						}
		    		  }).start();
				} else if(object instanceof DeleteRuleRequest) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							deleteRule(connection, (DeleteRuleRequest) object);
						}
		    		  }).start();
				}
				try {
					worker.distribute(object, bitDekkClients, connection);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		for(IBitDekkScenarioInstance i : bitDekkInstance.getChildren()) { 
			BitDekkClient bitDekkClient = new BitDekkClient();
			bitDekkClients.add(bitDekkClient);
			bitDekkClient.setBitDekkScenarioInstance(i);
			BitDekkDistributedUtil.registerClasses(bitDekkClient.getKryo());
		}
	}
	public IBitDekkScenarioInstance getBitDekkInstance() {
		return bitDekkInstance;
	}
	public void setBitDekkInstance(IBitDekkScenarioInstance bitDekkInstance) {
		this.bitDekkInstance = bitDekkInstance;
	}
	private void createDimensionValue(Connection connection, CreateDimensionValueRequest request) {
		System.err.println("Entering");
		bitDekkInstance.getScenarioDataLayer().createDimensionValue(request.getDimension(), request.getDimensionValue(), request.getId());
		connection.sendTCP(true);
		System.err.println("Exiting");
	}
	private void deleteDimensionValue(Connection connection, DeleteDimensionValueRequest request) {
		System.err.println("Entering");
		bitDekkInstance.getScenarioDataLayer().deleteDimensionValue(request.getDimension(), request.getDimensionValue(), request.getId());
		connection.sendTCP(true);
		System.err.println("Exiting");		
	}
	private void associateRule(Connection connection, AssociateRuleRequest request) {
		System.err.println("Entering");
		bitDekkInstance.getScenarioDataLayer().associateRule(request.getId(), request.getRuleBitSet(), request.getFactor());
		connection.sendTCP(true);
		System.err.println("Exiting");
	}
	private void deleteRule(Connection connection, DeleteRuleRequest request) {
		System.err.println("Entering");
		bitDekkInstance.getScenarioDataLayer().deleteRule(request.getId(), request.getKey());
		connection.sendTCP(true);
		System.err.println("Exiting");
	}
}
