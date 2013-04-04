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

import org.bitdekk.distributed.scenario.server.api.IBitDekkScenarioInstance;

import com.esotericsoftware.kryonet.Client;

public class BitDekkScenarioClient extends Client{
	private IBitDekkScenarioInstance bitDekkScenarioInstance;

	public IBitDekkScenarioInstance getBitDekkScenarioInstance() {
		return bitDekkScenarioInstance;
	}
	public void setBitDekkScenarioInstance(IBitDekkScenarioInstance bitDekkScenarioInstance) {
		this.bitDekkScenarioInstance = bitDekkScenarioInstance;
	}

}
