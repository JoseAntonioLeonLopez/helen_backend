package com.helen.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helen.Entity.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    
}
