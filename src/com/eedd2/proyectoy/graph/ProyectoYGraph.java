/**
 *
 */
package com.eedd2.proyectoy.graph;

import org.jgrapht.EdgeFactory;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.ClassBasedEdgeFactory;

/**
 * @author gbern
 * @param <E>
 * @param <V>
 *
 */
public class ProyectoYGraph<V,E> extends AbstractBaseGraph<V, E> implements UndirectedGraph<V,E> {

	/**
	 *
	 */
	private static final long serialVersionUID = 2731666982626275251L;

	public ProyectoYGraph(Class<? extends E> edgeClass) {
		super(new ClassBasedEdgeFactory<V,E>(edgeClass), true, true);
	}



}
