package SGC_API.controller;

import SGC_API.dto.UserCreationDTO;
import SGC_API.entity.User;
import SGC_API.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(
        name = "Usuários",
        description = "Gerenciamento de usuários do sistema"
)
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar usuários",
            description = "Retorna todos os usuários cadastrados"
    )
    @GetMapping
    public List<User> findAll() {
        return service.findAll();
    }

    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna um usuário específico através do seu identificador"
    )
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(
            summary = "Criar usuário",
            description = "Cria um novo usuário no sistema"
    )
    @PostMapping
    public User create(
            @Valid @RequestBody UserCreationDTO dto) {

        return service.create(dto);
    }

    @Operation(
            summary = "Atualizar usuário",
            description = "Atualiza os dados de um usuário existente"
    )
    @PutMapping("/{id}")
    public User update(
            @PathVariable Long id,
            @Valid @RequestBody User user) {

        return service.update(id, user);
    }

    @Operation(
            summary = "Excluir usuário",
            description = "Remove um usuário pelo ID"
    )
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}