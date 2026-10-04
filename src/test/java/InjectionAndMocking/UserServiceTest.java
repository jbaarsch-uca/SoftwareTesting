package InjectionAndMocking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        // Plain, type-safe instantiation without magic
        userService = new UserService(userRepository);
    }

    @Test
    void testGetUserName() {
        when(userRepository.findNameById(1L)).thenReturn("Alice");

        String name = userService.getUserName(1L);

        Assertions.assertEquals("Alice", name);
        verify(userRepository).findNameById(1L);
    }


    //Setter Mocking
    /*
    @BeforeEach
    void setUp() {
        userService = new UserService();
        userService.setUserRepository(userRepository); // Explicit setup required
    }

    @Test
    void testGetUserName() {
        when(userRepository.findNameById(1L)).thenReturn("Bob");

        String name = userService.getUserName(1L);

        assertEquals("Bob", name);
    }



     */

    // Field Injection
    /*
    @Mock
    private UserRepository userRepository;

    @InjectMocks // Mockito uses reflection to force fields into private member variables
    private UserService userService;

    @Test
    void testGetUserName() {
        when(userRepository.findNameById(1L)).thenReturn("Charlie");

        String name = userService.getUserName(1L);

        assertEquals("Charlie", name);
    }
     */

}