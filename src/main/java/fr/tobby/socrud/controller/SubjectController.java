package fr.tobby.socrud.controller;

import fr.tobby.socrud.exception.SubjectNotFound;
import fr.tobby.socrud.model.SubjectModel;
import fr.tobby.socrud.model.request.CreateSubjectRequest;
import fr.tobby.socrud.model.request.UpdateSubjectRequest;
import fr.tobby.socrud.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "subjects")
public class SubjectController {
    private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("")
    public Collection<SubjectModel> getAll(){
        return subjectService.getAll();
    }

    @GetMapping("{id}")
    public SubjectModel getById(@PathVariable("id") long subjectId){
        return subjectService.getById(subjectId);
    }

    @PostMapping("")
    public SubjectModel createSubject(@RequestBody CreateSubjectRequest request){
        return subjectService.createSubject(request);
    }

    @PatchMapping("{id}")
    public void updateSubject(@PathVariable("id") long subjectId, @RequestBody UpdateSubjectRequest request){
        subjectService.updateSubject(subjectId, request);
    }

    @DeleteMapping("{id}")
    public void deleteSubject(@PathVariable("id") long subjectId){
        subjectService.deleteSubject(subjectId);
    }

    @ExceptionHandler(SubjectNotFound.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String getError(SubjectNotFound exception) {
        logger.debug("Error on request", exception);
        return exception.getMessage();
    }
}
