package SGC_API.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import SGC_API.enums.TicketPriority;
import SGC_API.enums.TicketStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
@Schema(description = "Entidade que representa um chamado de suporte")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do chamado", example = "1")
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Size(
            min = 3,
            max = 100,
            message = "Título deve possuir entre 3 e 100 caracteres"
    )
    @Schema(description = "Título do chamado", example = "Computador não liga")
    private String title;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(
            min = 5,
            max = 500,
            message = "Descrição deve possuir entre 5 e 500 caracteres"
    )
    @Schema(
            description = "Descrição detalhada do problema",
            example = "Ao apertar o botão nada acontece"
    )
    private String description;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Schema(
            description = "Status atual do chamado",
            example = "ABERTO"
    )
    private TicketStatus status;

    @NotNull(message = "Prioridade é obrigatória")
    @Enumerated(EnumType.STRING)
    @Schema(
            description = "Prioridade do chamado",
            example = "ALTA"
    )
    private TicketPriority priority;

    @Schema(
            description = "Data e hora de criação do chamado",
            example = "2026-06-16T20:05:25"
    )
    private LocalDateTime createdAt;

    @Schema(
            description = "Data e hora da última atualização",
            example = "2026-06-16T21:10:00"
    )
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Schema(description = "Usuário responsável pela abertura do chamado")
    private User user;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}