package com.ddu.college_helpdesk_system;

import com.ddu.college_helpdesk_system.entity.Student;
import com.ddu.college_helpdesk_system.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class CollegeHelpdeskSystemApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void testDuplicateEnrollmentNumberFails() {
        Student student1 = new Student();
        student1.setName("Student One");
        student1.setEmail("student1@example.com");
        student1.setPassword("123456");
        student1.setEnrollmentNo("EN123");
        student1.setBranch("CSE");
        student1.setSemester(1);

        userService.registerStudent(student1);

        Student student2 = new Student();
        student2.setName("Student Two");
        student2.setEmail("student2@example.com");
        student2.setPassword("123456");
        student2.setEnrollmentNo("EN123"); // Same enrollment number
        student2.setBranch("IT");
        student2.setSemester(1);

        assertThrows(IllegalArgumentException.class, () -> {
            userService.registerStudent(student2);
        }, "Should throw IllegalArgumentException for duplicate enrollment number");
    }

}
