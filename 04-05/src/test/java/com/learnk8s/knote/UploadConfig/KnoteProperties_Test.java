package com.learnk8s.knote.UploadConfig;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

public class KnoteProperties_Test {

	@Test
	@Category(Categories.valid.class)
	public void testRetrievingUploadDirPathWhenSet() {
		knoteProperties = new KnoteProperties();
		knoteProperties.setUploadDir("/path/to/upload/dir");
		String uploadDir = knoteProperties.getUploadDir();
		org.junit.Assert.assertEquals("/path/to/upload/dir", uploadDir);
	}

	@Test
	@Category(Categories.valid.class)
	public void testRetrievingUploadDirPathWhenNotSet() {
		knoteProperties = new KnoteProperties();
		String uploadDir = knoteProperties.getUploadDir();
		org.junit.Assert.assertNull(uploadDir);
	}

	@Test
	@Category(Categories.valid.class)
	public void testRetrievingUploadDirPathWithSpecialCharacters() {
		knoteProperties = new KnoteProperties();
		knoteProperties.setUploadDir("/path/with/special!@#$%^&*()_+characters");
		String uploadDir = knoteProperties.getUploadDir();
		org.junit.Assert.assertEquals("/path/with/special!@#$%^&*()_+characters", uploadDir);
	}

	@Test
	@Category(Categories.valid.class)
	public void testRetrievingUploadDirPathForEmptyString() {
		knoteProperties = new KnoteProperties();
		knoteProperties.setUploadDir("");
		String uploadDir = knoteProperties.getUploadDir();
		org.junit.Assert.assertEquals("", uploadDir);
	}

}