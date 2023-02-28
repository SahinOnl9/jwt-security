package org.websec.jwtsecurity.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.websec.jwtsecurity.model.AppUser;

@DataJpaTest
class AppUserRepoTest {

	@Autowired
	private AppUserRepo underTest;

	@Test
	void testFindByUsername() {
		AppUser findByUsername = underTest.findByUsername("john23");
		assertThat(findByUsername).isNull();
	}

}
