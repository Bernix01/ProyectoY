/**
 *
 */
package com.eedd2.proyectoy.sustentacion;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * @author gbern
 *
 */
public class BasicEdge extends DefaultWeightedEdge {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{\"distancia\":" + this.getWeight() + ", \"source\":\"" + ((Usuario) this.getSource()).getName() + "\", \"target\":\"" + ((Usuario) this.getTarget()).getName() + "\"}";
	}



}
