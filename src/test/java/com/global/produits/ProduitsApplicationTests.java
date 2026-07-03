package com.global.produits;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.global.produits.entities.Categorie;
import com.global.produits.entities.Produit;

//@SpringBootTest
class ProduitsApplicationTests {
	
	/*@Autowired
	private ProduitRepository produitRepository;
	
	@Test
	@Rollback(false)
	public void testCreatedProduct() {
		Produit prod = new Produit(new Date(), "HP PC", 305.00);
		try {
			
			produitRepository.save(prod);
			System.out.print("Succés d'enregistrement");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public  void testById() {
		Produit p = produitRepository.findById(4L).get();
		System.out.print(p);
	}
	
	@Test
	public void testUpdate() {
		Produit p = produitRepository.findById(1L).get();
		p.setNomProduit("IPhone 8");
		produitRepository.save(p);
	}
	
	@Test
	public void testDelete() {
		produitRepository.deleteById(2L);
	}
	
	@Test
	public void testAll() {
		List<Produit> prs= produitRepository.findAll();
		
		for(Produit p: prs)
			System.out.print(p);
		
	}
	
	@Test
	public void byName() {
		List<Produit> p= produitRepository.findByNomProduit("HP PC");

		for(Produit prod: p)
			System.out.print(prod);

	}
	
	@Test
	public void findByCont() {
		List<Produit> p= produitRepository.findByNomProduitContains("p");
		
		for(Produit prod: p)
			System.out.print(prod);

	}
	
	@Test
	public void findByNomPrix() {
		List<Produit> p= produitRepository.findNomPrix("PC", 200.00);
		
		for(Produit prod:p)
			System.out.println(prod);
	}
	
	@Test
	public void findByCat() {
		Categorie cat= new Categorie();
		cat.setIdCat(1L);
		
		List<Produit> p =  produitRepository.findByCat(cat);
		
		for(Produit prod: p)
			System.out.println(prod);
	}
	
	@Test
	public void findCat() {
		List<Produit> p= produitRepository.findByCategorieIdCat(1L);

		for(Produit prod: p)
			System.out.println(prod);
		
		
	}
	
	@Test
	public void findAllProduct() {
		List<Produit> p= produitRepository.findByOrderByNomProduitAsc();

		for(Produit prod: p)
			System.out.println(prod);
		
		
	}

	@Test
	public void findTrie() {
		List<Produit> p= produitRepository.findNomPrixTrie();

		for(Produit prod: p)
			System.out.println(prod);
		
		
	}
	
	@Test
	public void essaie() {
		try (Scanner scan = new Scanner(System.in)) {
			int[] nbres= new int[10];
			
			System.out.println("Saisie des 10 nombres");
			
			for (int i=0 ;i<nbres.length; i++) {

				boolean valueSaisie= false;
				while(!valueSaisie) {
					System.out.println("Nombre n° " + (i +1) + " :");
					if(scan.hasNextInt()) {
						nbres[i]= scan.nextInt();
						valueSaisie= true;
					}else {
						System.out.println("Erreur");
						scan.next();
					}
				}
			}
			int max= nbres[0];
			int position= 0;
			for (int i=1 ;i<nbres.length; i++) {
				if (nbres[i] > max) {
					max= nbres[i];
					position= i;
				}
			}
			System.out.println("Le plus grand nombre est: " + max + " à la position " + position);
		}
		
	}*/
}
