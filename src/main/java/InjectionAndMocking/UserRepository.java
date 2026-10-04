package InjectionAndMocking;

public class UserRepository {

    public String findNameById(Long id) {
        return "User " + id;
    }
}
