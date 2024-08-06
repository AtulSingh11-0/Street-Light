package ashis.com.backend.Street_light.Security.controller;

import ashis.com.backend.Street_light.Security.model.Admin;
import ashis.com.backend.Street_light.Security.model.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ashis.com.backend.Street_light.Security.repository.AdminRepository;
import ashis.com.backend.Street_light.Security.repository.ValidateRepository;
import ashis.com.backend.Street_light.Security.service.AdminService;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("gov/original/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Qualifier("asyncExecutor")
    @Autowired
    private AsyncTaskExecutor asyncTaskExecutor;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ValidateRepository validateRepository;

    @Async
    @GetMapping("/register")
    public CompletableFuture<ResponseEntity<Validate>> handleGetRegistrationForm () {
        CompletableFuture< Validate > future = CompletableFuture.supplyAsync(() -> {
            Validate user = new Validate();
            return user;
        }, asyncTaskExecutor);

        return future.thenApply(result -> ResponseEntity.ok().body(result))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }

    @Async
    @PostMapping("/register")
    public CompletableFuture<ResponseEntity<?>> handleAdminRegistration (
            @RequestBody Validate user
    ) {
        Optional<Admin> admin = adminRepository.findByJobId(user.getJobId());

        if(admin.isPresent() && admin.get().getRole().equals("ADMIN")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return CompletableFuture.completedFuture(validateRepository.save(user))
                    .thenApply(savedAdmin -> {
                        if ( savedAdmin != null ) {
                            return ResponseEntity.ok().body(savedAdmin);
                        } else {
                            return ResponseEntity.notFound().build();
                        }
                    }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
        }
        return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Admin already exists"));
    }

}
