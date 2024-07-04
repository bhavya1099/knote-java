// ********RoostGPT********
/*
Test generated by RoostGPT for test test-workflow using AI Type Azure Open AI and AI Model roostgpt-4-32k
ROOST_METHOD_HASH=addResourceHandlers_ac8819fe1c
ROOST_METHOD_SIG_HASH=addResourceHandlers_ce66a353ba
"""
Scenario 1: Validate the registry object configuration after addResourceHandlers method execution
Details:
TestName: validateRegistryConfiguration
Description: This test will check whether the method correctly configures the registry object, including the resource handler configuration, cache period, and resolver.
Execution:
  Arrange: Mock ResourceHandlerRegistry object and Properties object (properties.getUploadDir() method).
  Act: Invoke the addResourceHandlers method using the mocked ResourceHandlerRegistry object.
  Assert: Validate that the resource handlers, cache period, and resolver configuration methods on the registry object are invoked with expected arguments.
Validation:
  This assertion verifies that the addResourceHandlers method configures the registry object accurately, which is vital for correct static resources handling in the application.
Scenario 2: Check if the ResourceHandler method is configured with the proper path
Details:
TestName: checkResourceHandlerPath
Description: This test is meant to ensure the set path "/uploads/**" is properly configured for the 'addResourceHandler' method.
Execution:
  Arrange: Mock the ResourceHandlerRegistry object.
  Act: Call the addResourceHandlers method using the mocked ResourceHandlerRegistry object.
  Assert: The 'addResourceHandler()' method is invoked with the correct argument, i.e., '/uploads/**'.
Validation:
  The assertion ensures that static resources are correctly fetched from the '/uploads/**' path. This is crucial as it directs the application to the right directory for resource fetching.
Scenario 3: Test if the method can handle the null value for properties.getUploadDir()
Details:
TestName: handleNullValueForGetUploadDir
Description: This test will check the behavior of the addResourceHandlers method when properties.getUploadDir() returns null.
Execution:
  Arrange: Mock the Properties object and its getUploadDir() method to return null, and mock the ResourceHandlerRegistry object.
  Act: Call the addResourceHandlers method using these mocks.
  Assert: The 'addResourceLocations()' method on the registry object is not invoked.
Validation:
  The assertion checks that the method behaves correctly when a null properties value is encountered, avoiding any null pointer exceptions and faulty configurations.
Scenario 4: Verify cache period setting
Details:
TestName: verifyCacheSetting
Description: This test will check if the cache period is set correctly in the method.
Execution:
  Arrange: Mock the ResourceHandlerRegistry object.
  Act: Call the addResourceHandlers method using the mocked ResourceHandlerRegistry object.
  Assert: Verify the 'setCachePeriod()' method is invoked with the correct parameter, i.e., 3600.
Validation:
  The assertion ensures that static resources are cached for the correct duration which improves performance by reducing redundant server requests.
Scenario 5: Verify resolver addition
Details:
TestName: verifyResolverAddition
Description: This test will check if PathResourceResolver is added correctly.
Execution:
  Arrange: Mock the ResourceHandlerRegistry object.
  Act: Call the addResourceHandlers method using the mocked ResourceHandlerRegistry object.
  Assert: Verify the 'addResolver()' method is invoked with PathResourceResolver object.
Validation:
  The assertion ensures that the correct resolver is added to handle the path-based resource resolution which is crucial for the correct functioning of the resource fetching mechanism.
"""
*/
// ********RoostGPT********
package com.learnk8s.knote.UploadConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.resource.PathResourceResolver;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.junit.experimental.categories.Category;

@Category({ Categories.addResourceHandlers.class, Categories.roostTest1.class, Categories.roostTest2.class })
@RunWith(MockitoJUnitRunner.class)
public class KnoteConfigAddResourceHandlersTest {

	@Mock
	private KnoteProperties properties;

	@Mock
	private ResourceHandlerRegistry registry;

	@Test
    public void validateRegistryConfiguration() {
        when(properties.getUploadDir()).thenReturn("testDir");
        KnoteConfig config = new KnoteConfig(properties);
        config.addResourceHandlers(registry);
        verify(registry).addResourceHandler("/uploads/**");
        verify(registry).addResourceLocations("file:testDir");
        verify(registry).setCachePeriod(3600);
        verify(registry).resourceChain(true);
        verify(registry).addResolver(isA(PathResourceResolver.class));
    }

	@Test
    public void checkResourceHandlerPath() {
        when(properties.getUploadDir()).thenReturn("testDir");
        KnoteConfig config = new KnoteConfig(properties);
        config.addResourceHandlers(registry);
        verify(registry).addResourceHandler("/uploads/**");
    }

	@Test
    public void handleNullValueForGetUploadDir() {
        when(properties.getUploadDir()).thenReturn(null);
        KnoteConfig config = new KnoteConfig(properties);
        config.addResourceHandlers(registry);
        verify(registry, never()).addResourceLocations(anyString());
    }

	@Test
    public void verifyCacheSetting() {
        when(properties.getUploadDir()).thenReturn("testDir");
        KnoteConfig config = new KnoteConfig(properties);
        config.addResourceHandlers(registry);
        verify(registry).setCachePeriod(3600);
    }

	@Test
    public void verifyResolverAddition() {
        when(properties.getUploadDir()).thenReturn("testDir");
        KnoteConfig config = new KnoteConfig(properties);
        config.addResourceHandlers(registry);
        verify(registry).addResolver(isA(PathResourceResolver.class));
    }

}