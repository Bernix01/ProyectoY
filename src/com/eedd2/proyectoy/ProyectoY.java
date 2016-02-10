package com.eedd2.proyectoy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.jgrapht.DirectedGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.eedd2.proyectoy.graph.ProyectoYEdge;
import com.eedd2.proyectoy.graph.ProyectoYGraph;
import com.eedd2.proyectoy.model.Pelicula;

public class ProyectoY {

	public static ProyectoYGraph<Pelicula, ProyectoYEdge> cargar() {
		ProyectoYGraph<Pelicula, ProyectoYEdge> orbe = new ProyectoYGraph<Pelicula, ProyectoYEdge>(ProyectoYEdge.class);

		File file = new File("peliculas.y");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String linea;
			String[] datos;
			Pelicula temporal;
			while ((linea = br.readLine()) != null) {
				datos = linea.split("\\|");
				temporal = new Pelicula();
				temporal.setId(Integer.parseInt(datos[0]));
				temporal.setNombre(datos[1]);
				temporal.setAnio(datos[2]);
				temporal.setDirector(datos[3]);
				temporal.setProtagonista(datos[4]);
				temporal.setGenero(datos[5]);
				temporal.setComprar(datos[7]);
				String[] trailerarray = datos[8].split(" ");
				temporal.setTrailers(Arrays.asList(trailerarray).stream().filter(trailer -> !trailer.equals("N/A"))
						.collect(Collectors.toList()));
				temporal.setImagen(datos[9].equals("N/A") ? "" : datos[9]);
				orbe.addVertex(temporal);
			}
			orbe.vertexSet().stream().forEach(pelicula -> {
				orbe.vertexSet().stream().filter(tmp -> !tmp.equals(pelicula)).forEach(pelicula2 -> {
if(!pelicula.equals(pelicula2)){
					ProyectoYEdge edge;
					if (pelicula.getProtagonista().equals(pelicula2.getProtagonista())) {
						edge = new ProyectoYEdge(RelType.PROTAGONISTA);
						if (!orbe.containsEdge(edge))
							orbe.addEdge(pelicula, pelicula2, edge);
					}
					if (pelicula.getDirector().equals(pelicula2.getDirector())) {
						edge = new ProyectoYEdge(RelType.DIRECTOR);
						if (!orbe.containsEdge(edge))
							orbe.addEdge(pelicula, pelicula2, edge);
					}
}
				});
			});
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return orbe;
	}

	public static enum RelType {
		DIRECTOR, PROTAGONISTA;
	}
}
