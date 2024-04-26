package com.helen.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helen.Entity.UserFav;
import com.helen.Repository.UserFavRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFavService {

    @Autowired
    private final UserFavRepository userFavRepository;

    public List<UserFav> getAllUserFavs() {
        return this.userFavRepository.findAll();
    }

    public Optional<UserFav> getUserFav(Long id) {
        return userFavRepository.findById(id);
    }

    public UserFav addUserFav(UserFav userFav) {
        // Verificar si ya existe un registro de "like" para el usuario y la publicación
        Optional<UserFav> existingUserFav = userFavRepository.findByFkUserAndFkPublication(userFav.getFkUser(), userFav.getFkPublication());
        if (existingUserFav.isPresent()) {
            // Si ya existe, simplemente devolvemos el registro existente sin crear uno nuevo
            return existingUserFav.get();
        } else {
            // Si no existe, creamos un nuevo registro de "like"
            return userFavRepository.save(userFav);
        }
    }

    public UserFav updateUserFav(UserFav userFav) {
        return userFavRepository.save(userFav);
    }

    @Transactional
    public boolean removeUserFav(Long userId, Long publicationId) {
        // Buscar el like por el userId y el publicationId y eliminarlo si se encuentra
        Optional<UserFav> userFavOptional = userFavRepository.findByFkUserAndFkPublication(userId, publicationId);
        if (userFavOptional.isPresent()) {
            userFavRepository.delete(userFavOptional.get());
            return true;
        } else {
            return false;
        }
    }

}
