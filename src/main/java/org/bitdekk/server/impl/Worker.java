package org.bitdekk.server.impl;

import java.io.IOException;
import java.util.List;

import org.bitdekk.distributed.util.BitDekkDistributedUtil;
import org.bitdekk.server.model.Request;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Worker {
	public void distribute(Request request, List<BitDekkClient> bitDekkClients, final Connection connection) throws IOException {
		for(final BitDekkClient client : bitDekkClients) {
			BitDekkDistributedUtil.registerClasses(client.getKryo());
			client.addListener(new Listener() {
				public void received (final Connection conn, Object object) {
					if(object instanceof Double) {
						connection.sendTCP((Double)object);//Received from a server
						client.removeListener(this);
					}
				}
			});
			client.start();
			client.connect(5000, client.getBitDekkInstance().getIp(), client.getBitDekkInstance().getPort());
			client.sendTCP(request);
		}
	}
}
