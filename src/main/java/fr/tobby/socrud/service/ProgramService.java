package fr.tobby.socrud.service;

import fr.tobby.socrud.entity.DegreesEntity;
import fr.tobby.socrud.entity.ProgramEntity;
import fr.tobby.socrud.entity.ProgramSubjectEntity;
import fr.tobby.socrud.entity.SubjectEntity;
import fr.tobby.socrud.exception.DegreeNotFoundException;
import fr.tobby.socrud.exception.ProgramNotFoundException;
import fr.tobby.socrud.exception.SubjectNotFoundException;
import fr.tobby.socrud.model.ProgramModel;
import fr.tobby.socrud.model.request.CreateProgramRequest;
import fr.tobby.socrud.model.request.ProgramSubjectRequest;
import fr.tobby.socrud.model.request.UpdateProgramRequest;
import fr.tobby.socrud.repository.DegreesRepository;
import fr.tobby.socrud.repository.ProgramRepository;
import fr.tobby.socrud.repository.ProgramSubjectRepository;
import fr.tobby.socrud.repository.SubjectRepository;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;
    private final DegreesRepository degreesRepository;
    private final SubjectRepository subjectRepository;
    private final ProgramSubjectRepository programSubjectRepository;

    public ProgramService(ProgramRepository programRepository, DegreesRepository degreesRepository, SubjectRepository subjectRepository, ProgramSubjectRepository programSubjectRepository) {
        this.programRepository = programRepository;
        this.degreesRepository = degreesRepository;
        this.subjectRepository = subjectRepository;
        this.programSubjectRepository = programSubjectRepository;
    }

    public Collection<ProgramModel> getAllOrdered() {
        List<ProgramEntity> programEntities = programRepository.findAllByOrderByStartDate();
        return programEntities.stream().map(ProgramModel::of).toList();
    }

    public List<ProgramModel> getAllOrdered(@Nullable String campus, @Nullable String degree, @Nullable Double remotePercentage,
                                            @Nullable Integer durationMonth)
    {
        List<ProgramEntity> programEntities = programRepository.findAllByOrderByStartDate();
        Predicate<ProgramModel> predicate = model -> {
            return (campus == null || model.getCampus().equalsIgnoreCase(campus))
                    && (degree == null || model.getDegree().equalsIgnoreCase(degree))
                    && (remotePercentage == null || model.getRemotePercentage() == remotePercentage)
                    && (durationMonth == null || model.getDurationMonths() == durationMonth);
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

    @Transactional
    public ProgramModel create(CreateProgramRequest request) {
        ProgramEntity programEntity = programRepository.save(ProgramEntity.of(request));
        programEntity.setDegree(degreesRepository.findByTitle(request.getDegree()).orElseThrow(() -> new DegreeNotFoundException("No degree found with title " + request.getDegree())));
        request.getSubjects().forEach(subject -> {
            SubjectEntity subjectEntity = subjectRepository.findById(subject.getSubjectId()).orElseThrow(() -> new SubjectNotFoundException("No subject found"));
            programEntity.getSubjects().add(programSubjectRepository.save(new ProgramSubjectEntity(programEntity, subjectEntity, subject.getSemesterIndex())));
        });
        return ProgramModel.of(programEntity);
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
        if (request.getSubjects() != null) {
            updateProgramSubjects(programEntity, request.getSubjects());
        }
        if (request.getSubjectsToRemoveFromProgram() != null) {
            programEntity.setSubjects(programEntity.getSubjects().stream().filter(e -> !request.getSubjectsToRemoveFromProgram()
                    .contains(e.getSubject().getId())).toList());
        }
        return ProgramModel.of(programEntity);
    }

    private void updateProgramSubjects(ProgramEntity programEntity, List<ProgramSubjectRequest> programSubjects)
    {

        for (final ProgramSubjectRequest subject : programSubjects)
        {
            Optional<ProgramSubjectEntity> programSubjectEntity = programEntity.getSubjects()
                                                                               .stream()
                                                                               .filter(e -> subject.getSubjectId().equals(e.getSubject().getId()))
                                                                               .findAny();
            if (programSubjectEntity.isPresent())
                programSubjectEntity.get().setSemesterIndex(subject.getSemesterIndex());
            else
            {
                SubjectEntity subjectEntity = subjectRepository.findById(subject.getSubjectId()).orElseThrow(() -> new SubjectNotFoundException("No subject found"));
                programEntity.getSubjects().add(programSubjectRepository.save(new ProgramSubjectEntity(programEntity, subjectEntity, subject.getSemesterIndex())));
            }
        }
    }
}
