package fr.tobby.socrud.controller;

import fr.tobby.socrud.model.ProgramModel;
import fr.tobby.socrud.service.ProgramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
