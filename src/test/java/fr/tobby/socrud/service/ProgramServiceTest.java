package fr.tobby.socrud.service;

import fr.tobby.socrud.entity.DegreesEntity;
import fr.tobby.socrud.entity.SubjectEntity;
import fr.tobby.socrud.exception.SubjectNotFoundException;
import fr.tobby.socrud.model.ProgramModel;
import fr.tobby.socrud.model.request.CreateProgramRequest;
import fr.tobby.socrud.model.request.ProgramSubjectRequest;
import fr.tobby.socrud.model.request.UpdateProgramRequest;
import fr.tobby.socrud.repository.DegreesRepository;
import fr.tobby.socrud.repository.ProgramRepository;
import fr.tobby.socrud.repository.ProgramSubjectRepository;
import fr.tobby.socrud.repository.SubjectRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class ProgramServiceTest {
    private static long SOCRA_ID;
    private static long DEVOPS_ID;
    private static long MANAGEMENT_ID;

    private ProgramService programService;
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private DegreesRepository degreesRepository;

    @Autowired
    private ProgramSubjectRepository programSubjectRepository;

    @BeforeAll
    static void fillData(@Autowired DegreesRepository degreesRepository, @Autowired SubjectRepository subjectRepository) {
        degreesRepository.save(new DegreesEntity("MASTER"));
        SOCRA_ID = subjectRepository.save(new SubjectEntity("SOCRA", "Software Craftmanship")).getId();
        DEVOPS_ID =subjectRepository.save(new SubjectEntity("DEVOPS", "Devops")).getId();
        MANAGEMENT_ID = subjectRepository.save(new SubjectEntity("MANAGEMENT", "Management")).getId();
    }

    @BeforeEach
    void initProgramService() {
        programService = new ProgramService(programRepository, degreesRepository, subjectRepository, programSubjectRepository);
    }

    @Test
    void testCreateProgram() {
        String title = "Web Developer School";
        CreateProgramRequest createProgramRequest = CreateProgramRequest.builder()
                .title(title)
                .description("School to become a Web Developer")
                .campus("Paris")
                .degree("MASTER")
                .durationMonths(9)
                .price(500)
                .remotePercentage(50.0)
                .startDate(Date.valueOf("2024-06-06"))
                .subjects(new ArrayList<>() {{
                    add(ProgramSubjectRequest.builder().subjectId(SOCRA_ID).semesterIndex(1).build());
                }})
                .build();
        ProgramModel programModel = programService.create(createProgramRequest);
        Assertions.assertTrue(programModel.getTitle().equals(title));
        Assertions.assertEquals(1, programModel.getSubjects().get(1).size());
    }

    @Test
    void testCreateProgramWithNonExistingSubject() {
        String title = "Web Developer School";
        CreateProgramRequest createProgramRequest = CreateProgramRequest.builder()
                .title(title)
                .description("School to become a Web Developer")
                .campus("Paris")
                .degree("MASTER")
                .durationMonths(9)
                .price(500)
                .remotePercentage(50.0)
                .startDate(Date.valueOf("2024-06-06"))
                .subjects(new ArrayList<>() {{
                    add(ProgramSubjectRequest.builder().subjectId(8L).semesterIndex(1).build());
                }})
                .build();
        Assertions.assertThrows(SubjectNotFoundException.class, () -> programService.create(createProgramRequest));
    }

    @Test
    void testUpdateProgram() {
        String newTitle = "Full Stack Developer School";
        CreateProgramRequest createProgramRequest = CreateProgramRequest.builder()
                .title("Web Developer School")
                .description("School to become a Web Developer")
                .campus("Paris")
                .degree("MASTER")
                .durationMonths(9)
                .price(500)
                .remotePercentage(50.0)
                .startDate(Date.valueOf("2024-06-06"))
                .subjects(new ArrayList<ProgramSubjectRequest>() {{
                    add(ProgramSubjectRequest.builder().subjectId(SOCRA_ID).semesterIndex(1).build());
                    add(ProgramSubjectRequest.builder().subjectId(DEVOPS_ID).semesterIndex(1).build());
                }})
                .build();
        ProgramModel programModel = programService.create(createProgramRequest);
        UpdateProgramRequest updateProgramRequest = UpdateProgramRequest.builder()
                .title(newTitle)
                .subjects(List.of(ProgramSubjectRequest.builder()
                                .subjectId(DEVOPS_ID)
                                .semesterIndex(2)
                                .build(),
                        ProgramSubjectRequest.builder()
                                .subjectId(MANAGEMENT_ID)
                                .semesterIndex(2)
                                .build()))
                .subjectsToRemoveFromProgram(List.of(SOCRA_ID))
                .build();
        programModel = programService.update(programModel.getId(), updateProgramRequest);
        Assertions.assertTrue(programModel.getTitle().equals(newTitle));
        Assertions.assertEquals(2, programModel.getSubjects().get(2).size());
        Assertions.assertFalse(programModel.getSubjects().get(2).stream().filter(s -> s.getTitle().equals("DEVOPS")).toList().isEmpty());
        Assertions.assertFalse(programModel.getSubjects().get(2).stream().filter(s -> s.getTitle().equals("MANAGEMENT")).toList().isEmpty());
        Assertions.assertFalse(programModel.getSubjects().containsKey(1));
    }

    @AfterEach
    void deleteData(){
        programRepository.deleteAll();
    }
}
