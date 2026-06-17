package SGC_API.service;

import SGC_API.dto.TicketCreationDTO;
import SGC_API.dto.TicketStatsDTO;
import SGC_API.entity.Ticket;
import SGC_API.entity.User;
import SGC_API.enums.TicketPriority;
import SGC_API.enums.TicketStatus;
import SGC_API.repository.TicketRepository;
import SGC_API.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository repository;

    private final UserRepository userRepository;

    public TicketService(
            TicketRepository repository,
            UserRepository userRepository) {

        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<Ticket> findAll() {
        return repository.findAll();
    }

    public Ticket save(Ticket ticket) {
        return repository.save(ticket);
    }

    public Ticket findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Ticket não encontrado"));
    }

    public Ticket update(Long id, Ticket ticket) {

        Ticket existing = findById(id);

        existing.setTitle(ticket.getTitle());
        existing.setDescription(ticket.getDescription());
        existing.setStatus(ticket.getStatus());
        existing.setPriority(ticket.getPriority());

        return repository.save(existing);
    }

    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Ticket não encontrado");
        }

        repository.deleteById(id);
    }

    public List<Ticket> findByStatus(TicketStatus status) {
        return repository.findByStatus(status);
    }

    public List<Ticket> findByPriority(TicketPriority priority) {
        return repository.findByPriority(priority);
    }

    public List<Ticket> searchByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    public Page<Ticket> findAllPaged(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public TicketStatsDTO getStats() {

        long abertos =
                repository.countByStatus(TicketStatus.ABERTO);

        long emAndamento =
                repository.countByStatus(TicketStatus.EM_ANDAMENTO);

        long fechados =
                repository.countByStatus(TicketStatus.FECHADO);

        long abertoAlta =
                repository.countByStatusAndPriority(
                        TicketStatus.ABERTO,
                        TicketPriority.ALTA
                );

        long abertoMedia =
                repository.countByStatusAndPriority(
                        TicketStatus.ABERTO,
                        TicketPriority.MEDIA
                );

        long abertoBaixa =
                repository.countByStatusAndPriority(
                        TicketStatus.ABERTO,
                        TicketPriority.BAIXA
                );

        long andamentoAlta =
                repository.countByStatusAndPriority(
                        TicketStatus.EM_ANDAMENTO,
                        TicketPriority.ALTA
                );

        long andamentoMedia =
                repository.countByStatusAndPriority(
                        TicketStatus.EM_ANDAMENTO,
                        TicketPriority.MEDIA
                );

        long andamentoBaixa =
                repository.countByStatusAndPriority(
                        TicketStatus.EM_ANDAMENTO,
                        TicketPriority.BAIXA
                );

        long fechadoAlta =
                repository.countByStatusAndPriority(
                        TicketStatus.FECHADO,
                        TicketPriority.ALTA
                );

        long fechadoMedia =
                repository.countByStatusAndPriority(
                        TicketStatus.FECHADO,
                        TicketPriority.MEDIA
                );

        long fechadoBaixa =
                repository.countByStatusAndPriority(
                        TicketStatus.FECHADO,
                        TicketPriority.BAIXA
                );

        return new TicketStatsDTO(
                abertos,
                emAndamento,
                fechados,

                abertoAlta,
                abertoMedia,
                abertoBaixa,

                andamentoAlta,
                andamentoMedia,
                andamentoBaixa,

                fechadoAlta,
                fechadoMedia,
                fechadoBaixa
        );
    }

    public Ticket create(TicketCreationDTO dto) {

        Ticket ticket = new Ticket();

        ticket.setTitle(dto.title());
        ticket.setDescription(dto.description());
        ticket.setStatus(dto.status());
        ticket.setPriority(dto.priority());

        if (dto.userId() != null) {

            User user = userRepository.findById(dto.userId())
                    .orElseThrow(() ->
                            new RuntimeException("Usuário não encontrado"));

            ticket.setUser(user);
        }

        return repository.save(ticket);
    }
}