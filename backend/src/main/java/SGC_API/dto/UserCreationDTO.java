package SGC_API.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados necessários para criar um usuário")
public record UserCreationDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(
                min = 3,
                max = 100,
                message = "Nome deve possuir entre 3 e 100 caracteres"
        )
        @Schema(
                description = "Nome do usuário",
                example = "João Sousa"
        )
        String name,

        @Email(message = "Email inválido")
        @NotBlank(message = "Email é obrigatório")
        @Size(
                max = 150,
                message = "Email deve possuir no máximo 150 caracteres"
        )
        @Schema(
                description = "Email do usuário",
                example = "joao@email.com"
        )
        String email

) {}