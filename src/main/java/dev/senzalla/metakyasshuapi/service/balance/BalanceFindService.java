package dev.senzalla.metakyasshuapi.service.balance;

import dev.senzalla.metakyasshuapi.model.balance.entity.Balance;
import dev.senzalla.metakyasshuapi.model.balance.mapper.BalanceMapper;
import dev.senzalla.metakyasshuapi.model.balance.module.BalanceDto;
import dev.senzalla.metakyasshuapi.model.balance.module.BalanceFilter;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.BalanceRepository;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class BalanceFindService {
    private final BalanceRepository repository;
    private final UserService userService;
    private final BalanceMapper mapper;

    public BalanceDto find(Long pk) {
        Balance balance = repository.findById(pk).orElseThrow(() -> new RuntimeException("Balance not found"));
        return mapper.toDto(balance);
    }

    public Page<BalanceDto> findAllPage(BalanceFilter balanceFilter, String token, Pageable pageable) {
        User user = userService.findByToken(token);
        Page<Balance> balances = repository.findAllByUserAndFilter(user, balanceFilter, pageable);
        return mapper.toSummarized(balances);
    }

}
