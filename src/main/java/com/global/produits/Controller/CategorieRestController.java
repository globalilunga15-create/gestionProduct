package com.global.produits.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.produits.CategorieRepository;
import com.global.produits.entities.Categorie;


@CrossOrigin
@RestController
@RequestMapping("/api/categorie")
public class CategorieRestController {
	
	@Autowired
	CategorieRepository catRepos;
	
	@GetMapping
	public List<Categorie> getCategorie(){
		return catRepos.findAll();
	}
	
	@GetMapping("/{id}")
	public Categorie getCategorieById(@PathVariable("id") Long id) {
		return catRepos.findById(id).get();
	}
	
	@PostMapping
	public Categorie addCategorie(@RequestBody Categorie cat) {
		return catRepos.save(cat);
	}

}
