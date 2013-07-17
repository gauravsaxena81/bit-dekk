package org.bitdekk.helper.sql.grammar.model;

import org.bitdekk.api.IBitSet;
import org.bitdekk.util.BitDekkUtil;
@Deprecated
public class DimensionValueTreeNode implements ITreeNode<Integer> {
	private IBitSet childrenBitSet = BitDekkUtil.newBitSet();
	private int dimensionValueId;
	public DimensionValueTreeNode(int dimensionValueId) {
		this.dimensionValueId = dimensionValueId;
	}
	@Override
	public IBitSet childrenBitset() {
		return childrenBitSet;
	}
	@Override
	public Integer getNodeObject() {
		return dimensionValueId;
	}
	@Override
	public int hashCode() {
		return dimensionValueId;
	}
	@Override
	public boolean equals(Object o) {
		if(o != null && o instanceof DimensionValueTreeNode)
			return ((DimensionValueTreeNode)o).dimensionValueId == this.dimensionValueId;
		else
			return false;
	}
}
