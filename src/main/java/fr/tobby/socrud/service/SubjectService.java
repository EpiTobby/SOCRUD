package fr.tobby.socrud.service;

import fr.tobby.socrud.entity.SubjectEntity;
import fr.tobby.socrud.model.SubjectModel;
import fr.tobby.socrud.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Transactional
    public Collection<SubjectModel> getAll(){
        List<SubjectEntity> subjectEntities = subjectRepository.findAll();
        return subjectEntities.stream().map(e -> SubjectModel.of(e)).toList();
    }
}
