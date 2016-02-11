package com.eedd2.proyectoy.view;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

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

	public static void main(String... args) {
		// Cargar el grafo desde los archivos
		ProyectoYGraph<Pelicula, ProyectoYEdge> orbe = ProyectoY.cargar();

		UndirectedSubgraph<Pelicula, ProyectoYEdge> sub;
		Set<Pelicula> res = new HashSet<Pelicula>();

		// Pedir la pelicula a buscar
		String palabra = JOptionPane.showInputDialog("Proyecto Y - Ingrese palabra para buscar coincidencias:");
		// obtener todas las posibles peliculas que contengan esa palabra,
		// ignorando mayusculas y minusculas
		List<Pelicula> coincidencias = orbe.vertexSet().stream()
				.filter(pelicula -> pelicula.getNombre().toLowerCase().trim().contains(palabra.trim().toLowerCase()))
				.collect(Collectors.toList());
		//Crea la lista de opciones
		String opciones = "";
		int i=0;
		for (Pelicula a:coincidencias){
			opciones+=i+"."+a.getNombre()+"\n";
			i++;
		}

		// pidiendo la opcion que se quiere buscar
		int opt = Integer.parseInt(JOptionPane.showInputDialog(null,opciones));


		// almacenando la pelicula buscada
		Pelicula prototipo = coincidencias.get(opt);

		//Aumentando las vistas de la pelicula seleccionada
		coincidencias.get(opt).voteUp();

		//sobreescribe la base de datos
		ProyectoY.guardar(orbe);

		// por cada coincidencia, se ejecuta una busqueda en anchura y se
		// almacena en un set
		coincidencias.forEach(peli -> {

			// porque los colores son geniales, darle un color especifico a
			// todas las coincidencias de palabra
			peli.setColor(Pelicula.COLOR.COINCIDENCIA);
			BreadthFirstIterator<Pelicula, ProyectoYEdge> iter = new BreadthFirstIterator<>(orbe, peli);
			iter.forEachRemaining(pelicula -> {
				res.add(pelicula);
			});
		});

		// porque los colores son geniales, darle un color especifico a la
		// pelicula buscada
		prototipo.setColor(Pelicula.COLOR.BUSCADO);

		// creamos un subgrafo con el set de peliculas que resultaron de las
		// busquedas en anchura
		sub = new UndirectedSubgraph<Pelicula, ProyectoYEdge>(orbe, res, null);
		// intentamos mostrar
		try {
			ProyectoY.mostrar(sub);
			orbe.clearColors(); //limpiar de los colores al orbe.
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
