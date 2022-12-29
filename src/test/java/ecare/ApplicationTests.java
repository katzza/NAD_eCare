package ecare;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private Recource controller;
	@Test
	void contextLoads() {
	}

	@Test
	public void contextLoadsRecource() throws Exception {
		assertThat(controller).isNotNull();
	}

}
