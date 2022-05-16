package fr.tobby.socrud.repository;

import fr.tobby.socrud.entity.DegreesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DegreesRepository extends CrudRepository<DegreesEntity, Long> {
    List<DegreesEntity> findAll();

    Optional<DegreesEntity> findById(long id);
}
