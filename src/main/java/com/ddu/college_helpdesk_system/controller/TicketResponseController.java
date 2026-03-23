package com.ddu.college_helpdesk_system.controller;

import com.ddu.college_helpdesk_system.entity.TicketResponse;
import com.ddu.college_helpdesk_system.service.TicketResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/responses")
public class TicketResponseController {

    @Autowired
    private TicketResponseService ticketResponseService;

    @PostMapping("/{ticketId}/{staffId}")
    public ResponseEntity<TicketResponse> addResponse(
            @PathVariable Long ticketId,
            @PathVariable Long staffId,
            @RequestBody TicketResponse response) {
        return ResponseEntity.ok(ticketResponseService.addResponse(ticketId, staffId, response));
    }

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<TicketResponse> getResponseByTicket(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketResponseService.getResponseByTicket(ticketId));
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllResponses() {
        return ResponseEntity.ok(ticketResponseService.getAllResponses());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResponse(@PathVariable Long id) {
        ticketResponseService.deleteResponse(id);
        return ResponseEntity.ok("Response deleted successfully!");
    }
}
