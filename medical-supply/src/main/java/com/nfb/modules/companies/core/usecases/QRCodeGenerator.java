package com.nfb.modules.companies.core.usecases;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;


public class QRCodeGenerator {

    public static byte[] generateQRCodeImage(String data, int width, int height) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bufferedImage.createGraphics();

        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (bitMatrix.get(j, i)) {
                    graphics.fillRect(j, i, 1, 1);
                }
            }
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }

}
