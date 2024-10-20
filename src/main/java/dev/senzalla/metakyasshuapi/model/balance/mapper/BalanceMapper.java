package dev.senzalla.metakyasshuapi.model.balance.mapper;

import dev.senzalla.metakyasshuapi.model.InterfaceMapper;
import dev.senzalla.metakyasshuapi.model.balance.entity.Balance;
import dev.senzalla.metakyasshuapi.model.balance.module.BalanceDto;
import dev.senzalla.metakyasshuapi.model.category.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BalanceMapper implements InterfaceMapper<BalanceDto, Balance, BalanceDto, BalanceDto> {
    private final CategoryMapper categoryMapper;

    @Override
    public BalanceDto toDto(Balance balance) {
        BalanceDto balanceDto = new BalanceDto();
        balanceDto.setPkBalance(balance.getPkBalance());
        balanceDto.setDescriptionBalance(balance.getDescriptionBalance());
        balanceDto.setValueBalance(balance.getValueBalance());
        balanceDto.setDateBalance(balance.getDateBalance());
        balanceDto.setCategory(categoryMapper.toDto(balance.getCategory()));
        return balanceDto;
    }

    @Override
    public Balance toEntity(BalanceDto balanceDto) {
        Balance balance = new Balance();
        balance.setPkBalance(balanceDto.getPkBalance());
        balance.setDescriptionBalance(balanceDto.getDescriptionBalance());
        balance.setValueBalance(balanceDto.getValueBalance());
        balance.setDateBalance(balanceDto.getDateBalance());
        balance.setCategory(categoryMapper.toEntityExpense(balanceDto.getCategory()));
         return balance;
    }

    @Override
    public Page<BalanceDto> toSummarized(Page<Balance> balances) {
        return balances.map(this::toDto);
    }
}