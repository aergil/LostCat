package org.lostcat.models.repositories;

import java.awt.image.BufferedImage;

public class FakeEntrepotImage extends EntrepotImages {

	public FakeEntrepotImage() {
	}

	@Override
	public String PutCatIcon(String id, BufferedImage file) {
		return id + "-little.png";
	}

	@Override
	public String PutCatImage(String id, BufferedImage file) {
		return id + ".png";
	}

}
