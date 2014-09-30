package com.example.contentprovidertest;

public class Contact {
	public String id;
	public String name;

	public Contact(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		String result = name + " " + id;
		return result;
	}
}
