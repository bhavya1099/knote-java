package com.learnk8s.knote.UploadConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.mockito.Mockito.when;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class KnoteConfig_Test {

@Test
public void testAddingResourceHandlerWithDefaultCachePeriod() {
    when(properties.getUploadDir()).thenReturn("/default/upload/dir");
    KnoteConfig knoteConfig = new KnoteConfig(properties);
    knoteConfig.addResourceHandlers(registry);
    // TODO: Verify that the resource handler is added with the default cache period of 3600 seconds.
}

@Test
public void testAddingResourceHandlerWithCustomCachePeriod() {
    when(properties.getUploadDir()).thenReturn("/custom/upload/dir");
    KnoteConfig knoteConfig = new KnoteConfig(properties);
    knoteConfig.addResourceHandlers(registry);
    // TODO: Verify that the resource handler is added with the specified custom cache period.
}

@Test
public void testAddingResourceHandlerWithResourceChainDisabled() {
    when(properties.getUploadDir()).thenReturn("/specific/upload/dir");
    KnoteConfig knoteConfig = new KnoteConfig(properties);
    knoteConfig.addResourceHandlers(registry);
    // TODO: Verify that the resource handler is added with the resource chain disabled.
}

@Test
public void testAddingResourceHandlerWithCustomResolver() {
    when(properties.getUploadDir()).thenReturn("/custom/resolver/upload/dir");
    KnoteConfig knoteConfig = new KnoteConfig(properties);
    knoteConfig.addResourceHandlers(registry);
    // TODO: Ensure that the resource handler is added with the specified custom resolver.
}

@Test
public void testAddingResourceHandlerWithNonDefaultUploadDir() {
    when(properties.getUploadDir()).thenReturn("/non-default/upload/dir");
    KnoteConfig knoteConfig = new KnoteConfig(properties);
    knoteConfig.addResourceHandlers(registry);
    // TODO: Verify that the resource handler is added with the specified non-default upload directory.
}

}