package com.helen.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helen.Entity.UserFollow;

public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {

}
