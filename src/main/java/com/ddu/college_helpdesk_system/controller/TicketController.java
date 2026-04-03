package com.ddu.college_helpdesk_system.controller;

import com.ddu.college_helpdesk_system.entity.Ticket;
import com.ddu.college_helpdesk_system.enums.TicketStatus;
import com.ddu.college_helpdesk_system.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/raise/{studentId}/{departmentId}")
    public ResponseEntity<Ticket> raiseTicket(
            @PathVariable Long studentId,
            @PathVariable Long departmentId,
            @RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.raiseTicket(studentId, departmentId, ticket));
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Ticket>> getTicketsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(ticketService.getTicketsByStudent(studentId));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Ticket>> getTicketsByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(ticketService.getTicketsByDepartment(departmentId));
    }

    @GetMapping("/unresolved")
    public ResponseEntity<List<Ticket>> getUnresolvedTickets() {
        return ResponseEntity.ok(ticketService.getUnresolvedTickets());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Ticket> updateTicketStatus(
            @PathVariable Long id,
            @RequestParam TicketStatus status) {
        return ResponseEntity.ok(ticketService.updateTicketStatus(id, status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(
            @PathVariable Long id,
            @RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.updateTicket(id, ticket));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok("Ticket deleted successfully!");
    }
}
