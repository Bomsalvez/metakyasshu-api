package dev.senzalla.metakyasshuapi.repository;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByKeyUser(String code);

    Optional<User> findUserByCpfUserOrEmailUserAndConfirmedUser(String cpfUser, String emailUser, @NotNull boolean confirmedUser);

    Optional<User> findUserByCpfUserAndKeyUser(String cpf, String token);
}