package br.com.compass.core.usecase.user;

import br.com.compass.core.domain.User;
import br.com.compass.core.repository.UserRepositoryInterface;
import br.com.compass.core.usecase.UseCase;
import br.com.compass.core.usecase.user.input.CreateUserInput;
import br.com.compass.core.voter.user.CreateUserVoter;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Date;

public class CreateUserUseCase implements UseCase<CreateUserInput, Void> {
    private final UserRepositoryInterface userRepository;

    public CreateUserUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Void execute(CreateUserInput input) {
        new CreateUserVoter().invoke(input);

        String hashedPassword = BCrypt.hashpw(input.password(), BCrypt.gensalt());
        User user = new User(input.name(), Date.valueOf(input.birthDate()), input.cpf(), input.phone(), hashedPassword);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
