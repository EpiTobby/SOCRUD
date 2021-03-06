package fr.tobby.socrud.service;

import fr.tobby.socrud.entity.SubjectEntity;
import fr.tobby.socrud.exception.SubjectNotFoundException;
import fr.tobby.socrud.model.SubjectModel;
import fr.tobby.socrud.model.request.CreateSubjectRequest;
import fr.tobby.socrud.model.request.UpdateSubjectRequest;
import fr.tobby.socrud.repository.SubjectRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Transactional
    public SubjectModel createSubject(CreateSubjectRequest request){
        SubjectEntity subjectEntity = subjectRepository.save(new SubjectEntity(request.getTitle(), request.getDescription()));
        return SubjectModel.of(subjectEntity);
    }

    public Collection<SubjectModel> getAll(){
        Collection<SubjectEntity> subjectEntities = subjectRepository.findAll();
        return subjectEntities.stream().map(SubjectModel::of).toList();
    }

    @NotNull
    public SubjectModel getById(long subjectId) throws SubjectNotFoundException {
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException("No subject found"));
        return SubjectModel.of(subjectEntity);
    }

    @Transactional
    public void updateSubject(long subjectId, UpdateSubjectRequest request){
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException("No subject found"));
        if (request.getTitle() != null)
            subjectEntity.setTitle(request.getTitle());
        if (request.getDescription() != null)
            subjectEntity.setDescription(request.getDescription());
    }

    @Transactional
    public void deleteSubject(long subjectId){
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException("No subject found"));
        subjectRepository.delete(subjectEntity);
    }
}
