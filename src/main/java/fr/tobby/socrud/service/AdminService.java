package fr.tobby.socrud.service;

import fr.tobby.socrud.entity.AdminEntity;
import fr.tobby.socrud.model.request.CreateAccountRequest;
import fr.tobby.socrud.repository.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(final AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Transactional
    public void create(CreateAccountRequest request) {
        adminRepository.save(AdminEntity.of(request.getLogin(), request.getPassword()));
    }
}
