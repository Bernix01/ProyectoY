package com.eedd2.proyectoy.model;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Pelicula implements Comparable<Pelicula> {

	private String nombre, anio, genero, director, protagonista, comprar, imagen;
	private List<String> trailers;
	private int id;
	private int rating;
	private COLOR color;

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(COLOR color) {
		this.color = color;
	}

	public Pelicula() {
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

	/**
	 * @return the color
	 */
	public COLOR getColor() {
		return color;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Pelicula)) {
			return false;
		}
		Pelicula other = (Pelicula) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	public static enum COLOR {
		BUSCADO("rgb(179, 65, 49)"), COINCIDENCIA("rgb(236, 173, 50)");

		private final String text;

		/**
		 * @param text
		 */
		private COLOR(final String text) {
			this.text = text;
		}

		@JsonValue
		public String getValue() {
			return text;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return text;
		}
	}

	public String paraGuardar() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append('|');
		sb.append(nombre);
		sb.append('|');
		sb.append(anio);
		sb.append('|');
		sb.append(director);
		sb.append('|');
		sb.append(protagonista);
		sb.append('|');
		sb.append(genero);
		sb.append('|');
		sb.append(rating);
		sb.append('|');
		sb.append(comprar);
		sb.append('|');
		trailers.forEach(trailer -> {
			sb.append(trailers);
			sb.append(" ");
		});
		sb.substring(sb.length() - 1);
		sb.append('|');
		sb.append(imagen);
		return sb.toString();
	}

}
