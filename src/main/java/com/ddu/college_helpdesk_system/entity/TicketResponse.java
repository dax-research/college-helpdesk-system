package com.ddu.college_helpdesk_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ticket_responses")
public class TicketResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String message;

    private LocalDateTime resolvedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "resolved_by")
    private Staff resolvedBy;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}
