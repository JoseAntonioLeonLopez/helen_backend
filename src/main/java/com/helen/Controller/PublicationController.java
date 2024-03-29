package com.helen.Controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
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
    public ResponseEntity<Publication> addPublication(@RequestPart("image") MultipartFile file,
                                                      @RequestPart("title") String title,
                                                      @RequestPart("description") String description,
                                                      @RequestPart("city") String city,
                                                      @RequestPart("fkUser") Long fkUser) {
        try {
            // Crear un nuevo objeto Publication
            Publication publication = new Publication();
            publication.setImage(file.getBytes()); // Asignar los bytes de la imagen
            publication.setTitle(title);
            publication.setDescription(description);
            publication.setCity(city);
            publication.setFkUser(fkUser);

            // Llamar al servicio para agregar la publicación
            Publication addedPublication = publicationService.addPublication(publication);

            // Devolver la respuesta con la publicación agregada y el código de estado 201 (CREATED)
            return new ResponseEntity<>(addedPublication, HttpStatus.CREATED);
        } catch (IOException e) { // Maneja la IOException
            // Maneja la excepción (por ejemplo, registra el error)
            e.printStackTrace();
            // Devuelve una respuesta de error con el código de estado 500 (INTERNAL_SERVER_ERROR)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
	public ResponseEntity<Void> removePublication(@PathVariable("id") Long id) {
		if (publicationService.removePublication(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
