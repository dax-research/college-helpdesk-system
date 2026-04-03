package com.ddu.college_helpdesk_system.service;

import com.ddu.college_helpdesk_system.entity.Ticket;
import com.ddu.college_helpdesk_system.entity.TicketResponse;
import com.ddu.college_helpdesk_system.entity.Staff;
import com.ddu.college_helpdesk_system.enums.TicketStatus;
import com.ddu.college_helpdesk_system.repository.TicketResponseRepository;
import com.ddu.college_helpdesk_system.repository.TicketRepository;
import com.ddu.college_helpdesk_system.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketResponseService {

    @Autowired
    private TicketResponseRepository ticketResponseRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private StaffRepository staffRepository;

    public TicketResponse addResponse(Long ticketId, Long staffId, TicketResponse response) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketId));

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));

        response.setTicket(ticket);
        response.setResolvedBy(staff);

        ticket.setStatus(TicketStatus.RESOLVED);
        ticketRepository.save(ticket);

        return ticketResponseRepository.save(response);
    }

    public TicketResponse getResponseByTicket(Long ticketId) {
        return ticketResponseRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new RuntimeException("No response found for ticket id: " + ticketId));
    }

    public List<TicketResponse> getAllResponses() {
        return ticketResponseRepository.findAll();
    }

    public void deleteResponse(Long id) {
        ticketResponseRepository.deleteById(id);
    }
}