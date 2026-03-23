package com.ddu.college_helpdesk_system.repository;

import com.ddu.college_helpdesk_system.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByRaisedById(Long studentId);
    List<Ticket> findByDepartmentId(Long departmentId);
}