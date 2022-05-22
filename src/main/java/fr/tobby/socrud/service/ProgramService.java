package fr.tobby.socrud.service;

import fr.tobby.socrud.entity.DegreesEntity;
import fr.tobby.socrud.entity.ProgramEntity;
import fr.tobby.socrud.exception.DegreeNotFoundException;
import fr.tobby.socrud.exception.ProgramNotFoundException;
import fr.tobby.socrud.model.ProgramModel;
import fr.tobby.socrud.model.request.CreateProgramRequest;
import fr.tobby.socrud.model.request.UpdateProgramRequest;
import fr.tobby.socrud.repository.DegreesRepository;
import fr.tobby.socrud.repository.ProgramRepository;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;
    private final DegreesRepository degreesRepository;

    public ProgramService(ProgramRepository programRepository, DegreesRepository degreesRepository) {
        this.programRepository = programRepository;
        this.degreesRepository = degreesRepository;
    }

    public Collection<ProgramModel> getAllOrdered() {
        List<ProgramEntity> programEntities = programRepository.findAllByOrderByStartDate();
        return programEntities.stream().map(ProgramModel::of).toList();
    }

    public List<ProgramModel> getAllOrdered(@Nullable String campus) {
        List<ProgramEntity> programEntities = programRepository.findAllByOrderByStartDate();
        Predicate<ProgramModel> predicate = model -> {
            return campus == null || model.getCampus().equals(campus);
        };
        return programEntities.stream().map(ProgramModel::of)
                              .filter(predicate)
                              .toList();
    }

    public ProgramModel getById(long id) {
        ProgramEntity programEntity = programRepository.findById(id).orElseThrow(() -> new ProgramNotFoundException("No program found with id " + id));
        return ProgramModel.of(programEntity);
    }

    public void deleteById(long id) {
        programRepository.deleteById(id);
    }

    public ProgramModel create(CreateProgramRequest request) {
        ProgramEntity programEntity = ProgramEntity.of(request);
        programEntity.setDegree(degreesRepository.findByTitle(request.getDegree()).orElseThrow(() -> new DegreeNotFoundException("No degree found with title " + request.getDegree())));
        return ProgramModel.of(programRepository.save(programEntity));
    }

    @Transactional
    public ProgramModel update(long id, UpdateProgramRequest request) {
        ProgramEntity programEntity = programRepository.findById(id).orElseThrow(() -> new ProgramNotFoundException("No program found with id " + id));
        if (request.getTitle() != null) {
            programEntity.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            programEntity.setDescription(request.getDescription());
        }
        if (request.getStartDate() != null) {
            programEntity.setStartDate(request.getStartDate());
        }
        if (request.getCampus() != null) {
            programEntity.setCampus(request.getCampus());
        }
        if (request.getDurationMonths() != null) {
            programEntity.setDurationMonths(request.getDurationMonths());
        }
        if (request.getDegree() != null) {
            DegreesEntity degree = degreesRepository.findByTitle(request.getDegree()).orElseThrow(() -> new ProgramNotFoundException("No degree found with id " + request.getDegree()));
            programEntity.setDegree(degree);
        }
        if (request.getPrice() != null) {
            programEntity.setPrice(request.getPrice());
        }
        if (request.getRemotePercentage() != null) {
            programEntity.setRemotePercentage(request.getRemotePercentage());
        }
        return ProgramModel.of(programEntity);
    }
}
