package com.helen.Service;

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
        return userFollowService.save(userFollow);
    }

    public UserFollow updateUserFollow(UserFollow userFollow) {
        return userFollowService.save(userFollow);
    }

    public void removeUserFollow(Long id) {
    	userFollowService.deleteById(id);
    }
	
}
