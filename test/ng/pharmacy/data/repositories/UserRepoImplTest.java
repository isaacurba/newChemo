package ng.pharmacy.data.repositories;

import ng.pharmacy.data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepoImplTest {
    private User user;
    private UserRepository userRepo;

    @BeforeEach
    public void setUp(){
        user = new User();
        userRepo = new UserRepoImpl();
    }

    @Test
    public void crete1UserSaveAndCountIs1_Test(){
        user.setFullName("Isaac Kpomassi");
        user.setUsername("isaacurban0");
        user.setPassword("5231");
        userRepo.save(user);
        assertEquals(1, userRepo.count());
    }

    @Test
    public void crete2UserSaveAndCountIs2_Test(){
        User user1 = new User();
        user1.setFullName("Isaac Kpomassi");
        user1.setUsername("isaacurban0");
        user1.setPassword("5231");

        User user2 = new User();
        user2.setFullName("Barrack Obama");
        user2.setUsername("ziccostade");
        user2.setPassword("5231");

        userRepo.save(user1);
        userRepo.save(user2);

        assertEquals(2, userRepo.count());
    }

    @Test
    public void creat2SameUserCountIs1_Test(){
        User user1 = new User();
        user1.setFullName("Isaac Kpomassi");
        user1.setUsername("isaacurban0");
        user1.setPassword("5231");

        User user2 = new User();
        user2.setFullName("Isaac Kpomassi");
        user2.setUsername("isaacurban0");
        user2.setPassword("5231");

        userRepo.save(user1);
        userRepo.save(user2);

        assertEquals(1, userRepo.count());
    }

    @Test
    public void create1UserFindByUsernameCountIsOne_Test(){
        user.setFullName("Isaac Kpomassi");
        user.setUsername("isaacurban0");
        user.setPassword("5231");
        userRepo.save(user);

        User found = userRepo.findByUsername("isaacurban0");
        assertEquals("isaacurban0", found.getUsername());
        assertEquals(1, userRepo.count());
    }

    @Test
    public void create2UserFindByUsernameCountIs1_Test(){
        user.setFullName("Isaac Kpomassi");
        user.setUsername("isaacurban0");
        user.setPassword("5231");

        User user2 = new User();
        user2.setFullName("Isaac Kpomassi");
        user2.setUsername("isaacurban0");
        user2.setPassword("5231");

        userRepo.save(user);
        userRepo.save(user2);

        User found = userRepo.findByUsername("isaacurban0");
        assertEquals("isaacurban0", found.getUsername());
        assertEquals(1, userRepo.count());
    }

    @Test
    public void create1UserFindByIdCountIs1_Test(){
        user.setFullName("Isaac Kpomassi");
        user.setUsername("isaacurban0");
        user.setPassword("5231");

        userRepo.save(user);
        User found = userRepo.findById(1);
        assertEquals("isaacurban0", found.getUsername());
        assertEquals(1, userRepo.count());
    }

    @Test
    public void create2UserFindByIdCountIs2_Test(){
        user.setFullName("Isaac Kpomassi");
        user.setUsername("isaacurban0");
        user.setPassword("5231");

        User user2 = new User();
        user2.setFullName("Barrack Obama");
        user2.setUsername("semicolon");
        user2.setPassword("5231");

        userRepo.save(user);
        userRepo.save(user2);

        User found = userRepo.findById(1);
        assertEquals("isaacurban0", found.getUsername());
        assertEquals(2, userRepo.count());
    }

    @Test
    public void create2UsersFindByIdDelete1CountIs1_Test(){
        user.setFullName("Isaac Kpomassi");
        user.setUsername("isaacurban0");
        user.setPassword("5231");

        User user2 = new User();
        user2.setFullName("Barrack Obama");
        user2.setUsername("semicolon");
        user2.setPassword("5231");

        userRepo.save(user);
        userRepo.save(user2);

        userRepo.deleteById(1);
        assertEquals(1, userRepo.count());
    }













}