package com.helen.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helen.Entity.UserFav;

public interface UserFavRepository extends JpaRepository<UserFav, Long> {

}
