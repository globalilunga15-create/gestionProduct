package com.global.produits;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.produits.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{

}
