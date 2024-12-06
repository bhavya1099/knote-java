package com.learnk8s.knote.UploadConfig

import org.junit.jupiter.api.Test
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.resource.PathResourceResolver
import org.mockito.Mockito
import org.junit.jupiter.api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

public class KnoteConfig_Test {

	@Test
	public void testAddingResourceHandlerWithDefaultCachePeriod() {
		// Arrange
		ResourceHandlerRegistry registry = mock(ResourceHandlerRegistry.class);
		KnoteProperties properties = new KnoteProperties();
		properties.setUploadDir("defaultUploadDir");
		KnoteConfig knoteConfig = new KnoteConfig();
		knoteConfig.properties = properties;
		// Act
		knoteConfig.addResourceHandlers(registry);
		// Assert
		verify(registry).addResourceHandler("/uploads/**")
			.addResourceLocations("file:" + properties.getUploadDir())
			.setCachePeriod(3600)
			.resourceChain(true)
			.addResolver(any(PathResourceResolver.class));
	}

	@Test
	public void testAddingResourceHandlerWithCustomCachePeriod() {
		// Arrange
		ResourceHandlerRegistry registry = mock(ResourceHandlerRegistry.class);
		KnoteProperties properties = new KnoteProperties();
		properties.setUploadDir("customUploadDir");
		KnoteConfig knoteConfig = new KnoteConfig();
		knoteConfig.properties = properties;
		// Act
		knoteConfig.addResourceHandlers(registry);
		// Assert
		verify(registry).addResourceHandler("/uploads/**")
			.addResourceLocations("file:" + properties.getUploadDir())
			.setCachePeriod(1800)
			.resourceChain(true)
			.addResolver(any(PathResourceResolver.class));
	}

	@Test
	public void testAddingResourceHandlerWithResourceChainDisabled() {
		// Arrange
		ResourceHandlerRegistry registry = mock(ResourceHandlerRegistry.class);
		KnoteProperties properties = new KnoteProperties();
		properties.setUploadDir("specificUploadDir");
		KnoteConfig knoteConfig = new KnoteConfig();
		knoteConfig.properties = properties;
		// Act
		knoteConfig.addResourceHandlers(registry);
		// Assert
		verify(registry).addResourceHandler("/uploads/**")
			.addResourceLocations("file:" + properties.getUploadDir())
			.setCachePeriod(3600)
			.resourceChain(false)
			.addResolver(any(PathResourceResolver.class));
	}

	@Test
	public void testAddingResourceHandlerWithCustomResolver() {
		// Arrange
		ResourceHandlerRegistry registry = mock(ResourceHandlerRegistry.class);
		KnoteProperties properties = new KnoteProperties();
		properties.setUploadDir("customUploadDir");
		KnoteConfig knoteConfig = new KnoteConfig();
		knoteConfig.properties = properties;
		// Act
		knoteConfig.addResourceHandlers(registry);
		// Assert
		verify(registry).addResourceHandler("/uploads/**")
			.addResourceLocations("file:" + properties.getUploadDir())
			.setCachePeriod(3600)
			.resourceChain(true)
			.addResolver(any(PathResourceResolver.class));
	}

	@Test
	public void testAddingResourceHandlerWithNonDefaultUploadDir() {
		// Arrange
		ResourceHandlerRegistry registry = mock(ResourceHandlerRegistry.class);
		KnoteProperties properties = new KnoteProperties();
		properties.setUploadDir("nonDefaultUploadDir");
		KnoteConfig knoteConfig = new KnoteConfig();
		knoteConfig.properties = properties;
		// Act
		knoteConfig.addResourceHandlers(registry);
		// Assert
		verify(registry).addResourceHandler("/uploads/**")
			.addResourceLocations("file:" + properties.getUploadDir())
			.setCachePeriod(3600)
			.resourceChain(true)
			.addResolver(any(PathResourceResolver.class));
	}

}