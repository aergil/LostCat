package tests;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;

import models.ObjectId;
import models.Repositories.Entrepots;

import org.junit.Test;

public class EntrepotImagesTest {

	@Test
	public void peutEnregistrerIcon() {
		File file = new File("/home/zorg/workspace/LostCat/test/tests/ressources/logo-test.jpg");

		String id = ObjectId.getInc();
		String catIconFileName = Entrepots.images().PutCatIcon(id, file);
		assertThat(catIconFileName).isEqualTo(id + "-little.png");
	}

	@Test
	public void peutEnregistrerImage() {
		File file = new File("/home/zorg/workspace/LostCat/test/tests/ressources/logo-test.jpg");

		String id = ObjectId.getInc();
		String catIconFileName = Entrepots.images().PutCatImage(id, file);
		assertThat(catIconFileName).isEqualTo(id + ".png");
	}

}
