package fr.tobby.socrud.service;

import fr.tobby.socrud.entity.ProgramEntity;
import fr.tobby.socrud.model.ProgramModel;
import fr.tobby.socrud.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public Collection<ProgramModel> getAllOrdered() {
        List<ProgramEntity> programEntities = programRepository.findAllByOrderByStartDate();
        return programEntities.stream().map(ProgramModel::of).toList();
    }
}
