package org.ftel.dsc.springinaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GreetingControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void greetingReturnsHelloWorld_whenUnauthenticated_andSecurityDisabledForTest() {
		String url = "http://localhost:" + port + "/greeting";
		String body = this.restTemplate.getForObject(url, String.class);
		assertEquals("Hello World", body);
	}

	// Disable security for this test slice so we can assert the controller response directly.
	@TestConfiguration
	static class DisableSecurityConfig {
		@Bean
		SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http.csrf(AbstractHttpConfigurer::disable);
			http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
			return http.build();
		}
	}

}