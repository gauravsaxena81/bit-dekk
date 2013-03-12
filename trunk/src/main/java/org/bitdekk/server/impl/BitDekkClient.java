package org.bitdekk.server.impl;

import org.bitdekk.server.api.IBitDekkInstance;

import com.esotericsoftware.kryonet.Client;

public class BitDekkClient extends Client{
	private IBitDekkInstance bitDekkInstance;

	public IBitDekkInstance getBitDekkInstance() {
		return bitDekkInstance;
	}

	public void setBitDekkInstance(IBitDekkInstance bitDekkInstance) {
		this.bitDekkInstance = bitDekkInstance;
	}
}
