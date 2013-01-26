package tests;

import java.io.File;

import models.Repositories.EntrepotImages;

public class FakeEntrepotImage extends EntrepotImages {

	public FakeEntrepotImage() {
	}

	@Override
	public String PutCatIcon(String id, File file) {
		return id + "-little.png";
	}

	@Override
	public String PutCatImage(String id, File file) {
		return id + ".png";
	}

}
