package org.bitdekk.api;

/**
 * Helps processs an object T
 * @author gaurav.saxena
 *
 * @param <T>
 */
public interface Processor<T> {
	void process(T t);
}
