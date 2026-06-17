package SGC_API.dto;

import SGC_API.enums.TicketPriority;
import SGC_API.enums.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TicketCreationDTO(

        @NotBlank(message = "Título é obrigatório")
        @Size(
                min = 3,
                max = 100,
                message = "Título deve possuir entre 3 e 100 caracteres"
        )
        String title,

        @NotBlank(message = "Descrição é obrigatória")
        @Size(
                min = 5,
                max = 500,
                message = "Descrição deve possuir entre 5 e 500 caracteres"
        )
        String description,

        @NotNull(message = "Status é obrigatório")
        TicketStatus status,

        @NotNull(message = "Prioridade é obrigatória")
        TicketPriority priority,

        Long userId

) {}