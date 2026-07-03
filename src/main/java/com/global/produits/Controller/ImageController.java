package com.global.produits.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.global.produits.entities.Image;
import com.global.produits.entities.Produit;
import com.global.produits.services.ImageServiceImpl;
import com.global.produits.services.produitService;

@RestController
@RequestMapping("/api/image")
public class ImageController {
	
	@Autowired
	ImageServiceImpl imageServe;
	
	@Autowired
	produitService produitServe;
	
	@RequestMapping(value = "/uploadFS/{id}" , method = RequestMethod.POST)
	public void uploadImageFS(@RequestParam("image") MultipartFile 
			file,@PathVariable("id") Long id) throws IOException {
		Produit p = produitServe.getProduit(id);
		p.setImagePath(id+".jpg");

		Files.write(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath())
				, file.getBytes());
		produitServe.saveProduit(p);

	}
	
	@RequestMapping(value = "/loadfromFS/{id}" , 
			method = RequestMethod.GET,
			produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImageFS(@PathVariable("id") Long id) throws IOException {

		Produit p = produitServe.getProduit(id);
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath()));
	}
	
	
	
	@PostMapping("upload")
	public Image uploadImage(@RequestParam("image") MultipartFile file) throws IOException{
		return imageServe.uploadImage(file);
	}
	
	@GetMapping("/get/info/{id}")
	public Image getImagedetails(@PathVariable("id") Long id) throws IOException{
		return imageServe.getImageDetails(id);
	}
	
	@GetMapping("/load/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException{
		return imageServe.getImage(id);
	}
	
	@PostMapping(value = "/uplaodImageProd/{idProd}" )
	public Image uploadMultiImages(@RequestParam("image")MultipartFile file,
			@PathVariable("idProd") Long idProd) 
					throws IOException {
		return imageServe.uplaodImageProd(file,idProd);
	}
	@RequestMapping(value = "/getImagesProd/{idProd}" , 
			method = RequestMethod.GET)
	public List<Image> getImagesProd(@PathVariable("idProd") Long idProd) 
			throws IOException {
		return imageServe.getImagesParProd(idProd);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteImage(@PathVariable("id") Long id) throws IOException{
		 imageServe.deleteImage(id);
	}

}
