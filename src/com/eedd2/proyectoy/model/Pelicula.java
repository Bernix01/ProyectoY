package com.eedd2.proyectoy.model;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Pelicula implements Comparable<Pelicula> {

	private String nombre, anio, genero, director, protagonista, comprar, imagen;
	private List<String> trailers;
	private int id;
	private int rating;

	public Pelicula(){
		this.rating = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getProtagonista() {
		return protagonista;
	}

	public void setProtagonista(String protagonista) {
		this.protagonista = protagonista;
	}

	public String getComprar() {
		return comprar;
	}

	public void setComprar(String comprar) {
		this.comprar = comprar;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<String> getTrailers() {
		return trailers;
	}

	public void setTrailers(List<String> trailers) {
		this.trailers = trailers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void voteUp() {
		this.rating++;
	}

	public int compareTo(Pelicula arg0) {
		return Integer.compare(arg0.id, this.id);
	}

	@Override
	public String toString() {
		ObjectMapper mapperObj = new ObjectMapper();
		try {
			return mapperObj.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
