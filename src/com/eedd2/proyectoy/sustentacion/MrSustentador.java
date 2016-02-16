package com.eedd2.proyectoy.sustentacion;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.*;

public class MrSustentador {
	private static final String usersFile = "personas.txt", followersFile = "seguidores.txt";

	public static DirectedWeightedMultigraph<Usuario, BasicEdge> cargar() {
		DirectedWeightedMultigraph<Usuario, BasicEdge> red = new DirectedWeightedMultigraph<Usuario, BasicEdge>(BasicEdge.class);
		File fileU = new File(usersFile);
		File fileS = new File(followersFile);
		String line;
		String data[];
		Usuario u;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileU));
			while((line = br.readLine()) != null){
				data = line.split(",");
				u = new Usuario();
				u.id = data[0];
				u.name = data[1];
				red.addVertex(u);

			}
			br.close();
			br = new BufferedReader(new FileReader(fileS));
			while((line = br.readLine()) != null){
				data = line.split(",");

				BasicEdge edge = new BasicEdge();
				final String idUsuario1 = data[0];
				final String idUsuario2 = data[1];
				Usuario u1,u2;
				u1 = red.vertexSet().stream().filter(usuario -> usuario.id.equals(idUsuario1)).findFirst().get();
				u2 = red.vertexSet().stream().filter(usuario -> usuario.id.equals(idUsuario2)).findFirst().get();
				red.addEdge(u1,u2 , edge);
				red.setEdgeWeight(edge, Point.distance(u1.locationlat,u1.locationlong,u2.locationlat,u2.locationlong));
				System.out.println(edge);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return red;
	}
}
