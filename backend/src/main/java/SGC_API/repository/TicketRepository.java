package SGC_API.repository;

import SGC_API.entity.Ticket;
import SGC_API.enums.TicketPriority;
import SGC_API.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByStatus(TicketStatus status);

    List<Ticket> findByPriority(TicketPriority priority);

    List<Ticket> findByTitleContainingIgnoreCase(String title);

    List<Ticket> findByStatusAndPriority(
            TicketStatus status,
            TicketPriority priority
    );

    long countByStatus(TicketStatus status);

    long countByPriority(TicketPriority priority);

    long countByStatusAndPriority(
            TicketStatus status,
            TicketPriority priority
    );
}