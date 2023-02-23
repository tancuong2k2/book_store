package com.datn.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import com.datn.entity.Users;
import com.datn.util.UserNotFoundExcepion;

public interface UsersService {

	List<Users> findAll();

	List<Users> findByIsDeleted();

	Users findByUsername(String username);

	//Login
	Users doLogin(String usernameOrEmail, String password) throws UserNotFoundExcepion;
	
	//Register
	Users doRegister(Users user) throws UserNotFoundExcepion;

	void save(Users user) throws UserNotFoundExcepion;
	
	void save(Users user, String siteURL) throws UserNotFoundExcepion, Exception;

	void update(Users user) throws UserNotFoundExcepion;

	void update(Users user, String siteURL) throws UserNotFoundExcepion, Exception;
	
	//Send mail verification
	void sendVerificationEmail(Users user, String siteURL) throws Exception;
	
	//verification
	Boolean verificationUser(String verificationCode);
	
	//change-password
	void changePassword(Users user, String newPassword);
	
	//take token
	Users getResetPasswordToken(String token);
	
	//forgot-password
	void forgotPassword(String token, String email) throws UserNotFoundExcepion ;
	
	//send mail confirm user
	void sendMailActivated(String email, String linkResetPassword)throws UnsupportedEncodingException, MessagingException;
	
	//Change Pasword
	void updatePassword(Users user, String newPassword);

	void deleteLogical(String username);

	void recoveryLogical(String username);

	void change(Users user, String fullname, String email, String newImage);
}