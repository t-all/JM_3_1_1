package SpringBootCRUD.service;

import SpringBootCRUD.model.Role;
import SpringBootCRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Security;
import java.util.Set;

@Component
public class UserDataLoader {

    RoleService roleService;
    UserService userService;

    @Autowired
    public UserDataLoader(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void loadDataUser() {
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");
        roleService.addRole(role1);
        roleService.addRole(role2);

        User user1 = new User("Bob", "qwe", "Bobby", "Bob", "Moon 13", "+375892154", "bob@test.com"/*, Set.of(role1, role2)*/);
        User user2 = new User("Bil", "qwe", "Billy", "Bil", "Earth 1", "+935892154", "bil@test.com");
        User user3 = new User("Tom", "qwe", "Tommy", "Tom", "Venus 5", "+475892154", "tom@test.com");
        User user4 = new User("Sam", "qwe", "Sammy", "Sam", "Jupiter 34", "+175892154", "sam@test.com");
        user1.setRoles(Set.of(role1, role2));
        user2.setRoles(Set.of(role1, role2));
        user3.setRoles(Set.of(role1, role2));
        user4.setRoles(Set.of(role1, role2));
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);
    }
}
