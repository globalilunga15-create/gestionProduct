package com.global.produits.Controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.global.produits.entities.Produit;
import com.global.produits.services.produitService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ProduitsRESTController {
	
	//@Autowired
	private final produitService produitServe;
	
	//@RequestMapping(methowd = RequestMethod.GET)
	@GetMapping("all")
	public List<Produit> getAllProduits(){
		return produitServe.getAllProduits();
	}
	
	@GetMapping("/getbyid/{id}")
	public Produit getProduit(@PathVariable("id") Long id) {
		return produitServe.getProduit(id);
	}
	
	@PostMapping("/addproduct")
	//@PreAuthorize("hasAutority('ADMIN')")
	public Produit addProduit(@RequestBody Produit product) {
		return produitServe.saveProduit(product);
	}
	
	@PutMapping("/updateproduct")
	public Produit update(@RequestBody Produit product) {
		return produitServe.updateProduit(product);
	}
	
	@DeleteMapping("/delprod/{id}")
	public void deleteProduit(@PathVariable("id") Long id) {
		produitServe.deleteProduit(id);
	}
	
	@GetMapping("/prodsCat/{id}")
	public List<Produit> prodByCat(@PathVariable("id") Long id) {
		return produitServe.findCat(id);
	}
	@GetMapping("prodsByName/{nom}")
	public List<Produit> getByNomContains(@PathVariable("nom") String nom){
		return produitServe.findByCont(nom);
	}
}
