package fr.tobby.socrud.repository;

import fr.tobby.socrud.entity.ProgramEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProgramRepository extends CrudRepository<ProgramEntity, Long> {
    List<ProgramEntity> findAllByOrderByStartDate();
}
