package fr.tobby.socrud.controller;

import fr.tobby.socrud.exception.DegreeNotFoundException;
import fr.tobby.socrud.exception.EntityNotFoundException;
import fr.tobby.socrud.exception.ProgramNotFoundException;
import fr.tobby.socrud.model.ProgramModel;
import fr.tobby.socrud.model.request.CreateProgramRequest;
import fr.tobby.socrud.model.request.UpdateProgramRequest;
import fr.tobby.socrud.service.ProgramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/program")
public class ProgramController {
    private static final Logger logger = LoggerFactory.getLogger(ProgramController.class);
    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping(path = "")
    public Collection<ProgramModel> getPrograms() {
        return programService.getAllOrdered();
    }

    @GetMapping(path = "{id}")
    public ProgramModel getById(@PathVariable("id") Long id) {
        return programService.getById(id);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        programService.deleteById(id);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ProgramModel create(@RequestBody CreateProgramRequest request) {
        return programService.create(request);
    }

    @PatchMapping(path = "{id}")
    public ProgramModel updateById(@PathVariable("id") Long id, @RequestBody UpdateProgramRequest request) {
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
