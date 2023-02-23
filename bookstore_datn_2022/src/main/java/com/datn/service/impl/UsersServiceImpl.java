package com.datn.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.datn.config.EncoderConfig;
import com.datn.entity.Roles;
import com.datn.entity.Users;
import com.datn.repository.UsersRepo;
import com.datn.service.RolesService;
import com.datn.service.UsersService;
import com.datn.util.UserNotFoundExcepion;

import net.bytebuddy.utility.RandomString;

@Service
@Transactional(rollbackOn = { Exception.class, Throwable.class })
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepo repo;

	@Autowired
	private EncoderConfig encoderConfig;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private RolesService rolesService;

	@Override
	public List<Users> findAll() {
		return repo.findByIsDeleted(Boolean.FALSE);
	}

	@Override
	public List<Users> findByIsDeleted() {
		return repo.findByIsDeleted(Boolean.TRUE);
	}

	@Override
	public Users findByUsername(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public void save(Users user) throws UserNotFoundExcepion {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public void save(Users user, String siteURL) throws Exception {
		if (existsUsername(user.getUsername())) {
			throw new UserNotFoundExcepion("Người dùng đã tồn tại!");
		} else if (existsEmail(user.getEmail())) {
			throw new UserNotFoundExcepion("Email đã tồn tại!");
		}
		Roles role = rolesService.findByName("user");
		user.setRole(role);
		user.setIsDeleted(Boolean.FALSE);
		String hashPassword = encoderConfig.passwordEncoder().encode(user.getHashPassword());
		user.setHashPassword(hashPassword);
		String randomCode = RandomString.make(64);
		user.setVerificationCode(randomCode);
		Users userSaved = repo.saveAndFlush(user);
		sendVerificationEmail(userSaved, siteURL);
	}

	@Override
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public void update(Users user) throws UserNotFoundExcepion {
		if (ObjectUtils.isNotEmpty(user) && StringUtils.isEmpty(user.getHashPassword())) {
			repo.updateNonPass(user.getEmail(), user.getFullname(), user.getImgUrl(), user.getUsername());
		} else {
			String hashPassword = encoderConfig.passwordEncoder().encode(user.getHashPassword());
			user.setHashPassword(hashPassword);
			repo.update(user.getEmail(), hashPassword, user.getFullname(), user.getImgUrl(), user.getUsername());
		}
	}

	@Override
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public void update(Users user, String siteURL) throws UserNotFoundExcepion, Exception {
		if (ObjectUtils.isNotEmpty(user) && StringUtils.isEmpty(user.getHashPassword())) {
			repo.updateNonPass(user.getEmail(), user.getFullname(), user.getImgUrl(), user.getUsername());
		} else {
			String hashPassword = encoderConfig.passwordEncoder().encode(user.getHashPassword());
			user.setHashPassword(hashPassword);
			repo.update(user.getEmail(), hashPassword, user.getFullname(), user.getImgUrl(), user.getUsername());
			sendVerificationEmail(user, siteURL);
		}
	}

	@Override
	public Users doLogin(String usernameOrEmail, String password) throws UserNotFoundExcepion {
		Users userResponse = repo.findByUsernameOrEmailAndIsEnabledAndIsDeleted(usernameOrEmail, usernameOrEmail,
				Boolean.TRUE, Boolean.FALSE);

		if (userResponse == null) {
			throw new UserNotFoundExcepion("Người dùng không tồn tại!");
		} else if (userResponse.getIsEnabled() == false) {
			throw new UserNotFoundExcepion("Người dùng chưa được kích hoạt!");
		} else if (userResponse.getIsDeleted() == true) {
			throw new UserNotFoundExcepion("Người dùng đã bị xóa");
		} else {
			Boolean checkPassword = encoderConfig.passwordEncoder().matches(password, userResponse.getHashPassword());
			return checkPassword ? userResponse : null;
		}
	}

	@Override
	public Users doRegister(Users user) throws UserNotFoundExcepion {
		if (existsUsername(user.getUsername())) {
			throw new UserNotFoundExcepion("Username đã được sử dụng!");
		} else if (existsEmail(user.getEmail())) {
			throw new UserNotFoundExcepion("Email đã tồn tại!");
		} else {
			Roles role = rolesService.findByName("user");
			user.setRole(role);
			user.setIsEnabled(Boolean.TRUE);
			user.setIsDeleted(Boolean.FALSE);
			String password = encoderConfig.passwordEncoder().encode(user.getHashPassword());
			user.setHashPassword(password);

//			String randomCode = RandomString.make(64);
//			user.setVerificationCode(randomCode);
			return repo.saveAndFlush(user);
		}
	}

	@Override
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public void deleteLogical(String username) {
		repo.deleteLogical(username);
	}

	@Override
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public void recoveryLogical(String username) {
		repo.recoveryLogical(username);
	}

	@Override
	public void sendVerificationEmail(Users user, String siteURL) throws Exception {
		String subject = "datn - Verify your register";
		String senderName = "datn";
		String mailContent = "<p>Dear: " + user.getFullname() + ", </p>";
		mailContent += "<p>Please click the link below to cofirm your register!</p>";

		String verifiURL = siteURL + "/verify?verificationCode=" + user.getVerificationCode();
		mailContent += "<h1><a href = \"" + verifiURL + "\">Verification</a></h1>";
		mailContent += "<p> Thank You </p>";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("lamnhps16063@fpt.edu.vn", senderName);
		helper.setTo(user.getEmail());
		helper.setSubject(subject);
		helper.setText(mailContent, true);

		mailSender.send(message);
	}

	@Override
	public Boolean verificationUser(String verificationCode) {
		Users user = repo.findByVerificationCode(verificationCode);

		if (user == null || user.getIsEnabled()) {
			return false;
		} else {
			user.setVerificationCode(null);
			user.setIsEnabled(Boolean.TRUE);
			repo.saveAndFlush(user);
			return true;
		}
	}

	@Override
	public void changePassword(Users user, String newPassword) {
		String hashPassword = encoderConfig.passwordEncoder().encode(newPassword);
		user.setHashPassword(hashPassword);
		repo.saveAndFlush(user);
	}

	@Override
	public Users getResetPasswordToken(String token) {
		// TODO Auto-generated method stub
		return repo.findByResetPasswordToken(token);
	}

	@Override
	public void forgotPassword(String token, String email) throws UserNotFoundExcepion {
		Users user = repo.findByEmail(email);

		if (user == null) {
			throw new UserNotFoundExcepion("Không thể tìm thấy bất kỳ khách hàng nào có email " + email);
		} else if (!user.getIsEnabled()) {
			throw new UserNotFoundExcepion("Tài khoản chưa được kích hoạt");
		} else if (user.getIsDeleted()) {
			throw new UserNotFoundExcepion("Tài khoản đã bị xóa");
		} else {
			user.setResetPasswordToken(token);
			repo.saveAndFlush(user);
		}
	}

	@Override
	public void updatePassword(Users user, String newPassword) {
		String hashPassword = encoderConfig.passwordEncoder().encode(newPassword);

		user.setHashPassword(hashPassword);
		user.setResetPasswordToken(null);
		repo.saveAndFlush(user);
	}

	@Override
	public void sendMailActivated(String email, String linkResetPassword)
			throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("contact@datn.com", "datn SUPPORT");
		helper.setTo(email);

		String subject = "Here the link to reset your password";

		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password: </p>" + "<h1><a href=\"" + linkResetPassword
				+ "\">Change My Password</a></h1>" + "<br>" + "<p> Ignore this email if you do remember your password, "
				+ " or you have not made the request. </p>";

		helper.setSubject(subject);
		helper.setText(content, true);
		mailSender.send(message);
	}

	private Boolean existsUsername(String username) {
		return repo.findByUsername(username) != null ? true : false;
	}

	private Boolean existsEmail(String email) {
		return repo.findByEmail(email) != null ? true : false;
	}

	@Override
	public void change(Users user, String fullname, String email, String newImage) {
		user.setFullname(fullname);
		user.setEmail(email);
		user.setImgUrl(newImage);
		repo.save(user);
	}
}
