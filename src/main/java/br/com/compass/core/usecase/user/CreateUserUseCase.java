package br.com.compass.core.usecase.user;

import br.com.compass.core.domain.user.User;
import br.com.compass.core.domain.user.UserCreated;
import br.com.compass.core.repository.UserRepositoryInterface;
import br.com.compass.core.services.event.EventPublisher;
import br.com.compass.core.usecase.UseCase;
import br.com.compass.core.usecase.user.input.CreateUserInput;
import br.com.compass.core.voter.user.CreateUserVoter;
import br.com.compass.infra.listeners.CreateAccountWhenUserCreated;
import br.com.compass.infra.repository.AccountRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Date;

public class CreateUserUseCase implements UseCase<CreateUserInput, User> {
    private final UserRepositoryInterface userRepository;
    private final EventPublisher eventPublisher;

    public CreateUserUseCase(UserRepositoryInterface userRepository, EventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
        eventPublisher.addListener(new CreateAccountWhenUserCreated(new AccountRepository()));
    }

    @Override
    public User execute(CreateUserInput input) {
        new CreateUserVoter().invoke(input);
        String hashedPassword = BCrypt.hashpw(input.password(), BCrypt.gensalt());
        User user = new User(input.name(), Date.valueOf(input.birthDate()), input.cpf(), input.phone(), hashedPassword);
        try {
            User savedUser = userRepository.save(user);
            input.accountTypes().forEach(accountType -> eventPublisher.publish(new UserCreated(savedUser, accountType)));
            return savedUser;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
