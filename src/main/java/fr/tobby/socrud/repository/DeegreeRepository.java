package fr.tobby.socrud.repository;

import fr.tobby.socrud.entity.DeegreeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DeegreeRepository extends CrudRepository<DeegreeEntity, Long> {
    List<DeegreeEntity> findAll();

    Optional<DeegreeEntity> findById(long id);
}
