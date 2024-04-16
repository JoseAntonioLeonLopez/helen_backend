package com.helen.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helen.Entity.UserFollow;
import com.helen.Repository.UserFollowRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFollowService {

	@Autowired
	private final UserFollowRepository userFollowService;
	
	public List<UserFollow> getAllUsersFollow() {
		return this.userFollowService.findAll();
	}
	
	public Optional<UserFollow> getUserFollow(Long id) {
        return userFollowService.findById(id);
    }

	public UserFollow addUserFollow(UserFollow userFollow) {
	    if (userFollow.getFkUserFollow() == userFollow.getFkUserFollowed()) {
	        throw new IllegalArgumentException("No se puede seguir a uno mismo");
	    }
	    
	    userFollow.setFollowDate(LocalDate.now());
	    
	    return userFollowService.save(userFollow);
	}


	public UserFollow updateUserFollow(UserFollow userFollow) {
	    if (!userFollowService.existsById(userFollow.getIdUserFollow())) {
	        throw new IllegalArgumentException("No se puede actualizar, el UserFollow no existe.");
	    }
	    
	    userFollow.setFollowDate(LocalDate.now());
	    
	    return userFollowService.save(userFollow);
	}


    public boolean removeUserFollow(Long id) {
    	return getUserFollow(id).map(userFollow -> {
			userFollowService.deleteById(id);
			return true;
		}).orElse(false);
    }
	
}
