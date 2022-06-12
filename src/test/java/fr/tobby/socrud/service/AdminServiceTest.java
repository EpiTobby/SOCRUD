package fr.tobby.socrud.service;

import fr.tobby.socrud.auth.TokenManager;
import fr.tobby.socrud.entity.AdminEntity;
import fr.tobby.socrud.exception.AdminCreationFailed;
import fr.tobby.socrud.exception.ConnectionFailedException;
import fr.tobby.socrud.model.request.CreateAccountRequest;
import fr.tobby.socrud.model.request.LoginRequest;
import fr.tobby.socrud.model.response.LoginResponse;
import fr.tobby.socrud.repository.AdminRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DataJpaTest
class AdminServiceTest {

    private static AdminService adminService;
    private static AdminRepository adminRepository;
    private static TokenManager tokenManager;
    private static UserDetailsService userDetailsService;
    private static PasswordEncoder passwordEncoder;

    @BeforeAll
    static void beforeAll(@Autowired AdminRepository adminRepository)
    {
        AdminServiceTest.adminRepository = adminRepository;
        tokenManager = mock(TokenManager.class);
        userDetailsService = mock(UserDetailsService.class);
        passwordEncoder = mock(PasswordEncoder.class);
        adminService = new AdminService(adminRepository, tokenManager, userDetailsService, passwordEncoder);
    }

    @AfterEach
    void tearDown()
    {
        adminRepository.deleteAll();
        reset(tokenManager);
        reset(userDetailsService);
        reset(passwordEncoder);
    }

    @Test
    void loginTest()
    {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername("Joe")).thenReturn(userDetails);
        AdminEntity entity = new AdminEntity("Joe", "password");
        adminRepository.save(entity);
        when(passwordEncoder.matches("password", "password")).thenReturn(true);

        LoginResponse response = adminService.login(new LoginRequest("Joe", "password"));

        verify(tokenManager, times(1)).generateFor(userDetails, entity.getId());
    }

    @Test
    void loginInvalidUserNameTest()
    {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername("Joe")).thenReturn(userDetails);
        when(userDetailsService.loadUserByUsername("Marc")).thenThrow(new UsernameNotFoundException("Marc"));
        adminRepository.save(new AdminEntity("Joe", "password"));
        when(passwordEncoder.matches("password", "password")).thenReturn(true);

        assertThrows(UsernameNotFoundException.class, () -> adminService.login(new LoginRequest("Marc", "password")));
    }

    @Test
    void loginBadPasswordTest()
    {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername("Joe")).thenReturn(userDetails);
        adminRepository.save(new AdminEntity("Joe", "password"));
        when(passwordEncoder.matches("password", "password")).thenReturn(true);

        assertThrows(ConnectionFailedException.class, () -> adminService.login(new LoginRequest("Joe", "bad")));
    }

    @Test
    void createNewAdminTest()
    {
        adminService.create(new CreateAccountRequest("Joe", "pwd"));

        assertTrue(adminRepository.findByLogin("Joe").isPresent());
    }

    @Test
    void createNewAdminExistingUsernameTest()
    {
        adminRepository.save(new AdminEntity("Joe", "blbl"));

        assertThrows(AdminCreationFailed.class, () -> adminService.create(new CreateAccountRequest("Joe", "pwd")));
    }
}