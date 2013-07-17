package org.bitdekk.helper.sql.grammar.model;

import org.bitdekk.api.IBitSet;
import org.bitdekk.util.BitDekkUtil;
@Deprecated
public class RootTreeNode implements ITreeNode<IBitSet> {
	private IBitSet filter;
	private IBitSet childrenBitSet = BitDekkUtil.newBitSet();
	public RootTreeNode(IBitSet filter) {
		this.filter = filter;
	}
	@Override
	public IBitSet getNodeObject() {
		return filter;
	}
	@Override
	public IBitSet childrenBitset() {
		return childrenBitSet;
	}
	@Override
	public int hashCode() {
		return -1;
	}
	@Override
	public boolean equals(Object o) {
		if(o != null && o instanceof DimensionValueTreeNode)
			return ((DimensionValueTreeNode)o).hashCode() == this.hashCode();
		else
			return false;
	}
}
