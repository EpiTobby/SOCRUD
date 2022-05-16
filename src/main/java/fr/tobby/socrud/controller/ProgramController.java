package fr.tobby.socrud.controller;

import fr.tobby.socrud.exception.ProgramNotFoundException;
import fr.tobby.socrud.model.ProgramModel;
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

    @ExceptionHandler(ProgramNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String getError(ProgramNotFoundException exception) {
        logger.debug("Error on request", exception);
        return exception.getMessage();
    }
}
