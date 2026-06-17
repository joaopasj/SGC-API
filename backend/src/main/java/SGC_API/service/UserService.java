package SGC_API.service;

import SGC_API.dto.UserCreationDTO;
import SGC_API.entity.User;
import SGC_API.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Usuário não encontrado"
                        ));
    }

    public User create(UserCreationDTO dto) {

        if (repository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException(
                    "Já existe um usuário com este email"
            );
        }

        User user = new User();

        user.setName(dto.name());
        user.setEmail(dto.email());

        return repository.save(user);
    }

    public User update(Long id, User user) {

        User existing = findById(id);

        if (!existing.getEmail().equals(user.getEmail())
                && repository.existsByEmail(user.getEmail())) {

            throw new IllegalArgumentException(
                    "Já existe um usuário com este email"
            );
        }

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());

        return repository.save(existing);
    }

    public void delete(Long id) {

        User user = findById(id);

        repository.delete(user);
    }
}