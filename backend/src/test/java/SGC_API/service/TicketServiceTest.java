package SGC_API.service;

import SGC_API.dto.TicketStatsDTO;
import SGC_API.entity.Ticket;
import SGC_API.enums.TicketPriority;
import SGC_API.enums.TicketStatus;
import SGC_API.repository.TicketRepository;
import SGC_API.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TicketServiceTest {

    @Test
    void shouldFindAllTickets() {

        TicketRepository repository = mock(TicketRepository.class);
        UserRepository userRepository = mock(UserRepository.class);

        when(repository.findAll())
                .thenReturn(List.of(new Ticket()));

        TicketService service =
                new TicketService(repository, userRepository);

        List<Ticket> tickets = service.findAll();

        assertEquals(1, tickets.size());
    }

    @Test
    void shouldSaveTicket() {

        TicketRepository repository = mock(TicketRepository.class);
        UserRepository userRepository = mock(UserRepository.class);

        Ticket ticket = new Ticket();

        when(repository.save(ticket))
                .thenReturn(ticket);

        TicketService service =
                new TicketService(repository, userRepository);

        Ticket result = service.save(ticket);

        assertEquals(ticket, result);
    }

    @Test
    void shouldFindTicketById() {

        TicketRepository repository = mock(TicketRepository.class);
        UserRepository userRepository = mock(UserRepository.class);

        Ticket ticket = new Ticket();
        ticket.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(ticket));

        TicketService service =
                new TicketService(repository, userRepository);

        Ticket result = service.findById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void shouldUpdateTicket() {

        TicketRepository repository = mock(TicketRepository.class);
        UserRepository userRepository = mock(UserRepository.class);

        Ticket existing = new Ticket();
        existing.setId(1L);

        Ticket updated = new Ticket();
        updated.setTitle("Novo título");

        when(repository.findById(1L))
                .thenReturn(Optional.of(existing));

        when(repository.save(any(Ticket.class)))
                .thenReturn(existing);

        TicketService service =
                new TicketService(repository, userRepository);

        service.update(1L, updated);

        assertEquals("Novo título", existing.getTitle());
    }

    @Test
    void shouldFindByStatus() {

        TicketRepository repository = mock(TicketRepository.class);
        UserRepository userRepository = mock(UserRepository.class);

        when(repository.findByStatus(TicketStatus.ABERTO))
                .thenReturn(List.of(new Ticket()));

        TicketService service =
                new TicketService(repository, userRepository);

        List<Ticket> result =
                service.findByStatus(TicketStatus.ABERTO);

        assertEquals(1, result.size());
    }

    @Test
    void shouldFindByPriority() {

        TicketRepository repository = mock(TicketRepository.class);
        UserRepository userRepository = mock(UserRepository.class);

        when(repository.findByPriority(TicketPriority.ALTA))
                .thenReturn(List.of(new Ticket()));

        TicketService service =
                new TicketService(repository, userRepository);

        List<Ticket> result =
                service.findByPriority(TicketPriority.ALTA);

        assertEquals(1, result.size());
    }

    @Test
    void shouldSearchByTitle() {

        TicketRepository repository = mock(TicketRepository.class);
        UserRepository userRepository = mock(UserRepository.class);

        when(repository.findByTitleContainingIgnoreCase("computador"))
                .thenReturn(List.of(new Ticket()));

        TicketService service =
                new TicketService(repository, userRepository);

        List<Ticket> result =
                service.searchByTitle("computador");

        assertEquals(1, result.size());
    }
}