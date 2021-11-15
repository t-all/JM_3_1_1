package SpringBootCRUD.controller;

import SpringBootCRUD.model.Role;
import SpringBootCRUD.model.User;
import SpringBootCRUD.service.RoleService;
import SpringBootCRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


//    @PostConstruct
//    public void loadDataUser() {
//        Role role1 = new Role("ROLE_ADMIN");
//        Role role2 = new Role("ROLE_USER");
//        roleService.addRole(role1);
//        roleService.addRole(role2);
//
//        User user1 = new User("Bob", "qwe", "Bobby", "Bob", "Moon 13", "+375892154", "bob@test.com"/*, Set.of(role1, role2)*/);
//        User user2 = new User("Bil", "qwe", "Billy", "Bil", "Earth 1", "+935892154", "bil@test.com");
//        User user3 = new User("Tom", "qwe", "Tommy", "Tom", "Venus 5", "+475892154", "tom@test.com");
//        User user4 = new User("Sam", "qwe", "Sammy", "Sam", "Jupiter 34", "+175892154", "sam@test.com");
//        user1.setRoles(Set.of(role1, role2));
//        user2.setRoles(Set.of(role1, role2));
//        user3.setRoles(Set.of(role1, role2));
//        user4.setRoles(Set.of(role1, role2));
//        userService.addUser(user1);
//        userService.addUser(user2);
//        userService.addUser(user3);
//        userService.addUser(user4);
//    }


    @GetMapping("/user")
    public String userInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user";
    }

    @GetMapping(value = "/admin")
    public String listUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping("/admin/create")
    public String addUser(@ModelAttribute User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : checkBoxRoles) {
            roleSet.add(roleService.getRoleByRole(role));
        }
        user.setRoles(roleSet);
        userService.addUser(user);

        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}")
    public String editUserForm(@AuthenticationPrincipal User user, @PathVariable("id") long id, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }

    @PutMapping(value = "/edit")
    public String update(@ModelAttribute User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : checkBoxRoles) {
            roleSet.add(roleService.getRoleByRole(roles));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}