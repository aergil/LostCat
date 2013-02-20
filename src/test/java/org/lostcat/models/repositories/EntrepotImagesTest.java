package org.lostcat.models.repositories;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.lostcat.models.ObjectId;

public class EntrepotImagesTest {

	private static final String photoPath = "/home/zorg/workspace/LostCat/src/test/resources/logo-test.jpg";

	@Test
	public void peutEnregistrerIcon() throws IOException {
		BufferedImage photo = ImageIO.read(new File(photoPath));
		String id = ObjectId.getInc();
		String catIconFileName = Entrepots.images().PutCatIcon(id, photo);
		assertThat(catIconFileName).isEqualTo(id + "-little.png");
	}

	@Test
	public void peutEnregistrerImage() throws IOException {
		BufferedImage photo = ImageIO.read(new File(photoPath));

		String id = ObjectId.getInc();
		String catIconFileName = Entrepots.images().PutCatImage(id, photo);
		assertThat(catIconFileName).isEqualTo(id + ".png");
	}

}
