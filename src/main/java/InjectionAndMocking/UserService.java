package InjectionAndMocking;

import java.util.Objects;

public class UserService {

    private final UserRepository userRepository; // Can be marked final
    // Setter injection does not allow final variables in object state to be mocked.
    //private UserRepository userRepository; // not marked final

    //Field injection
    // Normally would be @Autowired to be populated by Spring
    //public UserService() {
    //    this.userRepository = new UserRepository();
    //}

    public UserService(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository,
                "userRepository must not be null");
    }

    // Setter for setter injection
    //public void setUserRepository(UserRepository userRepository) {
    //    this.userRepository = userRepository;
    //}

    public String getUserName(Long id) {
        if (userRepository == null) {
            throw new IllegalStateException("UserRepository is not configured");
        }
        return userRepository.findNameById(id);
    }
}