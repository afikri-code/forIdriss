package com.springboot.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Recette {
	private int idRecette;
	private String nom;
	private Ingredient[] ingredients;
	private String instructions;
	private int tempsPreparation;
	private int tempsCuisson;
	private String image;

	public Recette(String nom, Ingredient[] ingredients, String instructions, int tempsPreparation, int tempsCuisson) {
		this.nom = nom;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.tempsPreparation = tempsPreparation;
		this.tempsCuisson = tempsCuisson;
	}

	@Override
	public String toString() {
		StringBuilder ingr = new StringBuilder();
		for (Ingredient i : ingredients) {
			ingr.append(i.toString());
		}
		return "Recette [nom=" + nom + ", instructions=" + instructions + " " + ingr.toString() + ", tempsPreparation="
				+ tempsPreparation + ", tempsCuisson=" + tempsCuisson + "]";
	}
}
