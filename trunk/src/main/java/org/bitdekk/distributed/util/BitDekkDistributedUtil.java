package org.bitdekk.distributed.util;

import org.bitdekk.server.model.Request;
import org.bitdekk.util.OpenBitSet;

import com.esotericsoftware.kryo.Kryo;

public class BitDekkDistributedUtil {
	public static void registerClasses(Kryo kryo) {
		kryo.register(Request.class);
		kryo.register(Double.class);
		kryo.register(OpenBitSet.class);
		kryo.register(long[][].class);
		kryo.register(long[].class);
	}
}
