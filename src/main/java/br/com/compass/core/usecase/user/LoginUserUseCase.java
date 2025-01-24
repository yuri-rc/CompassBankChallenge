package br.com.compass.core.usecase.user;

import br.com.compass.core.domain.user.User;
import br.com.compass.core.repository.UserRepositoryInterface;
import br.com.compass.core.usecase.UseCase;
import br.com.compass.core.usecase.user.input.LoginUserInput;
import org.mindrot.jbcrypt.BCrypt;

public class LoginUserUseCase implements UseCase<LoginUserInput, User> {
    private final UserRepositoryInterface userRepository;

    public LoginUserUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(LoginUserInput input) {
        User user;
        try {
            user = userRepository.getByCpf(input.cpf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if(!BCrypt.checkpw(input.password(), user.getPassword())) {
            throw new IllegalArgumentException("Incorrect password");
        }
        return user;
    }
}
