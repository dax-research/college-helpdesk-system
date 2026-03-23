package com.ddu.college_helpdesk_system.controller;

import com.ddu.college_helpdesk_system.entity.Staff;
import com.ddu.college_helpdesk_system.entity.Student;
import com.ddu.college_helpdesk_system.entity.User;
import com.ddu.college_helpdesk_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register/student")
    public ResponseEntity<User> registerStudent(@RequestBody Student student) {
        return ResponseEntity.ok(userService.registerStudent(student));
    }

    @PostMapping("/register/staff")
    public ResponseEntity<User> registerStaff(@RequestBody Staff staff) {
        return ResponseEntity.ok(userService.registerStaff(staff));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<User> registerAdmin(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerAdmin(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully!");
    }
}