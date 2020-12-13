package com.sfda.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/")
public class UsersController {

	private final UsersService usersService;
	
	public UsersController(UsersService usersService){
		this.usersService = usersService;
	}

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
		Users userAdded = usersService.findUser(user.getEmail());
		if (userAdded != null)
		{
			return  new ResponseEntity<>("User has been registered previously.", HttpStatus.BAD_REQUEST);
		}

		if (!UserDetailsValidator.isValidEmail(user.getEmail()))
			return  new ResponseEntity<>("Email format is incorrect", HttpStatus.BAD_REQUEST);
		if( !UserDetailsValidator.isValidPhone(user.getPhone())) {
			return  new ResponseEntity<>("Phone format is incorrect", HttpStatus.BAD_REQUEST);
		}
		List<Users> users = usersService.findAll();
		long id = users.size();
		user.setId(id++);
		resource = usersService.saveUser(user);
		resource = UserDetailsValidator.generateToken(resource);
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

	@GetMapping(path = UserLinks.GET_QR)
	public ResponseEntity<?> getQRCode(@ModelAttribute("email") String email) {
		log.info("UsersController#get QRCode");
		try {
			Users user = usersService.findUser(email);
			Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			BitMatrix matrix = QRCodeGenerator.createQRCode(user.getEmail(), "UTF-8", hintMap, 250, 250);
			BufferedImage img = QRCodeGenerator.saveQRCode(matrix, "");
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			ImageIO.write(img, "jpeg", bao);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "img/jpeg");
			user.setIsQRCodeGenerated("Y");
 			return new ResponseEntity<byte[]>(bao.toByteArray(), responseHeaders, HttpStatus.OK);
		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
		return (ResponseEntity<?>) ResponseEntity.EMPTY;
	}
}