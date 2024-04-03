package com.helen.Controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.helen.Entity.Publication;
import com.helen.Service.CloudinaryService;
import com.helen.Service.PublicationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/publications")
@AllArgsConstructor
public class PublicationController {
	
	@Autowired
	private final CloudinaryService cloudinaryService;
	
	@Autowired
	private final PublicationService publicationService;
	
    @GetMapping
    public ResponseEntity<List<Publication>> getAllPublications() {
    	return new ResponseEntity<>(publicationService.getAllPublications(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publication> getPublication(@PathVariable("id") Long id) {
		return publicationService.getPublication(id)
				.map(publication -> new ResponseEntity<>(publication, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    //Metodo para recoger las 10 mejores fotos
    @GetMapping("/top")
    public ResponseEntity<List<Publication>> getTop10Publications() {
    	return new ResponseEntity<>(publicationService.getAllTopPublications(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addPublication(@RequestParam("multipartFile") MultipartFile multipartFile,
                                                 @RequestParam("title") String title,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("city") String city,
                                                 @RequestParam("fkUser") Long fkUser) {
        try {
            BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
            if (bi == null) {
                return new ResponseEntity<>("Publicacion no valida: archivo no es una imagen", HttpStatus.BAD_REQUEST);
            }
            Map<String, String> result = cloudinaryService.upload(multipartFile);
            String imageUrl = result.get("url");
            String publicId = result.get("public_id");

            Publication publication = new Publication();
            publication.setImage(imageUrl);
            publication.setTitle(title);
            publication.setDescription(description);
            publication.setCity(city);
            publication.setPublicId(publicId);
            publication.setFkUser(fkUser);
            publicationService.addPublication(publication);

            return new ResponseEntity<>("Publicacion subida", HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al procesar la publicacion", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @PutMapping("/{id}")
	public ResponseEntity<Publication> updatePublication(@RequestBody Publication publication, @PathVariable("id") Long id) {
		if (id.equals(publication.getIdPublication())) {
			return publicationService.getPublication(id)
			    	.map(existingPublication -> new ResponseEntity<>(publicationService.updatePublication(publication), HttpStatus.OK))
			    	.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removePublication(@PathVariable("id") Long id) {
        Optional<Publication> publicationOptional = publicationService.getPublication(id);
        if (publicationOptional.isEmpty()) {
            return new ResponseEntity<>("No existe la publicacion", HttpStatus.NOT_FOUND);
        } 

        Publication publication = publicationOptional.get();
        String cloudinaryPublicationId = publication.getPublicId(); 
        try {
            cloudinaryService.delete(cloudinaryPublicationId);
        } catch (IOException e) {
            return new ResponseEntity<>("Fallo al borrar la imagen", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        publicationService.removePublication(id);
        return new ResponseEntity<>("Publicacion borrada", HttpStatus.OK);
    }

}
