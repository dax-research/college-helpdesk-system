package com.ddu.college_helpdesk_system.repository;

import com.ddu.college_helpdesk_system.entity.TicketResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TicketResponseRepository extends JpaRepository<TicketResponse, Long> {
    Optional<TicketResponse> findByTicketId(Long ticketId);
}