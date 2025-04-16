package com.droidgeniuslabs.tooltip.Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class ExtEncryption {
    @FXML private TextArea textInput, textOutput;
    @FXML private TextField keyInput;
    @FXML
    public void encryptCaesar() {
        String text = textInput.getText();
        int shift = Integer.parseInt(keyInput.getText());
        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            encrypted.append((char)(c + shift));
        }
        textOutput.setText(encrypted.toString());
    }
    @FXML
    public void decryptCaesar() {
        String text = textInput.getText();
        int shift = Integer.parseInt(keyInput.getText());
        StringBuilder decrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            decrypted.append((char)(c - shift));
        }
        textOutput.setText(decrypted.toString());
    }
    @FXML
    public void encryptAES() {
        try {
            String plainText = textInput.getText();
            String keyString = keyInput.getText();

            byte[] keyBytes = keyString.getBytes();
            byte[] keyPadded = new byte[16];
            System.arraycopy(keyBytes, 0, keyPadded, 0, Math.min(keyBytes.length, 16));

            SecretKey secretKey = new SecretKeySpec(keyPadded, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            String encoded = Base64.getEncoder().encodeToString(encrypted);
            textOutput.setText(encoded);
        } catch (Exception e) {
            textOutput.setText("Error: " + e.getMessage());
        }
    }
    @FXML
    public void decryptAES() {
        try {
            String encodedText = textInput.getText();
            String keyString = keyInput.getText();

            byte[] keyBytes = keyString.getBytes();
            byte[] keyPadded = new byte[16];
            System.arraycopy(keyBytes, 0, keyPadded, 0, Math.min(keyBytes.length, 16));

            SecretKey secretKey = new SecretKeySpec(keyPadded, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decodedBytes = Base64.getDecoder().decode(encodedText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            textOutput.setText(new String(decrypted));
        } catch (Exception e) {
            textOutput.setText("Error: " + e.getMessage());
        }
    }
}
