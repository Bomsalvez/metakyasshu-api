package dev.senzalla.metakyasshuapi.service.user;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class UserDeleteService {
    private final UserFindService findService;
    private final UserRepository repository;

    public void deleteUser(String token) {
        User user = findService.findByToken(token);
        if (checkParticipation(user)) {
            repository.delete(user);
        }
    }

    private boolean checkParticipation(User user) {
        if (user.getExpenses().stream().anyMatch(expense -> !expense.getParticipations().isEmpty() && expense.getPayment() == null) ||
                user.getGoals().stream().anyMatch(goal -> !goal.getParticipations().isEmpty() && goal.getDateExecutionGoal() == null)) {
            user.setConfirmedUser(false);
            repository.save(user);
            return false;
        }
        return true;
    }
}
