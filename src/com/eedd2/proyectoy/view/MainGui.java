/**
 *
 */
package com.eedd2.proyectoy.view;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import org.jgrapht.DirectedGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.Subgraph;
import org.jgrapht.graph.UndirectedSubgraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import com.eedd2.proyectoy.ProyectoY;
import com.eedd2.proyectoy.graph.ProyectoYEdge;
import com.eedd2.proyectoy.graph.ProyectoYGraph;
import com.eedd2.proyectoy.model.Pelicula;

/**
 * @author gbern
 *
 */
public class MainGui {

	public static void main(String... args){
		ProyectoYGraph<Pelicula, ProyectoYEdge> orbe = ProyectoY.cargar();
		Set<Pelicula> res = new HashSet<>();
		String palabra = JOptionPane.showInputDialog("Proyecto y - Peliculas");
		List<Pelicula> coincidencias = orbe.vertexSet().stream().filter(pelicula ->  pelicula.getNombre().contains(palabra)).collect(Collectors.toList());
		int opt = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la pelicula...\n"+coincidencias));
		Pelicula prototipo = coincidencias.get(opt);
		BreadthFirstIterator<Pelicula, ProyectoYEdge> iter = new BreadthFirstIterator<>(orbe,prototipo);
		iter.forEachRemaining(pelicula ->{
			res.add(pelicula);
		});
		UndirectedSubgraph<Pelicula,ProyectoYEdge> sub;
		sub = new UndirectedSubgraph<Pelicula,ProyectoYEdge>(orbe, res,null);
		System.out.println(sub.vertexSet());
		System.out.println(sub.edgeSet());
	}
}
