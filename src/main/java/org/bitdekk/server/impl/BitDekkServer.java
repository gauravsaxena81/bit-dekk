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
