package com.sfda.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeGenerator {

	public BitMatrix createQRCode(String qrGeneratorCode, String charset, Map hintMap, int qrHeight, int qrWidth)
			throws WriterException, IOException {

		return new BitMatrix(100);
	}

	public void saveQRCode(BitMatrix matrix, String filePath) throws WriterException, IOException {
	
	}
}