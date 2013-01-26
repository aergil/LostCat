package models.Repositories;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import models.ImageResizer;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

public class EntrepotImages {

	public EntrepotImages() {
		bucketName = "mycat-storage";
		AWS_ACCESS_KEY = System.getenv("AWS_ACCESS_KEY");
		AWS_SECRET_KEY = System.getenv("AWS_SECRET_KEY");
		AWSCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
		amazonS3Client = new AmazonS3Client(AWSCredentials);
	}

	public String PutCatIcon(String id, File file) {
		return putImage(id, file, "-little", 52, 52);
	}

	public String PutCatImage(String id, File file) {
		return putImage(id, file, "", 400, 400);
	}

	private String putImage(String id, File fileToUpload, String nameExtension, int width, int height) {
		try {
			String fileName = id + nameExtension + ".png";
			BufferedImage bufferedImage = ImageResizer.resize(fileToUpload, width, height);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", baos);
			ByteArrayInputStream is = new ByteArrayInputStream(baos.toByteArray());
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType("image/png");
			amazonS3Client.putObject(bucketName, fileName, is, metadata);
			return fileName;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	protected final String bucketName;
	protected final String AWS_ACCESS_KEY;
	protected final String AWS_SECRET_KEY;
	protected final AWSCredentials AWSCredentials;
	protected final AmazonS3 amazonS3Client;
	protected static EntrepotImages instance;
}
