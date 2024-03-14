package com.helen.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helen.Entity.UserFav;
import com.helen.Repository.UserFavRepository;

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
        return userFavRepository.save(userFav);
    }

    public UserFav updateUserFav(UserFav userFav) {
        return userFavRepository.save(userFav);
    }

    public void removeUserFav(Long id) {
    	userFavRepository.deleteById(id);
    }
	
}
