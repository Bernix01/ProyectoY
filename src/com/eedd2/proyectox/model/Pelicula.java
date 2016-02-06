package com.eedd2.proyectox.model;

import java.util.List;

public class Pelicula implements Comparable<Pelicula> {

	private String nombre,anio,genero, director, protagonista, comprar,imagen;
	private List<String> trailers;
	private int id;
	private double rating;
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
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	@Override
	public int compareTo(Pelicula arg0) {
		return Integer.compare(arg0.id, this.id);
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		sb.append("\"id");

		sb.append(this.id);
		sb.append('"'+this.nombre+'"');
		sb.append('"'+this.comprar+'"');
		sb.append('"'+this.director+'"');
		sb.append('"'+this.genero+'"');
		sb.append('"'+this.imagen+'"');
		sb.append('"'+this.protagonista+'"');
		sb.append(this.rating);
		sb.append(this.anio);

		sb.append('"'+"trailers"+'"');
		sb.append('[');
		this.trailers.forEach(t ->{
			sb.append('"'+t+'"'+',');
		});
		sb.setLength(sb.length() - 1);
		sb.append(']');

		sb.append('}');
		return sb.toString();
	}

}
