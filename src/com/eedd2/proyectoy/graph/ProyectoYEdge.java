/**
 *
 */
package com.eedd2.proyectoy.graph;

import org.jgrapht.graph.DefaultEdge;

import com.eedd2.proyectoy.ProyectoY.RelType;
import com.eedd2.proyectoy.model.Pelicula;

/**
 * @author gbern
 *
 */
public class ProyectoYEdge extends DefaultEdge {
	RelType type;

	public void setType(RelType type){
		this.type = type;
	}

	public ProyectoYEdge(RelType type){
		super();
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		super.equals(obj);
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProyectoYEdge))
			return false;
		ProyectoYEdge other = (ProyectoYEdge) obj;
		if (type != other.type)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{\"type\":\"" + type + "\", \"source\":" + ((Pelicula)this.getSource()).getId() + ", \"target\":" + ((Pelicula)this.getTarget()).getId() + "}";
	}


}
