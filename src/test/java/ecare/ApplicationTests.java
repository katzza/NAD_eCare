package ecare;

import ecare.resource.TariffController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private TariffController controller;

	@Test
	void contextLoadsRecource() throws Exception {
		assertThat(controller).isNotNull();
	}

}
