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
package org.bitdekk.distributed.util;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.bitdekk.api.Processor;
import org.bitdekk.distributed.cluster.ClusterConfig;
import org.bitdekk.distributed.scenario.server.model.CreateDimensionValueRequest;
import org.bitdekk.distributed.scenario.server.model.DeleteDimensionValueRequest;
import org.bitdekk.distributed.scenario.server.model.ExpressionEvaluationRequest;
import org.bitdekk.util.OpenBitSet;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class BitDekkDistributedUtil {
	public static void registerClasses(Kryo kryo) {
		kryo.register(ExpressionEvaluationRequest.class);
		kryo.register(CreateDimensionValueRequest.class);
		kryo.register(DeleteDimensionValueRequest.class);
		kryo.register(Double.class);
		kryo.register(OpenBitSet.class);
		kryo.register(long[][].class);
		kryo.register(long[].class);
		kryo.register(String.class);
		kryo.register(int.class);
		kryo.register(double[].class);
	}
	public static <T> boolean evaluate(long timeout, Object request, final Processor<T> processor) {
		final CountDownLatch doneSignal = new CountDownLatch(ClusterConfig.getInstance().getClusterSize());
		final Client client = new Client();
		BitDekkDistributedUtil.registerClasses(client.getKryo());
		client.addListener(new Listener() {
			@SuppressWarnings("unchecked")
			public void received (final Connection connection, Object object) {
				if(object instanceof Double) {
					processor.process((T)object);
					doneSignal.countDown();
				}
			}
		});
		client.start();
		try {
			client.connect(5000, ClusterConfig.getInstance().getRoot().getIp(), ClusterConfig.getInstance().getRoot().getPort());
			client.sendTCP(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return doneSignal.await(timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}
}
