package com.sfda.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

class QRCodeGeneratorTest {

	@Test
	public void junitWorksProperly() {
		assertTrue(true);
	}

	@Test
	public void testQRCodeIsNotEmpty() throws WriterException, IOException {
		QRCodeGenerator codeGenerator = new QRCodeGenerator();
		String qrGeneratorCode = "SFDA_Test";
		String charset = "UTF-8";
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		BitMatrix matrix = codeGenerator.createQRCode(qrGeneratorCode, charset, hintMap, 250, 250);

		assertTrue(matrix != null);
	}

	@Test
	public void testQRCodeValidtity() throws FileNotFoundException, IOException, NotFoundException, WriterException {
		// create the QR code
		QRCodeGenerator codeGenerator = new QRCodeGenerator();
		String qrGeneratorCode = "SFDA_Test";
		String charset = "UTF-8";
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		BitMatrix matrix = codeGenerator.createQRCode(qrGeneratorCode, charset, hintMap, 250, 250);
		// save the QR code
		codeGenerator.saveQRCode(matrix, "src/test/resources/QRCode.png");

		// now try to decode the QR code for validity
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream("src/test/resources/QRCode.png")))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap);
		String actualQrCode = qrCodeResult.getText();
		String expectedQrCode = "SFDA_Test";

		assertEquals(expectedQrCode, actualQrCode);
	}

	@Test
	public void testQRCodeImageFileWasCreated() throws WriterException, IOException, NotFoundException {
		// create the QR code
		QRCodeGenerator codeGenerator = new QRCodeGenerator();
		String qrGeneratorCode = "SFDA_Test";
		String charset = "UTF-8";
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		BitMatrix matrix = codeGenerator.createQRCode(qrGeneratorCode, charset, hintMap, 250, 250);
		// save the QR code
		codeGenerator.saveQRCode(matrix, "src/test/resources/QRCode.png");

		assertTrue(ImageIO.read(new FileInputStream("src/test/resources/QRCode.png")) != null);
	}

	@Test
	public void testQRCodeInputParamsValidity() throws WriterException, IOException {
		// create the QR code
		QRCodeGenerator codeGenerator = new QRCodeGenerator();
		String qrGeneratorCode = "SFDA_Test";
		String charset = "ABCD";
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		Exception exception = assertThrows(UnsupportedEncodingException.class, () -> {
			codeGenerator.createQRCode(qrGeneratorCode, charset, hintMap, 250, 250);
		});
		String expectedMessage = "ABCD";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
}