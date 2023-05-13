package com.springboot.app.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.app.model.Recette;
import com.springboot.app.model.ResponseModel;
import com.springboot.app.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.util.ArrayList;

@Service
public class GestionRecette {

	public ResponseModel addRecette(Recette recette) {
		ResponseModel responseModel = new ResponseModel();
		List<Recette> recetteData = readJsonData();
		int id_unique = 0;
		for (Recette r : recetteData) {
			id_unique++;
			r.setIdRecette(id_unique);
			System.out.println("larecette");
			System.out.println(r);
		}
		Recette recettePresent = null;
		
		if(recetteData != null) {
			recettePresent = recetteData.parallelStream().filter(rect -> rect.getNom().equals(recette.getNom())).findAny().orElse(null);
		}
		else {
			recetteData = new ArrayList<>();
		}

		if(recettePresent == null) {
			recette.setIdRecette(id_unique+1);
			recetteData.add (recette);

			boolean status = writeJsonData(recetteData);
			if(status) {
				responseModel.setStatus(Constants.SUCCESS) ;
				responseModel.setData(Constants.RECETTE_ADDED_SUCCESSFULLY);
			}
		}
		 

		else {
			responseModel.setData (Constants.RECETTE_ALREADY_PRESENT);
		}
		return responseModel;
	}
	
	public ResponseModel deleteRecette(String recette_nom) {
		ResponseModel reponsemodel = new ResponseModel();
		List<Recette> recetteData = readJsonData();
		recetteData.removeIf(rect -> (rect.getNom()).equals(recette_nom));
		boolean status = writeJsonData(recetteData);
		if(status)
		{
			reponsemodel.setStatus(Constants.SUCCESS);
			reponsemodel.setData(Constants.RECETTE_DELETED_SUCCESSFULLY);
		}
		else
		{
			reponsemodel.setData(Constants.RECETTE_NOT_FOUND);
		}
		return reponsemodel;
	}

	public List<Recette> readJsonData() {
		List<Recette> recettes = new ArrayList<>();

		try {
		    String json = Files.readString(Path.of("recettes.json"));
		    recettes = new Gson().fromJson(json, new TypeToken<List<Recette>>(){}.getType());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return recettes;
	}
	
	public boolean writeJsonData(List<Recette> recetteData){
		boolean status = false;
		for (Recette r : recetteData) {
			r.getIngredients();
		}
		try (FileWriter file = new FileWriter("recettes.json")) {
			file.write (new Gson().toJson(recetteData));
			file.flush();
			
			status = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return status;
	}

}
