package dev.senzalla.metakyasshuapi.service.goal;

import dev.senzalla.metakyasshuapi.model.goal.module.GoalDto;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalFilter;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalForm;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalSummarized;
import dev.senzalla.metakyasshuapi.service.InterfaceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GoalService implements InterfaceService<GoalDto, GoalFilter, GoalForm, GoalSummarized> {
    private final GoalUpdateService updateService;
    private final GoalFindService findService;
    private final GoalAddService addService;


    @Override
    public GoalDto save(GoalForm form, String token) {
        return addService.save(form, token);
    }

    @Override
    public GoalDto update(Long pk, GoalForm form) {
        return updateService.update(pk, form);
    }

    @Override
    public void delete(Long pk) {

    }

    @Override
    public GoalDto find(Long pk) {
        return findService.find(pk);
    }

    @Override
    public Page<GoalSummarized> findAllPage(GoalFilter goalFilter, String token, Pageable pageable) {
        return findService.findAllPage(goalFilter, token, pageable);
    }
}
