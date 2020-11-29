package com.sfda.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sfda.entity.Users;
import com.sfda.links.UserLinks;
import com.sfda.service.UsersService;
import com.sfda.util.QRCodeGenerator;
import com.sfda.util.UserDetailsValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/")
public class UsersController {

	@Autowired
	UsersService usersService;

	@GetMapping(path = UserLinks.LIST_USERS)
	public ResponseEntity<?> listUsers() {
		log.info("UsersController:  list users");
		List<Users> resource = usersService.getUsers();
		return ResponseEntity.ok(resource);
	}

	@PostMapping(path = UserLinks.ADD_USER)
	public ResponseEntity<?> saveUser(@RequestBody Users user) {
		log.info("UsersController:  list users");
		Users resource = null;
		//if(UserDetailsValidator.isValidEmail(user.getEmail()) && UserDetailsValidator.isValidPhone(user.getPhone())) {
			user.setPhone(user.getPhone().replaceAll("[-, ]", ""));
			resource = usersService.saveUser(user);
		//}
		return ResponseEntity.ok(resource);
	}
	
	@PostMapping(path = UserLinks.CREATE_QR)
	public ResponseEntity<?> createQRCode(@RequestBody Users user) {
		log.info("UsersController#createQRCode");
		try {
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		BitMatrix matrix = QRCodeGenerator.createQRCode(user.getEmail(), "UTF-8", hintMap, 250, 250);
		return ResponseEntity.ok(matrix);
		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
		return (ResponseEntity<?>) ResponseEntity.EMPTY;
	}
}