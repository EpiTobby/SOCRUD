package fr.tobby.socrud.controller;

import com.lowagie.text.DocumentException;
import fr.tobby.socrud.exception.EntityNotFoundException;
import fr.tobby.socrud.model.ProgramModel;
import fr.tobby.socrud.model.request.CreateProgramRequest;
import fr.tobby.socrud.model.request.UpdateProgramRequest;
import fr.tobby.socrud.service.PDFExporter;
import fr.tobby.socrud.service.ProgramSearchService;
import fr.tobby.socrud.service.ProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "/program")
public class ProgramController {
    private static final Logger logger = LoggerFactory.getLogger(ProgramController.class);
    private final ProgramService programService;
    private final ProgramSearchService searchService;
    private final PDFExporter pdfExporter;

    public ProgramController(ProgramService programService, final ProgramSearchService searchService, final PDFExporter pdfExporter) {
        this.programService = programService;
        this.searchService = searchService;
        this.pdfExporter = pdfExporter;
    }

    @GetMapping(path = "")
    @Operation(summary = "Get all the programs")
    @ApiResponse(responseCode = "200", description = "Returns all the programs")
    public Collection<ProgramModel> getPrograms() {
        return programService.getAllOrdered();
    }

    @GetMapping("search")
    public List<ProgramModel> search(@RequestParam("keywords") Collection<String> keywords) {
        return searchService.search(keywords);
    }

    @GetMapping("export/{id}")
    public void exportToPDF(@PathVariable("id") long id, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=programs.pdf");

        ProgramModel programModel = programService.getById(id);

        this.pdfExporter.exportProgram(response.getOutputStream(), programModel);
    }

    @GetMapping(path = "{id}")
    public ProgramModel getById(@PathVariable("id") long id) {
        return programService.getById(id);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a specific program")
    @ApiResponse(responseCode = "404", description = "Program has not been found")
    public void deleteById(@PathVariable("id") long id) {
        programService.deleteById(id);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a program")
    @ApiResponse(responseCode = "201", description = "Return the created program")
    @ApiResponse(responseCode = "404", description = "Program has not been found")
    public ProgramModel create(@RequestBody CreateProgramRequest request) {
        return programService.create(request);
    }

    @PatchMapping(path = "{id}")
    @Operation(summary = "Update a program")
    @ApiResponse(responseCode = "200", description = "Return the updated program")
    @ApiResponse(responseCode = "404", description = "Program has not been found")
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
