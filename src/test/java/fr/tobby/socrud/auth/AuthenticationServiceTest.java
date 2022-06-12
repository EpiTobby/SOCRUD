package fr.tobby.socrud.auth;

import fr.tobby.socrud.entity.AdminEntity;
import fr.tobby.socrud.repository.AdminRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class AuthenticationServiceTest {

    @Autowired
    private AdminRepository adminRepository;

    private AuthenticationService service;

    @BeforeEach
    void setUp()
    {
        service = new AuthenticationService(adminRepository);
    }

    @AfterEach
    void tearDown()
    {
        adminRepository.deleteAll();
    }

    @Test
    void loadExistingUserTest()
    {
        adminRepository.save(new AdminEntity("root", "root"));

        UserDetails userDetails = service.loadUserByUsername("root");

        assertEquals("root", userDetails.getUsername());
        assertEquals("root", userDetails.getPassword());
    }

    @Test
    void shouldThrowExceptionWhenLoadUserIsCalledWithInvalidUsername()
    {
        adminRepository.save(new AdminEntity("root", "root"));


        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("foo"));
    }
}