package org.bitdekk.helper.sql.grammar.model;

import org.bitdekk.api.IBitSet;
@Deprecated
public interface ITreeNode<T> {
	T getNodeObject();
	IBitSet childrenBitset();
}
