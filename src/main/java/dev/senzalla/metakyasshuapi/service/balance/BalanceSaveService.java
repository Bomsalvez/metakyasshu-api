package dev.senzalla.metakyasshuapi.service.balance;

import dev.senzalla.metakyasshuapi.model.balance.entity.Balance;
import dev.senzalla.metakyasshuapi.model.balance.mapper.BalanceMapper;
import dev.senzalla.metakyasshuapi.model.balance.module.BalanceDto;
import dev.senzalla.metakyasshuapi.model.category.mapper.CategoryMapper;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.BalanceRepository;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class BalanceSaveService {
    private final BalanceRepository repository;
    private final UserService userService;
    private final BalanceMapper mapper;
    private final CategoryMapper categoryMapper;

    public BalanceDto save(BalanceDto form, String token) {
        User user = userService.findByToken(token);
        Balance balance = mapper.toEntity(form);
        balance.setUser(user);
        repository.save(balance);
        return mapper.toDto(balance);
    }

    public BalanceDto update(Long pk, BalanceDto form) {
        Balance balance = repository.findById(pk).orElseThrow(() -> new RuntimeException("Balance not found"));
        balance.setDescriptionBalance(form.getDescriptionBalance());
        balance.setValueBalance(form.getValueBalance());
        balance.setDateBalance(form.getDateBalance());
        balance.setCategory(categoryMapper.toEntity(form.getCategory()));
        repository.save(balance);
        return mapper.toDto(balance);
    }
}
