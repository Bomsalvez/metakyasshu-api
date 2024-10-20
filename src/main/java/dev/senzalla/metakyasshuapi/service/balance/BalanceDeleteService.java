package dev.senzalla.metakyasshuapi.service.balance;

import dev.senzalla.metakyasshuapi.repository.BalanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class BalanceDeleteService {
    private final BalanceRepository repository;

    public void delete(Long pk) {
        repository.deleteById(pk);
    }
}
