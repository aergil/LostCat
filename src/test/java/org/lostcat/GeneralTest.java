package org.lostcat;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class GeneralTest {

	@Test
	public void PeutAccederAuxVariablesEnvironnment() {
		String AWS_ACCESS_KEY = System.getenv("AWS_ACCESS_KEY");
		String AWS_SECRET_KEY = System.getenv("AWS_SECRET_KEY");

		assertThat(AWS_ACCESS_KEY).isNotEmpty();
		assertThat(AWS_SECRET_KEY).isNotEmpty();
	}

}
