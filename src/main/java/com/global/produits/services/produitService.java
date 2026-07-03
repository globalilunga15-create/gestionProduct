package com.global.produits.services;

import java.util.List;

import com.global.produits.ProduitDTO;
import com.global.produits.entities.Categorie;
import com.global.produits.entities.Produit;

public interface produitService {
	Produit saveProduit(Produit p);
	Produit updateProduit(Produit p);
	void deleteProduit(Produit p);
	void deleteProduit(Long id);
	Produit getProduit(Long id);
	List<Produit> getAllProduits();
	List<Produit> byName(String nom);	
	List<Produit> findByCont(String nom);
	List<Produit> findByNomPrix(String nom, Double prix);
	List<Produit> findByCat(Categorie cat); 
	List<Produit> findCat(Long id); 
	List<ProduitDTO> findAllProduct();
	List<Produit> findTrie();
	ProduitDTO convertEntityToDTO(Produit p);
	Produit convertDTOToEntity(ProduitDTO p);
}
