package fr.tobby.socrud.controller;

import fr.tobby.socrud.exception.EntityNotFoundException;
import fr.tobby.socrud.model.ProgramModel;
import fr.tobby.socrud.model.request.CreateProgramRequest;
import fr.tobby.socrud.model.request.UpdateProgramRequest;
import fr.tobby.socrud.service.ProgramSearchService;
import fr.tobby.socrud.service.ProgramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "/program")
public class ProgramController {
    private static final Logger logger = LoggerFactory.getLogger(ProgramController.class);
    private final ProgramService programService;
    private final ProgramSearchService searchService;

    public ProgramController(ProgramService programService, final ProgramSearchService searchService) {
        this.programService = programService;
        this.searchService = searchService;
    }

    @GetMapping(path = "")
    public Collection<ProgramModel> getPrograms(@RequestParam(name = "campus", required = false) String campus,
                                                @RequestParam(name = "degree", required = false) String degree) {
        return programService.getAllOrdered(campus, degree);
    }

    @GetMapping("search")
    public List<ProgramModel> search(@RequestParam("keywords") Collection<String> keywords)
    {
        return searchService.search(keywords);
    }

    @GetMapping(path = "{id}")
    public ProgramModel getById(@PathVariable("id") long id) {
        return programService.getById(id);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") long id) {
        programService.deleteById(id);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ProgramModel create(@RequestBody CreateProgramRequest request) {
        return programService.create(request);
    }

    @PatchMapping(path = "{id}")
    public ProgramModel updateById(@PathVariable("id") long id, @RequestBody UpdateProgramRequest request) {
        return programService.update(id, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String getError(EntityNotFoundException exception) {
        logger.debug("Error on request", exception);
        return exception.getMessage();
    }
}
