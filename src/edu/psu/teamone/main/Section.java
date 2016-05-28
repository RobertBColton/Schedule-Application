package edu.psu.teamone.main;

import java.net.URI;

public class Section {
	private int id; // section id
	private String name; // name
	private String abbreviation; // course abbreviation ex)CMPSC 
	private int number; // course Number
	private URI bulletinURI; // web link to bulletin 

	public Section(String name, String abbreviation, int number, URI bulletinURI) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.number = number;
		this.bulletinURI = bulletinURI;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setBulletinURI(URI bulletinURI) {
		this.bulletinURI = bulletinURI;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public int getNumber() {
		return number;
	}

	public URI getBulletinURI() {
		return bulletinURI;
	}
}
