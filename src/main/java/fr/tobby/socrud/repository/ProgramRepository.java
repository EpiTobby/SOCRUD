package fr.tobby.socrud.repository;

import fr.tobby.socrud.entity.ProgramEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface ProgramRepository extends CrudRepository<ProgramEntity, Long> {
    List<ProgramEntity> findAllByOrderByStartDate();

    @Override
    @NotNull
    Collection<ProgramEntity> findAll();
}
