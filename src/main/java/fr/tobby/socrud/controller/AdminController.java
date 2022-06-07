package fr.tobby.socrud.controller;

import fr.tobby.socrud.exception.AdminCreationFailed;
import fr.tobby.socrud.exception.ConnectionFailedException;
import fr.tobby.socrud.model.request.CreateAccountRequest;
import fr.tobby.socrud.model.request.LoginRequest;
import fr.tobby.socrud.model.response.LoginResponse;
import fr.tobby.socrud.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(path = "login")
    @Operation(summary = "Login")
    @ApiResponse(responseCode = "200", description = "Login success")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return adminService.login(loginRequest);
    }

    @PostMapping(path = "create")
    @PreAuthorize("hasAuthority('admin')")
    @Operation(summary = "Create admin account")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Create admin account succeed")
    public void createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        adminService.create(createAccountRequest);
    }

    @ExceptionHandler(ConnectionFailedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String getError(ConnectionFailedException exception) {
        logger.debug("Error on request", exception);
        return exception.getMessage();
    }

    @ExceptionHandler(AdminCreationFailed.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String adminCreationFailed(AdminCreationFailed exception) {
        return exception.getMessage();
    }
}
