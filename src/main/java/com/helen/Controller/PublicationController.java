package com.helen.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
import com.helen.Service.PublicationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/publications")
@AllArgsConstructor
public class PublicationController {
	
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
	public ResponseEntity<Publication> addPublication(@RequestBody Publication publication, @RequestParam("file") MultipartFile image) {
    	if (!image.isEmpty()) {
			Path imagesPublications = Paths.get("src//main//resources//static/imagesPublications");
			String rutaAbsoluta = imagesPublications.toFile().getAbsolutePath();
			
			try {
				byte[] bytesImg = image.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + image.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				
				publication.setImage(image.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
		return new ResponseEntity<Publication>(publicationService.addPublication(publication), HttpStatus.CREATED);
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
	public ResponseEntity<Void> removePublication(@PathVariable("id") Long id) {
		if (publicationService.removePublication(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
