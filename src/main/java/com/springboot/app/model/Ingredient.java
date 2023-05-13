package com.springboot.app.model;

import lombok.Data;

@Data
public class Ingredient {
	private int idIngredient;
	private String nom;
	private String type;

	@Override
	public String toString() {
		return "Ingredient [nom=" + nom + ", type=" + type + "]";
	}
}
