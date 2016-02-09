package com.eedd2.proyectoy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import org.jgrapht.DirectedGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.eedd2.proyectoy.graph.ProyectoYEdge;
import com.eedd2.proyectoy.model.Pelicula;

public class ProyectoY {

	public static UndirectedGraph<Pelicula,ProyectoYEdge> cargar(){
		UndirectedGraph<Pelicula,ProyectoYEdge> orbe = new SimpleGraph<>(ProyectoYEdge.class);

		File file = new File("peliculas.y");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String linea;
			String[] datos;
			Pelicula temporal;
			while((linea = br.readLine()) != null){
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
				temporal.setTrailers(Arrays.asList(trailerarray));
				temporal.setImagen(datos[9].equals("N/A")? "": datos[9]);
				orbe.addVertex(temporal);
			}
			orbe.vertexSet().forEach(pelicula ->{
				//orbe.vertexSet().
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


	public static enum RelType{
		ACTOR,GENERO,ANIO;
	}
}
