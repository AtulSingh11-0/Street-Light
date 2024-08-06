package ashis.com.backend.Street_light.Security.service;

import ashis.com.backend.Street_light.Security.model.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ashis.com.backend.Street_light.Security.repository.AdminRepository;
import ashis.com.backend.Street_light.Security.repository.ValidateRepository;

import java.util.Optional;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ValidateRepository validateRepository;

    @Override
    public UserDetails loadUserByUsername (String jobId ) throws UsernameNotFoundException {
        Optional<Validate> admin = validateRepository.findByJobId(jobId);

        if(admin.isPresent()) {
            Validate user = admin.get();
            return User.builder()
                    .username(user.getJobId())
                    .password(user.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException("Job Id doesn't exist");
        }
    }

//    private String[] getRoles(Validate admin) {
//        if (admin.getRole() == null) {
//            return new String[]{"ADMIN"};
//        }
//        return admin.getRole().split(",");
//    }
}
