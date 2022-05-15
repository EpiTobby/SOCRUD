package fr.tobby.socrud.service;

import fr.tobby.socrud.exception.SubjectNotFound;
import fr.tobby.socrud.model.SubjectModel;
import fr.tobby.socrud.model.request.CreateSubjectRequest;
import fr.tobby.socrud.model.request.UpdateSubjectRequest;
import fr.tobby.socrud.repository.SubjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class SubjectServiceTest{
    private SubjectService subjectService;

    @Autowired
    private SubjectRepository subjectRepository;

    @BeforeEach
    void initSubjectService(){
        subjectService = new SubjectService(subjectRepository);
    }

    @Test
    void testCreateSubject(){
        CreateSubjectRequest request = CreateSubjectRequest.builder()
                .title("subject1")
                .description("description for subject1")
                .build();
        long id = subjectService.createSubject(request).getId();
        Assertions.assertTrue(subjectService.getById(id).getTitle().equals("subject1"));
    }

    @Test
    void testUpdateSubject(){
        String newDescr = "A better description for subject1";
        CreateSubjectRequest createSubjectRequest = CreateSubjectRequest.builder()
                .title("subject1")
                .description("description for subject1")
                .build();
        long id = subjectService.createSubject(createSubjectRequest).getId();
        UpdateSubjectRequest updateSubjectRequest = UpdateSubjectRequest.builder()
                .description(newDescr)
                .build();
        subjectService.updateSubject(id, updateSubjectRequest);
        Assertions.assertTrue(subjectService.getById(id).getDescription().equals(newDescr));
    }

    @Test
    void testDeleteSubject(){
        String newDescr = "A better description for subject1";
        CreateSubjectRequest createSubjectRequest = CreateSubjectRequest.builder()
                .title("subject1")
                .description("description for subject1")
                .build();
        long id = subjectService.createSubject(createSubjectRequest).getId();
        subjectService.deleteSubject(id);
        Assertions.assertThrows(SubjectNotFound.class, () -> subjectService.getById(id));
    }
}