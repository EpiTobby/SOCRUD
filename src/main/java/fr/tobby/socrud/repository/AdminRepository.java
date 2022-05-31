package fr.tobby.socrud.repository;

import fr.tobby.socrud.entity.AdminEntity;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<AdminEntity, Long> {
}
