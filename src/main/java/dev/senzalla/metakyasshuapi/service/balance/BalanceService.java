package dev.senzalla.metakyasshuapi.service.balance;

import dev.senzalla.metakyasshuapi.model.balance.module.BalanceDto;
import dev.senzalla.metakyasshuapi.model.balance.module.BalanceFilter;
import dev.senzalla.metakyasshuapi.service.InterfaceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BalanceService implements InterfaceService<BalanceDto, BalanceFilter, BalanceDto, BalanceDto> {
    private final BalanceSaveService saveService;
    private final BalanceDeleteService deleteService;
    private final BalanceFindService findService;

    @Override
    public BalanceDto save(BalanceDto form, String token) {
        return saveService.save(form, token);
    }

    @Override
    public BalanceDto update(Long pk, BalanceDto form) {
        return saveService.update(pk, form);
    }

    @Override
    public void delete(Long pk) {
        deleteService.delete(pk);
    }

    @Override
    public BalanceDto find(Long pk) {
        return findService.find(pk);
    }

    @Override
    public Page<BalanceDto> findAllPage(BalanceFilter balanceFilter, String token, Pageable pageable) {
        return findService.findAllPage(balanceFilter, token, pageable);
    }
}