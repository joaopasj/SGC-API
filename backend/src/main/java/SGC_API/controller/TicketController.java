package SGC_API.controller;

import SGC_API.dto.TicketCreationDTO;
import SGC_API.dto.TicketStatsDTO;
import SGC_API.entity.Ticket;
import SGC_API.enums.TicketPriority;
import SGC_API.enums.TicketStatus;
import SGC_API.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@Tag(
        name = "Chamados",
        description = "Gerenciamento de chamados de suporte"
)
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar todos os chamados",
            description = "Retorna todos os chamados cadastrados no sistema"
    )
    @GetMapping
    public List<Ticket> findAll() {
        return service.findAll();
    }

    @Operation(
            summary = "Buscar chamado por ID",
            description = "Retorna um chamado específico através do seu identificador"
    )
    @GetMapping("/{id}")
    public Ticket findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(
            summary = "Criar novo chamado",
            description = "Cria um novo chamado de suporte"
    )
    @PostMapping
    public Ticket create(
            @Valid @RequestBody TicketCreationDTO dto) {

        return service.create(dto);
    }

    @Operation(
            summary = "Atualizar chamado",
            description = "Atualiza os dados de um chamado existente"
    )
    @PutMapping("/{id}")
    public Ticket update(
            @PathVariable Long id,
            @Valid @RequestBody Ticket ticket) {

        return service.update(id, ticket);
    }

    @Operation(
            summary = "Excluir chamado",
            description = "Remove um chamado pelo ID"
    )
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Operation(
            summary = "Buscar por status",
            description = "Retorna todos os chamados com o status informado"
    )
    @GetMapping("/status/{status}")
    public List<Ticket> findByStatus(
            @PathVariable TicketStatus status) {

        return service.findByStatus(status);
    }

    @Operation(
            summary = "Dashboard de estatísticas",
            description = "Retorna indicadores gerais dos chamados"
    )
    @GetMapping("/stats")
    public TicketStatsDTO stats() {
        return service.getStats();
    }

    @Operation(
            summary = "Buscar por prioridade",
            description = "Retorna todos os chamados da prioridade informada"
    )
    @GetMapping("/priority/{priority}")
    public List<Ticket> findByPriority(
            @PathVariable TicketPriority priority) {

        return service.findByPriority(priority);
    }

    @Operation(
            summary = "Buscar por título",
            description = "Pesquisa chamados contendo o texto informado no título"
    )
    @GetMapping("/searchByTitle")
    public List<Ticket> searchByTitle(
            @RequestParam String title) {

        return service.searchByTitle(title);
    }

    @Operation(
            summary = "Listagem paginada",
            description = "Retorna os chamados utilizando paginação"
    )
    @GetMapping("/paged")
    public Page<Ticket> findAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return service.findAllPaged(page, size);
    }
}