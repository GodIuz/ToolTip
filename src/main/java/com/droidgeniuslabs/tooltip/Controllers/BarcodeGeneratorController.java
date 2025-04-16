package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BarcodeGeneratorController {
    @FXML private TextField inputField;
    @FXML private ImageView qrImageView;
    private BufferedImage bufferedQRImage;
    private final Utilities utilities = new Utilities();

    public void onGenerate() {
	String text = inputField.getText();
	if (text != null && !text.isEmpty()) {
	    try {
		bufferedQRImage = utilities.generateQRImage(text, 300, 300);
		Image fxImage = SwingFXUtils.toFXImage(bufferedQRImage, null);
		qrImageView.setImage(fxImage);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    @FXML
    public void onSave() {
	if (bufferedQRImage != null) {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Αποθήκευση QR Code");
	    fileChooser.getExtensionFilters().addAll(
			    new FileChooser.ExtensionFilter("PNG", "*.png"),
			    new FileChooser.ExtensionFilter("JPEG", "*.jpg")
	    );
	    File file = fileChooser.showSaveDialog(qrImageView.getScene().getWindow());
	    if (file != null) {
		try {
		    String format = file.getName().endsWith(".jpg") ? "jpg" : "png";
		    ImageIO.write(bufferedQRImage, format, file);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}
    }
}
