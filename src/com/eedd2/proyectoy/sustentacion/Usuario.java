package com.eedd2.proyectoy.sustentacion;

import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Usuario {

	String id,name;
	long locationlat, locationlong;

	public Usuario(){
		this.locationlat = ThreadLocalRandom.current().nextLong(1000, 3000);
		this.locationlong = ThreadLocalRandom.current().nextLong(1000, 3000);
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the locationlat
	 */
	public long getLocationlat() {
		return locationlat;
	}
	/**
	 * @param locationlat the locationlat to set
	 */
	public void setLocationlat(long locationlat) {
		this.locationlat = locationlat;
	}
	/**
	 * @return the locationlong
	 */
	public long getLocationlong() {
		return locationlong;
	}
	/**
	 * @param locationlong the locationlong to set
	 */
	public void setLocationlong(long locationlong) {
		this.locationlong = locationlong;
	}


}
