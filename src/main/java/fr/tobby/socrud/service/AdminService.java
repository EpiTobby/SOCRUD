package fr.tobby.socrud.service;

import fr.tobby.socrud.auth.TokenManager;
import fr.tobby.socrud.entity.AdminEntity;
import fr.tobby.socrud.exception.ConnectionFailedException;
import fr.tobby.socrud.model.request.CreateAccountRequest;
import fr.tobby.socrud.model.request.LoginRequest;
import fr.tobby.socrud.model.response.LoginResponse;
import fr.tobby.socrud.repository.AdminRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

    public AdminService(final AdminRepository adminRepository, AuthenticationManager authenticationManager, TokenManager tokenManager, UserDetailsService userDetailsService, PasswordEncoder encoder) {
    this.adminRepository = adminRepository;
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    public LoginResponse login(LoginRequest loginRequest) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getLogin());
        AdminEntity adminEntity = adminRepository.findByLogin(loginRequest.getLogin()).orElseThrow(() -> new ConnectionFailedException("User does not exists"));
        String token = tokenManager.generateFor(userDetails, adminEntity.getId());
        return new LoginResponse(token);
    }

    @Transactional
    public void create(CreateAccountRequest request) {
        adminRepository.save(AdminEntity.of(request.getLogin(), encoder.encode(request.getPassword())));
    }
}
