
    package com.learnk8s.api_tests;
  
    import com.intuit.karate.Results;
    import com.intuit.karate.Runner;
    // import com.intuit.karate.http.HttpServer;
    // import com.intuit.karate.http.ServerConfig;
    import org.junit.jupiter.api.Test;
  
    import static org.junit.jupiter.api.Assertions.assertEquals;
  
    class ApiTest {
  
        @Test
        void testAll() {
            String resourcepoolmanagement_db0413cb31_url = System.getenv().getOrDefault("RESOURCEPOOLMANAGEMENT_DB0413CB31_URL", "http://127.0.0.1:4010");
String serviceactivationandconfiguration_v1_c70db0ed15_url = System.getenv().getOrDefault("SERVICEACTIVATIONANDCONFIGURATION_V1_C70DB0ED15_URL", "http://127.0.0.1:4011");
String resourcepoolmanagement_db0413cb31_auth_token = System.getenv().getOrDefault("RESOURCEPOOLMANAGEMENT_DB0413CB31_AUTH_TOKEN", "dummy_RESOURCEPOOLMANAGEMENT_DB0413CB31_AUTH_TOKEN");
            Results results = Runner.path("src/test/java/com/learnk8s/api_tests")
                    .systemProperty("RESOURCEPOOLMANAGEMENT_DB0413CB31_URL",resourcepoolmanagement_db0413cb31_url)
.systemProperty("SERVICEACTIVATIONANDCONFIGURATION_V1_C70DB0ED15_URL",serviceactivationandconfiguration_v1_c70db0ed15_url)
.systemProperty("RESOURCEPOOLMANAGEMENT_DB0413CB31_AUTH_TOKEN", resourcepoolmanagement_db0413cb31_auth_token)
                    .reportDir("testReport").parallel(1);
            assertEquals(0, results.getFailCount(), results.getErrorMessages());
        }
  
    }
