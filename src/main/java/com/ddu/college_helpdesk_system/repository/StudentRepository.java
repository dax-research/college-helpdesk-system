package com.ddu.college_helpdesk_system.repository;

import com.ddu.college_helpdesk_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEnrollmentNo(String enrollmentNo);
    boolean existsByEnrollmentNo(String enrollmentNo);
}