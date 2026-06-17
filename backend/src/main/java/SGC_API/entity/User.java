package SGC_API.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Schema(description = "Entidade que representa um usuário do sistema")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Identificador único do usuário",
            example = "1"
    )
    private Long id;

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
    private String name;

    @Column(
            unique = true,
            nullable = false
    )
    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    @Size(
            max = 150,
            message = "Email deve possuir no máximo 150 caracteres"
    )
    @Schema(
            description = "Email do usuário",
            example = "joaosousa@email.com"
    )
    private String email;
}