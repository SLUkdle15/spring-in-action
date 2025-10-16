package org.ftel.dsc.springinaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GreetingControllerTest {

	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		// Standalone setup uses only the controller defined below (no Spring context)
		mockMvc = MockMvcBuilders.standaloneSetup(new GreetingController()).build();
	}

	@Test
	void greetingReturnsHelloWorld() throws Exception {
		mockMvc.perform(get("/greeting"))
			.andExpect(status().isOk())
			.andExpect(content().string("Hello World"));
	}
}