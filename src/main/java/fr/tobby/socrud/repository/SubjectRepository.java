package fr.tobby.socrud.repository;

import fr.tobby.socrud.entity.SubjectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface SubjectRepository extends CrudRepository<SubjectEntity, Long> {
    Collection<SubjectEntity> findAll();

    Optional<SubjectEntity> findById(long id);
}
