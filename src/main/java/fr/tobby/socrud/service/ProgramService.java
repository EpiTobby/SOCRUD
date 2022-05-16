package fr.tobby.socrud.service;

import fr.tobby.socrud.entity.ProgramEntity;
import fr.tobby.socrud.exception.ProgramNotFoundException;
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

    public ProgramModel getById(Long id) {
        ProgramEntity programEntity = programRepository.findById(id).orElseThrow(() -> new ProgramNotFoundException("No program found with id " + id));
        return ProgramModel.of(programEntity);
    }

    public void deleteById(Long id) {
        programRepository.deleteById(id);
    }
}
