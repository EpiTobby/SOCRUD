package fr.tobby.socrud.repository;

import fr.tobby.socrud.entity.AdminEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByLogin(String username);
}
