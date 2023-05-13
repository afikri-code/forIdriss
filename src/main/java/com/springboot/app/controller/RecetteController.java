package com.springboot.app.controller;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.model.Recette;
import com.springboot.app.model.ResponseModel;
import com.springboot.app.service.GestionRecette;

@RestController
public class RecetteController {
	private final ResourceLoader resourceLoader;

public RecetteController(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
}
@Autowired
GestionRecette gestionRecette;

@PostMapping ("/add_recette")
public ResponseModel addRecette(@RequestBody Recette recette) {
	System.out.println(recette.toString());
	return gestionRecette.addRecette(recette);
}
//@CrossOrigin(origins = "*")
@PostMapping("/suppr_modification")
public ResponseModel deleteRecette(@RequestBody Recette recette_nom) {
	System.out.println("voici le nom de la recette " + recette_nom.getNom());
	return gestionRecette.deleteRecette(recette_nom.getNom());
}

@GetMapping("/supprimer-recette")
public ResponseEntity<String> afficherPageSupprimerRecette() throws IOException {
	Resource resource = resourceLoader.getResource("classpath:/templates/suppr_modification1.html");
    InputStream inputStream = resource.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    String html = reader.lines().collect(Collectors.joining("\n"));
    return ResponseEntity.ok(html);

}

    @GetMapping("/test6")
    public String getTest6Page() {
        return "test6";
    }

@GetMapping("/js")
public ResponseEntity<String> returnjs() throws IOException {
	Resource resource = resourceLoader.getResource("classpath:/templates/sources.js");
    InputStream inputStream = resource.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    String html = reader.lines().collect(Collectors.joining("\n"));
    return ResponseEntity.ok(html);

}
/*@Configuration
public class CorsConfig {

	@Bean
	public CorsFilter corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOriginPattern("*://localhost:*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
}*/

@GetMapping("/ajouter-recette")
public ResponseEntity<String> afficherPageAjouterRecette() throws IOException {
	Resource resource = resourceLoader.getResource("classpath:/templates/adds.html");
    InputStream inputStream = resource.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    String html = reader.lines().collect(Collectors.joining("\n"));
    return ResponseEntity.ok(html);

}

@GetMapping("/mypage")
public ResponseEntity<String> getMyPage() throws IOException {
	Resource resource = resourceLoader.getResource("classpath:/templates/first_interface_admin1.html");
	InputStream inputStream = resource.getInputStream();
	BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	String html = reader.lines().collect(Collectors.joining("\n"));
	return ResponseEntity.ok(html);
}
@GetMapping("/mypage_recette")
public ResponseEntity<String> getMyPage_recette() throws IOException {
    Resource resource = resourceLoader.getResource("classpath:/templates/test6.html");
    InputStream inputStream = resource.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    String html = reader.lines().collect(Collectors.joining("\n"));
    return ResponseEntity.ok(html);
}
@GetMapping("/recettes")
public ResponseEntity<List<Recette>> getRecettes() {
    List<Recette> recettes = gestionRecette.readJsonData(); // appell la methode du service
    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(recettes);
}
}