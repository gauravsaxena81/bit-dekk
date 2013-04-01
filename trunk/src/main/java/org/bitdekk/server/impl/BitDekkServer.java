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
package org.bitdekk.server.impl;

import java.io.IOException;
import java.util.ArrayList;

import org.bitdekk.distributed.util.BitDekkDistributedUtil;
import org.bitdekk.server.api.IBitDekkInstance;
import org.bitdekk.server.model.Request;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class BitDekkServer {
	private IBitDekkInstance bitDekkInstance;
	private ArrayList<BitDekkClient> bitDekkClients = new ArrayList<BitDekkClient>();
	private Worker worker = new Worker();

	public void launch() throws IOException {
		Server server = new Server();
		server.start();
		server.bind(bitDekkInstance.getPort());
		BitDekkDistributedUtil.registerClasses(server.getKryo());
		
		server.addListener(new Listener(){
			public void received (final Connection connection, Object object) {
				if(object instanceof Request) {
					final Request request = (Request) object;
					new Thread(new Runnable() {
						@Override
						public void run() {
							System.err.println("Entering");
							double aggregate = bitDekkInstance.getDataLayer().aggregate(request.getTableName(), request.getViewBitSet(), request.getFilterBitSet()
									, request.getMeasureExpression());
							System.err.println(aggregate);
							connection.sendTCP(aggregate);
							System.err.println("Exiting");
						}
		    		  }).start();
					try {
						worker.distribute((Request)object, bitDekkClients, connection);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		for(IBitDekkInstance i : bitDekkInstance.getChildren()) {
			BitDekkClient bitDekkClient = new BitDekkClient();
			bitDekkClients.add(bitDekkClient);
			bitDekkClient.setBitDekkInstance(i);
			BitDekkDistributedUtil.registerClasses(bitDekkClient.getKryo());
		}
	}
	public IBitDekkInstance getBitDekkInstance() {
		return bitDekkInstance;
	}
	public void setBitDekkInstance(IBitDekkInstance bitDekkInstance) {
		this.bitDekkInstance = bitDekkInstance;
	}
}
