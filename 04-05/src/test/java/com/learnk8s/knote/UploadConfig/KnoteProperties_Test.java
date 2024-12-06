package com.learnk8s.knote.UploadConfig

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api

public class KnoteProperties_Test {

	@BeforeEach
	public void setUp() {
		knoteProperties = new KnoteProperties();
	}

	@Test
	@Tag("valid")
	public void testRetrievingUploadDirPathWhenSet() {
		// Arrange
		String expectedUploadDir = "/path/to/upload/dir";
		knoteProperties.setUploadDir(expectedUploadDir);
		// Act
		String actualUploadDir = knoteProperties.getUploadDir();
		// Assert
		assertEquals(expectedUploadDir, actualUploadDir);
	}

	@Test
	@Tag("valid")
	public void testRetrievingUploadDirPathWhenNotSet() {
		// Act
		String actualUploadDir = knoteProperties.getUploadDir();
		// Assert
		assertEquals(null, actualUploadDir);
	}

	@Test
	@Tag("valid")
	public void testRetrievingUploadDirPathWithSpecialCharacters() {
		// Arrange
		String expectedUploadDir = "/path/with/special@chars";
		knoteProperties.setUploadDir(expectedUploadDir);
		// Act
		String actualUploadDir = knoteProperties.getUploadDir();
		// Assert
		assertEquals(expectedUploadDir, actualUploadDir);
	}

	@Test
	@Tag("valid")
	public void testRetrievingUploadDirPathForEmptyString() {
		// Arrange
		String expectedUploadDir = "";
		knoteProperties.setUploadDir(expectedUploadDir);
		// Act
		String actualUploadDir = knoteProperties.getUploadDir();
		// Assert
		assertEquals(expectedUploadDir, actualUploadDir);
	}

}