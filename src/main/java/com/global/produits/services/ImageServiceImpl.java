package com.global.produits.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.global.produits.ImageRepository;
import com.global.produits.ProduitRepository;
import com.global.produits.entities.Image;
import com.global.produits.entities.Produit;

@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	ImageRepository imageRepos;
	
	@Autowired
	ProduitRepository produitRepository;

	@Override
	public Image uploadImage(MultipartFile file) throws IOException {

		return imageRepos.save(Image.builder()
									.name(file.getOriginalFilename())
									.type(file.getContentType())
									.image(file.getBytes()).build());
	}

	@Override
	public Image getImageDetails(Long id) throws IOException {
		final Optional<Image> dbImage= imageRepos.findById(id);
		return Image.builder().idImage(dbImage.get().getIdImage())
							.name(dbImage.get().getName())
							.type(dbImage.get().getType())
							.image(dbImage.get().getImage()).build();
	}

	@Override
	public ResponseEntity<byte[]> getImage(Long id) throws IOException {
		final Optional<Image> dbImage= imageRepos.findById(id);
		return ResponseEntity.ok().contentType(MediaType.valueOf(dbImage.get().getType()))
				.body(dbImage.get().getImage());
	}

	@Override
	public void deleteImage(Long id) throws IOException {
		imageRepos.deleteById(id);

	}

	@Override
	public Image uplaodImageProd(MultipartFile file, Long idProd) throws IOException {
			Produit p = new Produit();
			p.setIdProduit(idProd);
			
			return imageRepos.save(Image.builder()
			 .name(file.getOriginalFilename())
			 .type(file.getContentType())
			 .image(file.getBytes())
			 .produit(p).build() );
	}

	@Override
	public List<Image> getImagesParProd(Long prodId) {
		Produit p = produitRepository.findById(prodId).get();
		return p.getImages();
	}

}
