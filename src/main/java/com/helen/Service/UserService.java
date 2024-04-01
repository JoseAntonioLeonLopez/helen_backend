package com.helen.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helen.Entity.User;
import com.helen.Entity.UserFollow;
import com.helen.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	private final UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}
	
	public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

	public User addUser(User user) {
	    if (userRepository.findByEmail(user.getEmail()) != null) {
	        throw new IllegalStateException("Ya existe un User con este email: " + user.getEmail());
	    }
	    
	    if (user.getFkRole() == null) {
        	user.setFkRole((long) 2);
    	}
	    
	    return userRepository.save(user);
	}

	public User updateUser(User user) {
	    if (userRepository.findById(user.getIdUser()).isEmpty()) {
	        throw new IllegalArgumentException("No se puede actualizar, el User no existe.");
	    }
	    
	    return userRepository.save(user);
	}

    public boolean removeUser(Long id) {
    	return getUser(id).map(user -> {
			userRepository.deleteById(id);
			return true;
		}).orElse(false);
    }
    
    //Metodo para sacar todos los seguidores
	public List<User> getAllFollowers(Long id) {
		List<User> followers = new ArrayList<User>();
		
		return getUser(id)
				.map(usuario -> {
					for(UserFollow uf : usuario.getFollower()) {
						followers.add(uf.getFollower());
					}
					return followers;
				}).orElse(followers);
	}
	
}
