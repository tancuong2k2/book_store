package com.datn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.datn.entity.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
	
	List<Users> findByIsDeleted(Boolean isDeleted);
	
	Users findByUsername(String username);
	
	Users findByEmail(String email);
	
	//login
	Users findByUsernameOrEmailAndIsEnabledAndIsDeleted(String username, String email, Boolean isEnabled, Boolean isDeleted);
	Users findByVerificationCode(String verificationCode);
	
	//Take token 
	Users findByResetPasswordToken(String token);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE users SET isDeleted = 1 WHERE username = ?", nativeQuery = true)
	void deleteLogical(String username);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE users SET isDeleted = 0 WHERE username = ?", nativeQuery = true)
	void recoveryLogical(String username);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE users SET email = ?1, hashPassword = ?2, fullname = ?3, imgUrl = ?4 WHERE username = ?5", nativeQuery = true)
	void update(String email, String hashPassword, String fullname, String imgUrl, String username);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE users SET email = ?1, fullname = ?2, imgUrl = ?3 WHERE username = ?4", nativeQuery = true)
	void updateNonPass(String email, String fullname, String imgUrl, String username);
}
