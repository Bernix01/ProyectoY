/**
 *
 */
package com.eedd2.proyectoy.view;

import org.jgrapht.DirectedGraph;
import org.jgrapht.UndirectedGraph;

import com.eedd2.proyectoy.ProyectoY;
import com.eedd2.proyectoy.graph.ProyectoYEdge;
import com.eedd2.proyectoy.model.Pelicula;

/**
 * @author gbern
 *
 */
public class MainGui {

	public static void main(String... args){
		UndirectedGraph<Pelicula, ProyectoYEdge> orbe = ProyectoY.cargar();
		System.out.println(orbe);
	}
}
