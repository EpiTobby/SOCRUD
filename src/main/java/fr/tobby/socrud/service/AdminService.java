package fr.tobby.socrud.service;

import fr.tobby.socrud.entity.AdminEntity;
import fr.tobby.socrud.model.request.CreateAccountRequest;
import fr.tobby.socrud.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder encoder;

    public AdminService(final AdminRepository adminRepository, final PasswordEncoder encoder) {
        this.adminRepository = adminRepository;
        this.encoder = encoder;
    }

    @Transactional
    public void create(CreateAccountRequest request) {
        adminRepository.save(AdminEntity.of(request.getLogin(), encoder.encode(request.getPassword())));
    }
}
