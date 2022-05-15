package fr.tobby.socrud.repository;

import fr.tobby.socrud.entity.SubjectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends CrudRepository<SubjectEntity, Long> {
    List<SubjectEntity> findAll();

    Optional<SubjectEntity> findById();
}
