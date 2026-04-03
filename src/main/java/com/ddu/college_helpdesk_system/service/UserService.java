package com.ddu.college_helpdesk_system.service;

import com.ddu.college_helpdesk_system.entity.Student;
import com.ddu.college_helpdesk_system.entity.Staff;
import com.ddu.college_helpdesk_system.entity.User;
import com.ddu.college_helpdesk_system.enums.Role;
import com.ddu.college_helpdesk_system.repository.StudentRepository;
import com.ddu.college_helpdesk_system.repository.StaffRepository;
import com.ddu.college_helpdesk_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerStudent(Student student) {
        if (userRepository.existsByEmail(student.getEmail())) {
            throw new IllegalArgumentException("Email already exists!");
        }
        if (studentRepository.existsByEnrollmentNo(student.getEnrollmentNo())) {
            throw new IllegalArgumentException("Enrollment number already exists!");
        }
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setRole(Role.STUDENT);
        return studentRepository.save(student);
    }

    public User registerStaff(Staff staff) {
        if (userRepository.existsByEmail(staff.getEmail())) {
            throw new IllegalArgumentException("Email already exists!");
        }
        if (staffRepository.existsByEmployeeId(staff.getEmployeeId())) {
            throw new IllegalArgumentException("Employee ID already exists!");
        }
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        staff.setRole(Role.STAFF);
        return staffRepository.save(staff);
    }

    public User registerAdmin(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}