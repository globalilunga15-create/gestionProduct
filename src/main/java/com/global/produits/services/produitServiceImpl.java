package com.global.produits.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.produits.ImageRepository;
import com.global.produits.ProduitDTO;
import com.global.produits.ProduitRepository;
import com.global.produits.entities.Categorie;
import com.global.produits.entities.Produit;

@Service
public class produitServiceImpl implements produitService{
	
	//@Autowired
	private final ProduitRepository produitRepository; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	ImageRepository imageRepository;

	public produitServiceImpl(ProduitRepository produitRepository) {
		super();
		this.produitRepository = produitRepository;
	}

	@Override
	public Produit saveProduit(Produit p) {
		return produitRepository.save(p);
	}

	@Override
	public Produit updateProduit(Produit p) {
		//Long oldProdImageId = this.getProduit(p.getIdProduit()).getImage().getIdImage();
		//Long newProdImageId = p.getImage().getIdImage();
		Produit prodUpdated = produitRepository.save(p);
		//if (oldProdImageId != newProdImageId) 
		//	imageRepository.deleteById(oldProdImageId);
		return prodUpdated;
	}

	@Override
	public void deleteProduit(Produit p) {
		produitRepository.delete(p);
	}

	@Override
	public void deleteProduit(Long id) {
		Produit p =getProduit(id);
		//suuprimer l'image avant de supprimer le produit
		try {
			Files.delete(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath()));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		produitRepository.deleteById(id);		
	}

	@Override
	public Produit getProduit(Long id) {
		return produitRepository.findById(id).get();
	}

	@Override
	public List<Produit> getAllProduits() {
		return produitRepository.findAll();
	}

	@Override
	public List<Produit> byName(String nom) {
		return produitRepository.findByNomProduit(nom);
	}

	@Override
	public List<Produit> findByCont(String nom) {
		return produitRepository.findByNomProduitContains(nom);

	}

	@Override
	public List<Produit> findByNomPrix(String nom, Double prix) {
		return produitRepository.findNomPrix(nom, prix);

	}

	@Override
	public List<Produit> findByCat(Categorie cat) {
		return produitRepository.findByCat(cat);

	}
	

	@Override
	public List<Produit> findCat(Long id) {
		return produitRepository.findByCategorieIdCat(id);
	}

	@Override
	public List<ProduitDTO> findAllProduct() {
		/*List<Produit> prod= produitRepository.findAll();
		List<ProduitDTO> prodDto= new ArrayList<ProduitDTO>(prod.size());
		for(Produit p: prod) {
			prodDto.add(convertEntityToDTO(p));
		}*/
		
		return produitRepository.findByOrderByNomProduitAsc().stream()
				.map(this::convertEntityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<Produit> findTrie() {
		return produitRepository.findNomPrixTrie();

	}

	@Override
	public ProduitDTO convertEntityToDTO(Produit produit) {
		 /*ProduitDTO produitDTO = new ProduitDTO();
		 produitDTO.setIdProduit(produit.getIdProduit());
		 produitDTO.setNomProduit(produit.getNomProduit());
		 produitDTO.setPrixProduit(produit.getPrixProduit());
		 produitDTO.setDateCreation(produit.getDateCreation());
		 produitDTO.setCategorie(produit.getCategorie());
		 return produitDTO;*/
		
		/*return ProduitDTO.builder()
				.idProduit(produit.getIdProduit())
				.nomProduit(produit.getNomProduit())
				.prixProduit(produit.getPrixProduit())
				.dateCreation(produit.getDateCreation())
				.categorie(produit.getCategorie())
				.build();*/
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		ProduitDTO prod= modelMapper.map(produit, ProduitDTO.class);
		return prod;
		}

	@Override
	public Produit convertDTOToEntity(ProduitDTO produitDto) {
		Produit produit = new Produit();
		produit.setIdProduit(produitDto.getIdProduit());
		produit.setNomProduit(produitDto.getNomProduit());
		produit.setPrixProduit(produitDto.getPrixProduit());
		produit.setDateCreation(produitDto.getDateCreation());
		produit.setCategorie(produitDto.getCategorie());
		 return produit;
	}

}
