package com.global.produits;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.global.produits.entities.Categorie;
import com.global.produits.entities.Produit;

@RepositoryRestResource(path  = "rest")
public interface ProduitRepository extends JpaRepository<Produit, Long>{
	
	List<Produit> findByNomProduit(String n);
	List<Produit> findByNomProduitContains(String n);
	
	@Query("select p from Produit p where p.nomProduit like %:nom and p.prixProduit > :prix")
	List<Produit> findNomPrix(@Param("nom") String nom, @Param("prix") Double prix);
	
	@Query("select p from Produit p where p.categorie = ?1")
	List<Produit> findByCat(Categorie categorie);
	
	List<Produit> findByCategorieIdCat(Long id);

	List<Produit> findByOrderByNomProduitAsc();
	
	@Query("select p from Produit p order by p.nomProduit ASC, p.prixProduit DESC")
	List<Produit> findNomPrixTrie();
	
	
}
