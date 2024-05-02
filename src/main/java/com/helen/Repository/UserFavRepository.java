package com.helen.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helen.Entity.UserFav;

public interface UserFavRepository extends JpaRepository<UserFav, Long> {

    Optional<UserFav> findByFkUserAndFkPublication(Long fkUser, Long fkPublication);
    Optional<UserFav> deleteByFkUserAndFkPublication(Long fkUser, Long fkPublication);
	void deleteByFkPublication(Long id);
}
