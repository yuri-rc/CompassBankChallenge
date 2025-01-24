package br.com.compass.core.repository;

import br.com.compass.core.domain.user.User;

public interface UserRepositoryInterface {
    User save(User user)  throws Exception;
    User getByCpf(String cpf) throws Exception;
}
