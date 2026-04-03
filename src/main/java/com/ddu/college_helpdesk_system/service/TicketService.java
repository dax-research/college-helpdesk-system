package com.ddu.college_helpdesk_system.service;

import com.ddu.college_helpdesk_system.entity.Ticket;
import com.ddu.college_helpdesk_system.entity.Student;
import com.ddu.college_helpdesk_system.entity.Department;
import com.ddu.college_helpdesk_system.enums.TicketStatus;
import com.ddu.college_helpdesk_system.repository.TicketRepository;
import com.ddu.college_helpdesk_system.repository.StudentRepository;
import com.ddu.college_helpdesk_system.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Ticket raiseTicket(Long studentId, Long departmentId, Ticket ticket) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));

        ticket.setRaisedBy(student);
        ticket.setDepartment(department);
        ticket.setStatus(TicketStatus.OPEN);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getUnresolvedTickets() {
        return ticketRepository.findByStatus(TicketStatus.OPEN);
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
    }

    public List<Ticket> getTicketsByStudent(Long studentId) {
        return ticketRepository.findByRaisedById(studentId);
    }

    public List<Ticket> getTicketsByDepartment(Long departmentId) {
        return ticketRepository.findByDepartmentId(departmentId);
    }

    public Ticket updateTicketStatus(Long id, TicketStatus status) {
        Ticket ticket = getTicketById(id);
        ticket.setStatus(status);
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Long ticketId, Ticket updatedTicket) {
        Ticket existing = getTicketById(ticketId);
        existing.setTitle(updatedTicket.getTitle());
        existing.setDescription(updatedTicket.getDescription());
        existing.setCategory(updatedTicket.getCategory());
        return ticketRepository.save(existing);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
