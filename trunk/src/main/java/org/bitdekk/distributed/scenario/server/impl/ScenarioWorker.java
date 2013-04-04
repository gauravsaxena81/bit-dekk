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
import java.util.List;

import org.bitdekk.distributed.util.BitDekkDistributedUtil;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ScenarioWorker {
	public void distribute(Object request, List<BitDekkScenarioClient> bitDekkClients, final Connection connection) throws IOException {
		for(final BitDekkScenarioClient client : bitDekkClients) {
			BitDekkDistributedUtil.registerClasses(client.getKryo());
			client.addListener(new Listener() {
				public void received (final Connection conn, Object object) {
					if(object instanceof Double || object instanceof Boolean) {
						connection.sendTCP(object);//Received from a server
						client.removeListener(this);
					}
				}
			});
			client.start();
			client.connect(5000, client.getBitDekkScenarioInstance().getIp(), client.getBitDekkScenarioInstance().getPort());
			client.sendTCP(request);
		}
	}
}
