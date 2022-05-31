package fr.tobby.socrud.controller;

import fr.tobby.socrud.exception.EntityNotFoundException;
import fr.tobby.socrud.model.SubjectModel;
import fr.tobby.socrud.model.request.CreateSubjectRequest;
import fr.tobby.socrud.model.request.UpdateSubjectRequest;
import fr.tobby.socrud.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Get all the subjects")
    @ApiResponse(responseCode = "200", description = "Returns all the subjects")
    public Collection<SubjectModel> getAll(){
        return subjectService.getAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Get a specific subject")
    @ApiResponse(responseCode = "200", description = "Returns the subject with the given id")
    @ApiResponse(responseCode = "404", description = "Subject has not been found")
    public SubjectModel getById(@PathVariable("id") long subjectId){
        return subjectService.getById(subjectId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a subject")
    @ApiResponse(responseCode = "201", description = "Return the created subject")
    public SubjectModel createSubject(@RequestBody CreateSubjectRequest request){
        return subjectService.createSubject(request);
    }

    @PatchMapping("{id}")
    @Operation(summary = "Update a subject")
    @ApiResponse(responseCode = "200", description = "Return the updated subject")
    @ApiResponse(responseCode = "404", description = "Subject has not been found")
    public void updateSubject(@PathVariable("id") long subjectId, @RequestBody UpdateSubjectRequest request){
        subjectService.updateSubject(subjectId, request);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete the subject")
    @ApiResponse(responseCode = "200", description = "Subject has been deleted")
    @ApiResponse(responseCode = "404", description = "Subject has not been found")
    public void deleteSubject(@PathVariable("id") long subjectId){
        subjectService.deleteSubject(subjectId);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String getError(EntityNotFoundException exception) {
        logger.debug("Error on request", exception);
        return exception.getMessage();
    }
}
