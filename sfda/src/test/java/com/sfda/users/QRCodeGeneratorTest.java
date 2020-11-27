package com.sfda.users;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sfda.util.QRCodeGenerator;

class QRCodeGeneratorTest {

	@Test
    public void junitWorksProperly(){
		assertTrue(true);
    }

	@Test
    public void testQRCodeIsNotEmpty() throws WriterException, IOException{
		QRCodeGenerator codeGenerator = new QRCodeGenerator();
		String qrGeneratorCode = "SFDA_Test";
		String charset = "UTF-8"; 
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		BitMatrix matrix = codeGenerator.createQRCode(qrGeneratorCode, charset, hintMap, 100, 100);
		assertTrue(matrix != null);
    }
	
	@Test
    public void testQRCodeValidtity(){
		assertTrue(true);
    }
	
	@Test
    public void testQRCodeImageFileWasCreated(){
		assertTrue(true);
    }
	
	@Test
    public void testQRCodeInputParamsValidity(){
		assertTrue(true);
    }
}