package springSecurity.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springSecurity.models.User;
import springSecurity.repositories.RoleRepository;
import springSecurity.repositories.UserRepository;

@Service
public class RegistrationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository peopleRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = peopleRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        userRepository.save(person);
    }
}
